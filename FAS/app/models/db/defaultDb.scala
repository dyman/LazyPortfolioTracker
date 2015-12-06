package models.db
import slick.driver.PostgresDriver.api._
import models.db.Tables.Country
import play.Logger
import models.db.Tables.Accounttypes
import models.db.Tables.User

object defaultDb {
  val db = Database.forConfig("mydb")

  def countryNames = {
    Logger.debug("country names query")
    val countries = for (c <- Country) yield c
    db.run(countries.result)
  }

  def accountTypes = {
    Logger.debug("account types query")
    val accountTypes = for (a <- Accounttypes) yield a
    db.run(accountTypes.result)
  }

  def getUser(email: String) = {
    Logger.debug("get user: {}", email)
    //val users = TableQuery[User]
    val users = for(u <- User) yield u
    val u = users.filter { _.email === email }
    db.run(u.result)
  }
}