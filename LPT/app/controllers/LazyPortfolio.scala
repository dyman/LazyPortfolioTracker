package controllers

import org.pac4j.play.scala.Security
import org.pac4j.core.profile.CommonProfile
import play.api.mvc.RequestHeader
import org.pac4j.play.PlayWebContext
import org.pac4j.core.profile.ProfileManager
import models.UserData
import org.pac4j.http.client.indirect.FormClient


trait LazyPortfolio  extends Security[CommonProfile] {
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

}