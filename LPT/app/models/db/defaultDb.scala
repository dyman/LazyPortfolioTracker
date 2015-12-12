package models.db
import slick.driver.PostgresDriver.api._
import models.db.Tables.Country
import play.Logger
import models.db.Tables.User
import models.db.Tables.Quote
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import models.db.Tables.QuoteRow
import play.cache.Cache
import scala.concurrent.Future
import models.db.Tables.Accounttype
import org.joda.time.DateTime
import models.db.Tables.UserRow
import models.db.Tables.UserRow
import scala.concurrent.Await
import scala.concurrent.duration.Duration

object defaultDb {

  def countryNames = {
    val db = Database.forConfig("mydb")
    try {
      Logger.debug("country names query")
      val countries = for (c <- Country) yield c
      db.run(countries.result)
    } finally {
      db.close()
    }
  }

  def accountTypes = {
    val db = Database.forConfig("mydb")
    try {
      Logger.debug("account types query")
      val accountTypes = for (a <- Accounttype) yield a
      db.run(accountTypes.result)
    } finally {
      db.close()
    }
  }

  def loginAndSaveUser(email: String): Unit = {
    val db = Database.forConfig("mydb")
    try {
      Logger.debug("getting user: {}", email)
      val users = for (u <- User) yield u
      val u = users.filter { _.email === email }
      db.run(u.result)
      val t = db.run(u.result)

      t.onSuccess {
        case ret => {
          if (ret.size > 0) {
            val in = ret.head
            Logger.debug("user found id is: {}", in.id.toString())
            val today = new java.sql.Timestamp(DateTime.now().getMillis)
            val userQueries: TableQuery[User] = TableQuery[User]
            val newIn: UserRow = new UserRow(in.id, email, today, Some(false))
            val updateAction: DBIO[Int] = userQueries.insertOrUpdate(newIn)
            Logger.debug(updateAction.toString())
            Future {
              in.id
            }
          } else if (ret.size == 0) {
            Logger.debug("user not found, inserting")
            val today = new java.sql.Timestamp(DateTime.now().getMillis)
            val userQueries: TableQuery[User] = TableQuery[User]
            val in: UserRow = new UserRow(100, email, today, Some(false))
            val insertAction: DBIO[Int] = (userQueries returning userQueries.map(x => x.id)) += in
            val result: Future[Int] = db.run(insertAction)
            result
          }
        }
      }

    } finally {
      //db.close()
    }
  }

  def getQuotes = {
    val db = Database.forConfig("mydb")
    try {
      Logger.debug("get quotes")
      val qus = for (q <- Quote) yield q
      db.run(qus.result)
    } finally {
      db.close()
    }
  }

  def getCachesQuotes: Future[Seq[Tables.QuoteRow]] = {

    Logger.debug("get cached quotes")
    if (Cache.get("quotes") == null) {
      val db = Database.forConfig("mydb")
      try {
        Logger.debug("querying db")
        val qus = for (q <- Quote) yield q
        val f = db.run(qus.result)
        f.onSuccess { case ret => Cache.set("quotes", ret) }
        f
      } finally {
        db.close()
      }
    } else {
      Logger.debug("querying cache")
      Future {
        Cache.get("quotes").asInstanceOf[Seq[Tables.QuoteRow]]
      }
    }

  }

}