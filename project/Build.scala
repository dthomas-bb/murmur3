import sbt._
import Keys._
import sbtrelease._
import ReleasePlugin.ReleaseKeys

object BuildSettings {
  lazy val releaseSettings = ReleasePlugin.releaseSettings ++ Seq(
    ReleaseKeys.versionBump := Version.Bump.Minor
  )

  lazy val buildSettings = Defaults.defaultSettings ++ Seq (
    organization := "org.apache.cassandra",
    name := "cassandra-murmur",
    autoScalaLibrary := false,
    crossPaths := false,
    publishArtifact in (Compile, packageDoc) := false,
    publishArtifact in (Compile, packageSrc) := false
  )

  lazy val standardSettings =
    buildSettings ++
    releaseSettings
}

object Dependencies {
  val jun = "net.ju-n.compile-command-annotations" % "compile-command-annotations" % "1.2.0"
}

object MurmurBuild extends Build {
  import BuildSettings._
  import Dependencies._

  val deps = Seq(jun)

  val root = Project(
    id = "murmur",
    base = file("."),
    settings = standardSettings ++ Seq(libraryDependencies ++= deps)
  )
}
