name := "AvroConnector"

version := "0.1"

scalaVersion := "2.11.12"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.4.3",
  "org.apache.spark" %% "spark-sql" % "2.4.3",
  "org.json" % "json" % "20190722",
  "com.griddynamics" % "dq-common" % "1.1" from "file://{PATH_TO_COMMON_MODULE}/dq-common.jar"
)