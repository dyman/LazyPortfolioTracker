name := """FAS"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.7"
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

libraryDependencies += "org.pac4j" % "play-pac4j-scala_2.11" % "2.0.0"


libraryDependencies ++= Seq(
  "org.pac4j" % "pac4j-http" % "1.8.0",
  "org.pac4j" % "pac4j-cas" % "1.8.0",
  "org.pac4j" % "pac4j-openid" % "1.8.0",
  "org.pac4j" % "pac4j-oauth" % "1.8.0",
  "org.pac4j" % "pac4j-saml" % "1.8.0",
  "org.pac4j" % "pac4j-oidc" % "1.8.0",
  "org.pac4j" % "pac4j-gae" % "1.8.0",
  "org.pac4j" % "pac4j-jwt" % "1.8.0",
  "org.pac4j" % "pac4j-ldap" % "1.8.0",
  "org.pac4j" % "pac4j-sql" % "1.8.0",
  "org.pac4j" % "pac4j-mongo" % "1.8.0",
  "org.pac4j" % "pac4j-stormpath" % "1.8.0"
)



resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

