package models.utils

object ws {

  slick.codegen.SourceCodeGenerator.main(Array("slick.driver.PostgresDriver",
    "org.postgresql.Driver",
    "jdbc:postgresql://127.0.0.1:5432/FAS", 
    "programming/LazyPortfolioTracker/LPT/app/",
    "models.db", "FAS", "FAS"))
    
}