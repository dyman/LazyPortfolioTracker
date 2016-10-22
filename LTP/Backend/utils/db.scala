/**
  * Created by peter on 10/21/16.
  */

package utils

class db {
  def run(): {
    println ("util main program!")
    slick.codegen.SourceCodeGenerator.main (Array ("slick.driver.PostgresDriver",
    "org.postgresql.Driver",
    "jdbc:postgresql://mandy:5432/FAS",
    "app/",
    "models.db", "FAS", "FAS") )

  }
}
