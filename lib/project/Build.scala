import sbt._
import Keys._

object BuildSettings {
	val buildOrganization = "zkoss"
	val buildVersion      = "0.6"

	val buildSettings = Defaults.defaultSettings ++ Seq (
		name := "zkscala",
		organization := buildOrganization,
		version := buildVersion
  	)
}

object Resolvers {
	val zkcerepo = "ZK CE" at "http://mavensync.zkoss.org/maven2"
}

object Dependencies {

	val zkversion = "5.0.8"

	val zkce = "org.zkoss.zk" % "zk" % zkversion
	val zkzul = "org.zkoss.zk" % "zul" % zkversion
	val zkplus = "org.zkoss.zk" % "zkplus" % zkversion

	val scalatest = "org.scalatest" % "scalatest_2.9.0" % "1.4.1" % "test"

	val zkDeps = Seq(
		zkce,
		zkzul,
		zkplus
	)
}

object ZKScalaBuild extends Build {
	import BuildSettings._
	import Resolvers._
	import Dependencies._

	lazy val zkscala = Project(
		"zkscala",
		file("."),
		settings = buildSettings ++ Seq(
			resolvers := Seq(zkcerepo),
			libraryDependencies ++= zkDeps
		)
	)
}
