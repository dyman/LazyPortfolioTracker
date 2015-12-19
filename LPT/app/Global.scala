
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
        val url = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist.xml"
        Logger.debug("rate db is empty saving history: " + url)
        defaultDb.saveRates(getRates(url).toList)
      } else {
        val lastDate = defaultDb.lastRateDate()
        val url = "https://www.ecb.europa.eu/stats/eurofxref/eurofxref-hist-90d.xml"
        Logger.debug("rate db is not empty, last data from: " + lastDate.toString())
        Logger.debug("synchronizing with the last 90 days")
        defaultDb.saveRates(getRates(url, lastDate).toList)

      }

      Logger.debug("tick");

    })

  }

  def getRates(url: String, lastDate: Option[Date] = None) = {
    val list = ListBuffer.empty[RateRow]
    val xml = XML.load(url)

    for (elem <- xml.child.filter { x => x.label.equals("Cube") }) {
      for (cube <- elem.child) {
        val time = cube \ "@time"
        if (!"".equals(time.text)) {
          val t = Date.valueOf(time.text)
          if (!lastDate.isDefined || t.after(lastDate.get))
            for (e <- cube.child) {
              val rate = e \ "@rate"
              val currency = e \ "@currency"
              if (!"".equals(rate.text)) {
                val newRate: RateRow = new RateRow(1, Some(currency.text), Some(t), Some(java.lang.Double.parseDouble(rate.text)))
                list += newRate
              }
            }
        }
      }
    }
    if (lastDate.isDefined) {
      Logger.debug("new rates from: " + lastDate.get + " " + list.mkString(","))
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