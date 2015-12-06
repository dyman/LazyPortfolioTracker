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

class Application extends Controller with Security[CommonProfile] {

  def index = Action.async {
    accountTypes.map(i => Ok(views.html.index("ok", "peti", i.map(x => x.name))))
  }

  def test = Action.async {

    countryNames.map(i =>
      Ok(views.html.index("message","name", i.map(x => x.name))))

  }

  def links = Action.async {
    accountTypes.map(i => Ok(views.html.links("peti", i.map(x => x.name))))

  }
  
    
  def facebookIndex = RequiresAuthentication("FacebookClient") { profile =>
    Action { request =>
      Logger.debug(profile.getEmail() + " tries to connect")
      Ok(views.html.protectedIndex(profile))
    }
  }

}

