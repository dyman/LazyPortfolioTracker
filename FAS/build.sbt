name := """FAS"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"
//jdbc,
libraryDependencies ++= Seq(  
  cache,
  ws,
  specs2 % Test
)
//libraryDependencies += evolutions
libraryDependencies += "com.typesafe.play" %% "play-slick" % "1.1.1"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "1.1.1"

libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1206-jdbc42"
libraryDependencies += "com.typesafe.slick" %% "slick-codegen" % "3.1.0"



resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

