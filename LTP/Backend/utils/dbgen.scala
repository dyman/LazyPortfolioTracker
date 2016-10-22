/**
  * Created by peter on 10/21/16.
  */

import  slick._

println("asdf")
codegen.SourceCodeGenerator.main (Array ("slick.driver.PostgresDriver",
  "org.postgresql.Driver",
  "jdbc:postgresql://192.168.0.20:5432/LPT",
  "app/",
  "models.db", "FAS", "FAS") )

println("asdf")