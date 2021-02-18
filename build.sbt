import sbtassembly.AssemblyKeys._
name := "SparkII"

version := "1.0"

scalaVersion := "2.12.13"

// https://mvnrepository.com/artifact/org.apache.spark/spark-sql
libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.0.1"
//libraryDependencies +="org.scala-sbt" %% "util-logging" % "1.3.0-M2"
// https://mvnrepository.com/artifact/org.plotly-scala/plotly-core
libraryDependencies += "org.plotly-scala" %% "plotly-core" % "0.8.1"
// SETTINGS
lazy val global = project
  .in(file("."))
  .aggregate(
    GetDataFrame,
    Sales2013,
    SalesDistribution,
    Sales2013MinusRefund,
    UserProducts
  )
lazy val GetDataFrame = project
  .settings(
    name := "GetDataFrame",
    libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.0.1"
  ).enablePlugins(AssemblyPlugin)

lazy val Sales2013 = project
  .settings(
    name := "Sales2013",
    libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.0.1"
  ).enablePlugins(AssemblyPlugin) dependsOn GetDataFrame

lazy val SalesDistribution = project
  .settings(
    name := "SalesDistribution",
    libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.0.1"
  ).enablePlugins(AssemblyPlugin) dependsOn(GetDataFrame)

lazy val Sales2013MinusRefund = project
  .settings(
    name := "Sales2013MinusRefund",
    libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.0.1"
  ).enablePlugins(AssemblyPlugin) dependsOn(GetDataFrame,Sales2013)

lazy val UserProducts = project
  .settings(
    name := "UserProducts",
    libraryDependencies += "org.apache.spark" %% "spark-sql" % "3.0.1"
  ).enablePlugins(AssemblyPlugin) dependsOn(GetDataFrame)