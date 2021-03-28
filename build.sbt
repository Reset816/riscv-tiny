// See README.md for license details.

ThisBuild / scalaVersion     := "2.12.13"
ThisBuild / version          := "1.0.0"
ThisBuild / organization     := ""
ThisBuild / transitiveClassifiers := Seq(Artifact.SourceClassifier)

lazy val root = (project in file("."))
  .settings(
    name := "riscv-tiny",
    libraryDependencies ++= Seq(
      "edu.berkeley.cs" %% "chisel3" % "3.4.+",
      "org.easysoc" %% "layered-firrtl" % "1.0.6",
      "edu.berkeley.cs" %% "chiseltest" % "0.3.+" % "test",
      "edu.berkeley.cs" %% "chisel-iotesters" % "1.5.2"
    ),
    scalacOptions ++= Seq(
      "-Xsource:2.11",
      "-language:reflectiveCalls",
      "-deprecation",
      "-feature",
      "-Xcheckinit"
    ),
    addCompilerPlugin("edu.berkeley.cs" % "chisel3-plugin" % "3.4.+" cross CrossVersion.full),
    addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.1" cross CrossVersion.full)
  )


