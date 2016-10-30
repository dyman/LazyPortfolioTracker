/*
 * Copyright (c) 2016. Lazy Portfolio Tracker
 */

package controllers


import javax.inject.Inject

import models.db.DB
import models.db.Tables.QuoteRow
import play.Logger
import play.api.cache.CacheApi
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.libs.json.Writes
import play.api.mvc.{Action, Controller}

//import play.libs.Json

import scala.util.Random

//import play.api.libs.json.{JsNull, Json, JsString, JsValue}
import play.api.libs.json._

/**
  * Created by peter on 10/30/16.
  */
class RandomQuoteController @Inject()(cache: CacheApi) extends Controller {

  val db = new DB(cache)


  implicit val quoteWrites = new Writes[QuoteRow] {
    def writes(quote: QuoteRow) = Json.obj(
      //"id" -> quote.id,
      "quote" -> quote.quote,
      "author" -> quote.author
    )
  }


  def quote = Action.async {

    db.getCachesQuotes.map(i => {
      val json = Json.toJson(Random.shuffle(i.toList).head)
      Logger.debug(Json.prettyPrint(json))
      Ok(json)
    })

  }
}