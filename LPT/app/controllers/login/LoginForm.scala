package controllers.login

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
import controllers.LazyPortfolio


class LoginForm  @Inject() (mailer: Mailer) extends Controller with LazyPortfolio  {
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
            Ok(views.html.logins.emailsent("regs", user))
          })

      }
  }

  def confirm(id: String) = Action {
    implicit request =>
      {
        Logger.debug("confirmation id: " + id)
        Ok(views.html.logins.confirmed("confirmed", notLoggedInUser(request)))
      }
  }
}