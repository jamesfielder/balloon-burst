import Dependencies._

ThisBuild / scalaVersion     := "2.13.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.github.jamesfielder"
ThisBuild / organizationName := "jamesfielder"

lazy val root = (project in file("."))
  .settings(
    name := "Balloon Burst",
    libraryDependencies += scalaTest % Test
  )

enablePlugins(JavaAppPackaging)
// See https://www.scala-sbt.org/1.x/docs/Using-Sonatype.html for instructions on how to publish to Sonatype.
