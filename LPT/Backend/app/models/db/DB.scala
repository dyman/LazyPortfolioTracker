/*
 * Copyright (c) 2016. Lazy Portfolio Tracker
 */

package models.db

import models.db.Tables.{Accounttype, Quote}
import play.Logger
import play.api.cache._
import javax.inject.Inject

import slick.driver.PostgresDriver.api._

import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits.defaultContext

/**
  * Created by peter on 10/30/16.
  */

class DB @Inject()(cache: CacheApi) {
  def getAccountTypes: Future[Seq[Tables.AccounttypeRow]] = {

    Logger.debug("get account Types")
    if (cache.get("accountTypes").isEmpty) {
      try {
        Logger.debug("querying db")
        val acS = for (a <- Accounttype) yield a
        val f = DB.myDb.run(acS.result)
        f.onSuccess { case ret => cache.set("accountTypes", ret) }
        f
      }
      finally {


      }
    }
    else {
      Logger.debug("queyriing cache")
      Future {
        cache.get("accountTypes").asInstanceOf[Option[Seq[Tables.AccounttypeRow]]].get
      }
    }

  }


  def getCachesQuotes: Future[Seq[Tables.QuoteRow]] = {

    Logger.debug("get cached quotes")
    if (cache.get("quotes").isEmpty) {
      try {
        Logger.debug("querying db")
        val qus = for (q <- Quote) yield q

        val f = DB.myDb.run(qus.result)
        f.onSuccess { case ret => cache.set("quotes", ret) }
        f
      } finally {
      }
    } else {
      Logger.debug("querying cache")
      Future {
        cache.get("quotes").asInstanceOf[Option[Seq[Tables.QuoteRow]]].get
      }
    }

  }
}

object DB {
  val myDb = Database.forConfig("db")


  def getQuotes = {
    try {
      Logger.debug("get quotes")
      val qus = for (q <- Quote) yield q
      myDb.run(qus.result)
    } finally {
    }
  }


}
