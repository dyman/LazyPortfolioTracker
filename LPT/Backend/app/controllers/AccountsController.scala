/*
 * Copyright (c) 2016. Lazy Portfolio Tracker
 */

package controllers

import javax.inject.Inject

import models.db.DB
import models.db.Tables.{AccounttypeRow, QuoteRow}
import play.Logger
import play.api.cache.CacheApi
import play.api.libs.json.{Json, Writes}
import play.api.mvc.{Action, Controller}
import play.api.libs.concurrent.Execution.Implicits.defaultContext

import scala.util.Random

/**
  * Created by peter on 12/22/16.
  */
class AccountsController @Inject()(cache: CacheApi) extends Controller {

  val db = new DB(cache)


  implicit val accountTypeWrites = new Writes[AccounttypeRow] {
    def writes(accType: AccounttypeRow) = Json.obj(
      "id" -> accType.id,
      "countryid" -> accType.countryid,
      "description" -> accType.description,
      "name" -> accType.name,
      "url" -> accType.url
    )
  }



  def types = Action.async {
    db.getAccountTypes.map(i => {
      val json = Json.toJson(i)
      Logger.debug(Json.prettyPrint(json))
      Ok(json)
    })


  }

}
