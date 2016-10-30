import slick._

println ("util main program!")


codegen.SourceCodeGenerator.main (Array ("slick.driver.PostgresDriver",
  "org.postgresql.Driver",
  "jdbc:postgresql://mandy:5432/LPT",
  "/home/peter/Programming/LazyPortfolioTracker/LTP/Backend/app/",
  "models.db", "FAS", "FAS") )
