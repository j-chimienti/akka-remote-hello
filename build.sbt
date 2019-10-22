

lazy val commonSettings = Seq(
  version := "0.1",
  scalaVersion := "2.11.8"
)

resolvers += "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/"

lazy val commonDeps =  Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.4.10",
  "com.typesafe.akka" %% "akka-remote" % "2.4.10"
)


lazy val root = (project in file("."))
    .settings(commonSettings)
    .aggregate(helloLocal, helloRemote)

lazy val helloLocal = (project in file("helloLocal"))
  .settings(commonSettings, name := "hello-local", libraryDependencies ++= commonDeps)

lazy val helloRemote = (project in file("helloRemote"))
  .settings(commonSettings, name := "hello-remote", libraryDependencies ++= commonDeps)