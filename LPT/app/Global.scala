
import java.sql.Date
import java.util.concurrent.TimeUnit

import scala.collection.mutable.ListBuffer
import scala.concurrent.duration.Duration
import scala.xml.XML

import akka.actor.Actor
import models.db.Tables.RateRow
import models.db.defaultDb
import play.api.Application
import play.api.GlobalSettings
import play.api.Logger
import play.api.libs.concurrent.Execution.Implicits
import play.libs.Akka

object Global extends GlobalSettings {
  override def onStart(app: Application) {
    Logger.info("Application has started")
    import play.api.libs.concurrent.Execution.Implicits._
    Akka.system.scheduler.schedule(Duration(2, TimeUnit.SECONDS), Duration(1, TimeUnit.DAYS))({

      if (defaultDb.ratesIsEmpty()) {
        val url = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml"
        Logger.debug("rate db is empty saving last 90 days: " +  url)
        defaultDb.saveRates(getRates(url).toList)
      }
      else{
        Logger.debug("rate db is not empty")
      }

      Logger.debug("tick");

    })

  }

  def getRates(url: String) = {
    val list = ListBuffer.empty[RateRow]
    val xml = XML.load(url)
    val time = (xml \ "Cube" \ "Cube")(0) \ "@time"
    println(time)
    val rates = (xml \ "Cube" \ "Cube")(0)
    //println(rates)
    for (elem <- rates.child) {
      //println(elem) 
      val rate = elem \ "@rate"
      val currency = elem \ "@currency"

      if (!"".equals(rate.text)) {
        val newRate: RateRow = new RateRow(1, Some(currency.text), Some(Date.valueOf(time.text)), Some(java.lang.Double.parseDouble(rate.text)))
        list += newRate
        //
      }

    }
    list
  }

  override def onStop(app: Application) {
    Logger.info("Application shutdown...")
  }
}

class CurrencyActor() extends Actor {
  def receive: Actor.Receive = {
    ???
  }
}