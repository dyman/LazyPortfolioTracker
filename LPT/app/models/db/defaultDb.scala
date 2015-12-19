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
import models.db.Tables.RateRow
import models.db.Tables.Rate
import scala.util.Success
import scala.util.Failure

object defaultDb {

  def myDb = Database.forConfig("mydb")

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

  def loginAndSaveUser(email: String): Future[Int] = {
    Logger.debug("getting user: {}", email)
    val users = for (u <- User) yield u
    val u = users.filter { _.email === email }
    val t = myDb.run(u.result)
    var rt: Future[Int] = null
    t.onComplete({
      case Success(ret) => {
        val u = ret.headOption
        if (!u.isDefined) {
          val in = u.get
          Logger.debug("user found id is: {}", in.id.toString())
          val today = new java.sql.Timestamp(DateTime.now().getMillis)
          val userQueries: TableQuery[User] = TableQuery[User]
          val newIn: UserRow = new UserRow(in.id, email, today, Some(false))
          val updateAction: DBIO[Int] = userQueries.insertOrUpdate(newIn)
          Logger.debug(updateAction.toString())
          rt = Future {
            in.id
          }
        } else {
          Logger.debug("user not found, inserting")
          val today = new java.sql.Timestamp(DateTime.now().getMillis)
          val userQueries: TableQuery[User] = TableQuery[User]
          val in: UserRow = new UserRow(100, email, today, Some(false))
          val insertAction: DBIO[Int] = (userQueries returning userQueries.map(x => x.id)) += in
          rt = myDb.run(insertAction)
        }
      }
      case Failure(exception) => {
        rt = Future {
          0
        }
      }
    })

    rt

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

  def ratesIsEmpty() = {    
    val rates: TableQuery[Rate] = TableQuery[Rate] 
    !Await.result(myDb.run(rates.exists.result),Duration.Inf)    
  }
  
  def lastRateDate() = {
    val rates: TableQuery[Rate] = TableQuery[Rate]
    val q = rates.map { x => x.ondate }
    Await.result(myDb.run(q.max.result),Duration.Inf)
  }

  ////rates
  def saveRates(newRates: List[RateRow]) = {
    Logger.debug("saving rates: ...")/// + newRates.mkString(","))
    val insertQuery: TableQuery[Rate] = TableQuery[Rate]
    val insertAction = insertQuery ++= newRates //*insertQuery returning insertQuery.map(x => x.id))

    val t = myDb.run(insertAction)
    t.onSuccess {
      case ret => {
        Logger.debug("rateId: {}", ret.toString())
      }
    }
    t.onFailure {
      case ret => {
        Logger.debug("rateFailure: {}", ret.toString())
      }
    }
    t
  }

}