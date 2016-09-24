package controllers

import javax.inject.Inject

import models.UserData
import models.db.Tables.RecordingRow
import models.db.defaultDb
import models.db.defaultDb.getCachesQuotes
import models.utils.mailer.Mailer
import play.Logger
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.functional.syntax._
import play.api.libs.json.{JsPath, Writes, _}
import play.api.mvc.{Action, Controller}

import scala.util.Random

// Combinator syntax

class Application @Inject()(mailer: Mailer) extends Controller with LazyPortfolio {

  def index = Action {
    request => {
      if (isAuthenticated(request)) {
        val profile = getProfile(request)

        val user = new UserData(true, profile.getEmail, "")
        Ok(views.html.index("proba", user))
      } else {
        val user = notLoggedInUser(request)
        Ok(views.html.index("proba", user))
      }
    }
  }

  //implicit val customDateWrites: Writes[java.util.Date] = dateWrites("yyyy-MM-dd")

  implicit val recordingWrites: Writes[RecordingRow] = (
    (JsPath \ "id").write[Int] and
      (JsPath \ "userid").write[Int] and
      (JsPath \ "ondate").write[Option[java.util.Date]]) (unlift(RecordingRow.unapply))

  def recordings = Action.async {
    request => {
      if (isAuthenticated(request)) {
        val profile = getProfile(request)
        val recordings = defaultDb.getRecordings(profile)
        recordings.map(x => {
          val json = Json.toJson(x)
          Logger.debug(Json.prettyPrint(json))
          Ok(json)
        })
      } else {
        notAuthenticated
      }
    }
  }

  //  implicit val recordingReads: Reads[RecordingJson] = (
  //    (JsPath \ "id").read[Int] and
  //    (JsPath \ "date").read[Date])
  //  (RecordingJson.apply _)

  //  def saveRecording = Action.async {
  //    request =>
  //      {
  //        if (isAuthenticated(request)) {
  //          val profile = getProfile(request)
  //
  //        } else {
  //          notAuthenticated
  //        }
  //
  //      }
  //  }

  def quote = Action.async {
    getCachesQuotes.map(i => Ok(views.html.quote(Random.shuffle(i.toList).head)))
  }

}

