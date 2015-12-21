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
import models.UserData

class Application extends Controller with LazyPortfolio {

  def index = Action {
    request =>
      {
        if (isAuthenticated(request)) {
          val profile = getProfile(request)
          Ok(views.html.index("protected", profile.getEmail, true))
        } else {

          Ok(views.html.index("not protected", "log in", false))
        }

      }
  }

  def index2 = Action {
    request =>
      {
        if (isAuthenticated(request)) {
          val profile = getProfile(request)
          val user = new UserData(true, profile.getEmail)
          Ok(views.html.index2("proba", user))
        } else {
          val user = new UserData(false, "no email")
          Ok(views.html.index2("proba", user))
        }

      }

  }

  def facebook = RequiresAuthentication("FacebookClient") { profile =>
    Action { request =>
      {
        Logger.debug("facebook authenticated user: " + profile.getEmail + " tries to connect")
        val userId = loginAndSaveUser(profile.getEmail)
        Redirect("/")
      }
    }
  }

  def google = RequiresAuthentication("OidcClient") { profile =>
    Action { request =>
      {
        Logger.debug("google authenticated user: " + profile.getEmail + " tries to connect")
        val userId = loginAndSaveUser(profile.getEmail)
        Redirect("/")
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

 
  
  def quote = Action.async {
    getCachesQuotes.map(i => Ok(views.html.quote(Random.shuffle(i.toList).head)))
  }

}

