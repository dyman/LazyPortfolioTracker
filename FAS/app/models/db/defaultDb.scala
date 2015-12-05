package models.db
import slick.driver.PostgresDriver.api._
import models.db.Tables.Country
import play.Logger

object defaultDb {
  val db = Database.forConfig("mydb")

  def countryNames = {
    Logger.debug("querying country names")
    val countries = for (c <- Country) yield c
    db.run(countries.result)
  }
}