import sbt._
import Keys._

object BuildSettings {
	val buildOrganization = "zkoss"
	val buildVersion      = "0.6"

	val buildSettings = Defaults.defaultSettings ++ Seq (
		name := "zkscala-examples",
		organization := buildOrganization,
		version := buildVersion
  	)
}

object Resolvers {
	val zkcerepo = "ZK CE" at "http://mavensync.zkoss.org/maven2"
}

object Dependencies {
	val zkce = "org.zkoss.zk" % "zk" % "5.0.8" 
	val scalatest = "org.scalatest" % "scalatest_2.9.0" % "1.4.1" % "test"
}

object ZKScalaBuild extends Build {
	import BuildSettings._
	import Resolvers._
	import Dependencies._
	
	lazy val root = Project(
		"zkscala-examples",
		file("."),
		settings = buildSettings ++ Seq(
			resolvers := Seq(zkcerepo),
			libraryDependencies ++= Seq(zkce)
		)
	) dependsOn(zkscala)

	lazy val zkscala = ProjectRef(
		uri("../lib"),
		"zkscala"
	)

	
}
