package controllers

import org.pac4j.play.scala.Security
import org.pac4j.core.profile.CommonProfile
import play.api.mvc.RequestHeader
import org.pac4j.play.PlayWebContext
import org.pac4j.core.profile.ProfileManager

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

}