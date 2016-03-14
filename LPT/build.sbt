name := """LPT"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"
//jdbc,
libraryDependencies ++= Seq(  
  cache,
  ws,
  specs2 % Test
)

//slick
libraryDependencies += "com.typesafe.play" %% "play-slick" % "1.1.1"
libraryDependencies += "com.typesafe.play" %% "play-slick-evolutions" % "1.1.1"
//libraryDependencies += "com.typesafe.play" % "play-slick_2.11" % "2.0.0"
//libraryDependencies += "com.typesafe.play" % "play-slick-evolutions_2.11" % "2.0.0"

libraryDependencies += "org.postgresql" % "postgresql" % "9.4-1206-jdbc42"

libraryDependencies += "com.typesafe.slick" % "slick-codegen_2.11" % "3.1.1"



// pac4j auth
libraryDependencies += "org.pac4j" % "play-pac4j-scala_2.11" % "2.0.0"

//webjars
libraryDependencies += "org.webjars" % "webjars-play_2.11" % "2.5.0"
libraryDependencies += "org.webjars" % "bootstrap" % "3.3.6"
libraryDependencies += "org.webjars" % "font-awesome" % "4.5.0"

libraryDependencies += "org.webjars" % "angularjs" % "1.5.0"
libraryDependencies += "org.webjars" % "angular-ui-bootstrap" % "1.2.1"

//xeditable
libraryDependencies += "org.webjars" % "angular-xeditable" % "0.1.9"


//netty
//libraryDependencies += "com.typesafe.play" % "play-netty-server_2.11" % "2.5.0"


//mailer plugin
libraryDependencies += "com.typesafe.play" % "play-mailer_2.11" % "4.0.0-M1"



libraryDependencies ++= Seq(
  "org.pac4j" % "pac4j-http" % "1.8.7",
  "org.pac4j" % "pac4j-openid" % "1.8.7",
  "org.pac4j" % "pac4j-oauth" % "1.8.7",
  "org.pac4j" % "pac4j-oidc" % "1.8.7"  
)


resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"




// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator


EclipseKeys.withSource := true

