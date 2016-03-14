package controllers.login

import org.pac4j.play.java.RequiresAuthentication
import play.api.mvc.Action
import play.Logger
import controllers.LazyPortfolio
import play.api.mvc.Controller
import models.db.defaultDb.loginAndSaveUser
import play.api.i18n.Messages.Implicits._
import play.api.libs.concurrent.Execution.Implicits.defaultContext

class LoginSocial extends Controller with LazyPortfolio {

  ///login
  def facebook = RequiresAuthentication("FacebookClient") { profile =>
    Action { request =>
      {
        Logger.debug("facebook authenticated user: " + profile.getEmail + " tries to connect")
        val userId = loginAndSaveUser(profile.getEmail, None, Some("Facebook"))
        userId.map { x => profile.setId(x) }
        Redirect("/")
      }
    }
  }

  def google = RequiresAuthentication("OidcClient") { profile =>
    Action { request =>
      {
        Logger.debug("google authenticated user: " + profile.getEmail + " tries to connect")
        val userId = loginAndSaveUser(profile.getEmail, None, Some("Google"))
        userId.map { x => profile.setId(x) }
        Redirect("/")
      }
    }
  }

}