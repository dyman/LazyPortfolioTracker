package controllers

import scala.annotation.implicitNotFound
import org.pac4j.core.profile.CommonProfile
import org.pac4j.core.profile.ProfileManager
import org.pac4j.http.client.indirect.FormClient
import org.pac4j.play.PlayWebContext
import org.pac4j.play.scala.Security
import models.UserData
import play.Logger
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.Action
import play.api.mvc.RequestHeader
import scala.concurrent.Future

trait LazyPortfolio extends Security[CommonProfile] {
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

  def notLoggedInUser(request: play.api.mvc.Request[play.api.mvc.AnyContent]) = {
    val newSession = getOrCreateSessionId(request)
    val webContext = new PlayWebContext(request, dataStore)
    val clients = config.getClients()
    val urlForm = (clients.findClient("FormClient").asInstanceOf[FormClient])
    //.getRedirectAction(webContext, false).getLocation
    new UserData(false, "no email", urlForm.getCallbackUrl)
  }

  val notAuthenticated = Future {
    InternalServerError("not authenticated")
  }

}