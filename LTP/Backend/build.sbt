name := "play-scala"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  cache,
  ws,
  "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.0-RC1" % Test
)

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play-slick" % "2.0.0",
  "com.typesafe.play" %% "play-slick-evolutions" % "2.0.0"
)

libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1206-jdbc42"

libraryDependencies += "com.typesafe.slick" % "slick-codegen_2.11" % "3.1.1"

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

