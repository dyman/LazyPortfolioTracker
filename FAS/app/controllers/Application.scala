package controllers

import models.db.defaultDb.countryNames
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc.Action
import play.api.mvc.Controller

class Application extends Controller {

  def index = Action {

    Ok(views.html.index("ok"))

  }

  def test = Action.async {

    countryNames.map(i => Ok(views.html.index(i.foldLeft("")(_ + _.name.get + ","))))

  }
}

