package controllers

import models.db.defaultDb._
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

class Application extends Controller with Security[CommonProfile] {

  def index = Action {
    request =>
      {
        if (isAuthenticated(request)) {
          val newSession = getOrCreateSessionId(request)
          Redirect("/user").withSession(newSession)
        } else
          Ok(views.html.index("not protected", "log in", false))

      }
  }

  def user = RequiresAuthentication("FacebookClient") { profile =>
    Action { request =>
      {
        logger.debug(profile.getEmail() + " tries to connect")
        val user = getUser(profile.getEmail)
        user.map { x => Logger.debug(x.foldLeft("ids: ")(_ + _.id)) }

        Ok(views.html.index("protected", profile.getEmail, true))
      }
    }
  }

  def getProfile(request: RequestHeader) = {
    val webContext = new PlayWebContext(request, dataStore)
    val profileManager = new ProfileManager[CommonProfile](webContext)

    profileManager.get(true)
  }
  def isAuthenticated(request: RequestHeader): Boolean = {
    val webContext = new PlayWebContext(request, dataStore)
    val profileManager = new ProfileManager[CommonProfile](webContext)

    profileManager.isAuthenticated()
  }

  //  def index = Action {
  //    //getUserProfile(request)
  //    Ok(views.html.index("portico", "Log in"))
  //  }

  //accountTypes.map(i => Ok(views.html.index("ok", "peti", i.map(x => x.name))))

  def test = Action {

    //countryNames.map(i =>
    Ok(views.html.index("message", "name",false))

  }

  def links = Action.async {
    accountTypes.map(i => Ok(views.html.links("peti",false)))

  }

}

