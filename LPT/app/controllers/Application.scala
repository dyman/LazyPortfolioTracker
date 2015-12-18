package controllers

import models.db.defaultDb._
import scala.util.Random
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.Action
import play.api.mvc.Controller
import org.pac4j.core.profile._
import org.pac4j.play._
import org.pac4j.play.scala._
import play.api.libs.json.Json
import play.api.mvc._
import org.pac4j.core.profile._
import org.pac4j.play._
import org.pac4j.play.scala._
import play.api.libs.json.Json
import org.pac4j.play.java.RequiresAuthentication
import play.Logger
import play.mvc.Results.Redirect

class Application extends Controller with LazyPortfolio {

  def index = Action {
    request =>
      {
        if (isAuthenticated(request)) {
          val newSession = getOrCreateSessionId(request)
          Redirect("/user").withSession(newSession)
        } else {

          Ok(views.html.index("not protected", "log in", false))
        }

      }
  }

  def user = RequiresAuthentication("FacebookClient") { profile =>
    Action { request =>
      {
        Logger.debug("authenticated user: " + profile.getEmail + " tries to connect")
        val userId = loginAndSaveUser(profile.getEmail)

        Ok(views.html.index("protected", profile.getEmail, true))
      }
    }
  }

  def test = Action {

    //countryNames.map(i =>
    Ok(views.html.index("message", "name", false))

  }

  def links = Action.async {
    accountTypes.map(i => Ok(views.html.links("peti", false)))

  }

  def quote = Action.async {
    getCachesQuotes.map(i => Ok(views.html.quote(Random.shuffle(i.toList).head)))
  }

}

