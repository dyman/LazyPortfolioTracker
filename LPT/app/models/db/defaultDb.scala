package models.db
import slick.driver.PostgresDriver.api._
import models.db.Tables.Country
import play.Logger
import models.db.Tables.User
import models.db.Tables.Quotes
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import models.db.Tables.QuotesRow
import play.cache.Cache
import scala.concurrent.Future
import models.db.Tables.Accounttype
import org.joda.time.DateTime

object defaultDb {
  val db = Database.forConfig("mydb")

  def countryNames = {
    Logger.debug("country names query")
    val countries = for (c <- Country) yield c
    db.run(countries.result)
  }

  def accountTypes = {
    Logger.debug("account types query")
    val accountTypes = for (a <- Accounttype) yield a
    db.run(accountTypes.result)
  }

  def getUser(email: String) = {
    Logger.debug("get user: {}", email)
    //val users = TableQuery[User]
    val users = for (u <- User) yield u
    val u = users.filter { _.email === email }
    db.run(u.result)
//    val t = db.run(u.result)
//    val today = DateTime.now()
//    t.onSuccess {
//      case ret => {
//        if (ret.size == 1) ret else users +=
//          (None,
//            email,
//            new java.sql.Date(today.year().get, today.monthOfYear().get(), today.dayOfMonth().get()),
//            false,
//            1)
//      }
//    }

  }

  def getQuotes = {
    Logger.debug("get quotes")
    val qus = for (q <- Quotes) yield q
    val f = db.run(qus.result)
    f
  }

  def getCachesQuotes: Future[Seq[Tables.QuotesRow]] = {
    Logger.debug("get cached quotes")
    if (Cache.get("quotes") == null) {
      Logger.debug("querying db")
      val qus = for (q <- Quotes) yield q
      val f = db.run(qus.result)
      f.onSuccess { case ret => Cache.set("quotes", ret) }
      f
    } else {
      Logger.debug("querying cache")
      Future {
        Cache.get("quotes").asInstanceOf[Seq[Tables.QuotesRow]]
      }
    }

  }

//  def findOrSaveUser(): Int = {
//
//  }
}