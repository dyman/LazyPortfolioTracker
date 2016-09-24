package util

object ws {
def main(args: Array[String]): Unit = {
    println("util main program!")
    slick.codegen.SourceCodeGenerator.main(Array("slick.driver.PostgresDriver",
    "org.postgresql.Driver",
    "jdbc:postgresql://127.0.0.1:5432/FAS", 
    "app/",
    "models.db", "FAS", "FAS"))
  
  }
    
}
