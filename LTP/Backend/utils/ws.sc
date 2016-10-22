import slick._

println("alma")

println ("util main program!")


codegen.SourceCodeGenerator.main (Array ("slick.driver.PostgresDriver",
  "org.postgresql.Driver",
  "jdbc:postgresql://192.168.0.20:5432/LPT",
  "",
  "models.db", "FAS", "FAS") )
