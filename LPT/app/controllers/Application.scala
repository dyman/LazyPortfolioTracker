package controllers

import scala.util.Random
import javax.inject.Inject
import models.LoginData
import models.RegistrationData
import models.UserData
import models.db.defaultDb.getCachesQuotes
import models.db.defaultDb.loginAndSaveUser
import play.Logger
import play.api.data.Form
import play.api.data.Forms.mapping
import play.api.data.Forms.text
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.Action
import play.api.mvc.Controller
import play.api.i18n.Messages.Implicits._
import utils.mailer.Mailer
import play.api.Play.current
import models.Message
import models.db.defaultDb._

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

  def facebook = RequiresAuthentication("FacebookClient") { profile =>
    Action { request =>
      {
        Logger.debug("facebook authenticated user: " + profile.getEmail + " tries to connect")
        val userId = loginAndSaveUser(profile.getEmail, None, Some("Facebook"))
        Redirect("/")
      }
    }
  }

  def google = RequiresAuthentication("OidcClient") { profile =>
    Action { request =>
      {
        Logger.debug("google authenticated user: " + profile.getEmail + " tries to connect")
        val userId = loginAndSaveUser(profile.getEmail, None, Some("Google"))
        Redirect("/")
      }
    }
  }

  val registrationDataForm = Form(
    mapping(
      "username" -> text,
      "password" -> text,
      "passwordAgain" -> text)(RegistrationData.apply)(RegistrationData.unapply))

  val loginDataForm = Form(
    mapping(
      "username" -> text,
      "password" -> text)(LoginData.apply)(LoginData.unapply))

  def reg = Action {
    request =>
      {
        if (!isAuthenticated(request)) {
          Redirect("/register")
        } else {
          Redirect("/")
        }
      }
  }

  def register = Action {
    implicit request =>
      {
        val logindata = loginDataForm.bindFromRequest
        if (logindata.hasErrors) {
          Logger.debug("wrong login request" + logindata.toString() + " " + logindata.errors.toString())
        }
        val data = registrationDataForm.bindFromRequest

        data.fold(
          f => {
            Logger.debug("redisplay form because it has errors: " + f)
            Ok(views.html.register(f, "register", notLoggedInUser(request)))
          },
          t => {
            val user = notLoggedInUser(request)
            if (t.password.equals(t.passwordAgain)) {
              Logger.debug("tries to register" + t)
              val uid = preRegisterUser(t.email, t.password)

              mailer.sendMail(Message.registration(t.email, uid))
            }
            Ok(views.html.emailsent("regs", user))
          })

      }
  }

  def confirm(id: String) = Action {
    implicit request =>
      {
        Logger.debug("confirmation id: " + id)
        Ok(views.html.confirmed("confirmed", notLoggedInUser(request)))
      }
  }

  def quote = Action.async {
    getCachesQuotes.map(i => Ok(views.html.quote(Random.shuffle(i.toList).head)))
  }

}

