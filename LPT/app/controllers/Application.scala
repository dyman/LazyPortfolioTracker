package controllers

import java.io.File
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
import org.pac4j.http.client.indirect.FormClient
import org.pac4j.oauth.client.FacebookClient
import org.pac4j.oidc.client.OidcClient
import play.api.data.Form
import play.api.data.Mapping
import play.api.data._
import play.api.data.format.Formats._
import play.api.data.Forms._
import models.RegistrationData
import play.api.Play.current
import play.api.i18n.Messages.Implicits._
import models.LoginData
import javax.inject.Inject
import play.api.libs.mailer._
import org.apache.commons.mail.EmailAttachment

class Application @Inject() (mailer: MailerClient) extends Controller with LazyPortfolio {

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

  def notLoggedInUser(request: play.api.mvc.Request[play.api.mvc.AnyContent]) = {
    val newSession = getOrCreateSessionId(request)
    val webContext = new PlayWebContext(request, dataStore)
    val clients = config.getClients()
    val urlForm = (clients.findClient("FormClient").asInstanceOf[FormClient])
    //.getRedirectAction(webContext, false).getLocation
    new UserData(false, "no email", urlForm.getCallbackUrl)
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
            val user = new UserData(true, t.email, "")
            Logger.debug("tries to register" + t)
            sendEmail(t.email)
            Ok(views.html.index("regs", user))
          })

      }
  }

  def quote = Action.async {
    getCachesQuotes.map(i => Ok(views.html.quote(Random.shuffle(i.toList).head)))
  }

  def sendEmail(emailAddress:String): Unit = {

    val cid = "1234"
    val file = AttachmentFile("favicon.png", new File(current.classloader.getResource("public/images/favicon.png").getPath))
    val email = Email(
      "Simple email",
      "lazyportfoliotracker4@gmail.com",
      Seq("mandypeter@gmail.com",emailAddress),
      //bodyText = Some("A text message"),
      bodyHtml = Some(s"""<html><body><p>An <b>html</b> message with cid <img src="cid:$cid"></p></body></html>"""),
      attachments = Seq(
        file,
        AttachmentData("data.txt", "data".getBytes, "text/plain", Some("Simple data"), Some(EmailAttachment.INLINE))))

    val id = mailer.send(email)
  }

}

