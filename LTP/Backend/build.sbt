import sbt.Keys._


val slickVersion = "3.1.1"


lazy val commonSettings = Seq(
  scalaVersion := "2.11.8",
  name := "play-scala",
  version := "1.0-SNAPSHOT"
)

val hello = TaskKey[Unit]("hello")
lazy val root = (project in file(".")).
  //settings(commonSettings: _*).
  settings(
    hello := { println("Hello!") }
  ).
  enablePlugins(PlayScala)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0-RC1" % Test
)

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "2.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0"
)

//libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1206-jdbc42"
libraryDependencies += "org.postgresql" % "postgresql" % "9.4.1211"


libraryDependencies += "com.typesafe.slick" % "slick-codegen_2.11" % slickVersion

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"



//val hello = TaskKey[Unit]("hello") := println("hello world!")


lazy val slick = TaskKey[Seq[File]]("gen-tables")

val sampleTask = TaskKey[Int]("sample-task")
sampleTask := {
  val sum = 1 + 2
  println("sum: " + sum)
  sum
}


lazy val slickGenerate = taskKey[Seq[File]]("slick code generation")

slickGenerate := {
  val dbName = "LPT"
  val userName = "FAS"
  val password = "FAS"
  val url = s"jdbc:postgresql://mandy:5432/LPT"
  val jdbcDriver = "org.postgresql.Driver"
  val slickDriver = "org.postgresql.Driver"
  val targetPackageName = "models.db"
  val outputDir = ((sourceManaged in Compile).value / dbName).getPath // place generated files in sbt's managed sources folder
  val fname = outputDir + s"/$targetPackageName/Tables.scala"
  println(s"\nauto-generating slick source for database schema at $url...")
  println(s"output source file file: file://$fname\n")
  (runner in Compile).value.run("scala.slick.codegen.SourceCodeGenerator", (dependencyClasspath in Compile).value.files, Array(slickDriver, jdbcDriver, url, outputDir, targetPackageName, userName, password), streams.value.log)
  Seq(file(fname))
}