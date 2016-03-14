package controllers

import scala.util.Random
import javax.inject.Inject
import models.LoginData
import models.RegistrationData
import models.UserData
import models.db.defaultDb.getCachesQuotes
import play.Logger
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms.text
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.Action
import play.api.mvc.Controller
import play.api.i18n.Messages.Implicits._
import play.api.Play.current
import models.Message
import models.db.defaultDb._
import play.api.libs.json.Writes
import play.api.libs.json.JsValue
import play.api.libs.json.JsPath
import models.db.Tables.RecordingRow
import play.api.libs.json._
import play.api.libs.functional.syntax._
import models.utils.mailer.Mailer
import models.db.defaultDb
import play.api.libs.json.Writes.dateWrites
import scala.concurrent.Future

class Application @Inject() (mailer: Mailer) extends Controller with LazyPortfolio {

  def index = Action {
    request =>
      {
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
  
  implicit val recordingsWrites: Writes[RecordingRow] = (
    (JsPath \ "id").write[Int] and
    (JsPath \ "userid").write[Int] and
    (JsPath \ "ondate").write[Option[java.util.Date]])(unlift(RecordingRow.unapply))

  
    
  def recordings = Action.async {
    request =>
      {
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

  def quote = Action.async {
    getCachesQuotes.map(i => Ok(views.html.quote(Random.shuffle(i.toList).head)))
  }

}

