import Dependencies._

lazy val root = (project in file(".")).
  enablePlugins(ScalaJSPlugin).
  settings(
    inThisBuild(List(
      organization := "io.longyang",
      scalaVersion := "2.12.1",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "Hello",
    libraryDependencies += scalaTest % Test,
    libraryDependencies += "com.thoughtworks.binding" %%% "dom" % "latest.release",
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)
  )
