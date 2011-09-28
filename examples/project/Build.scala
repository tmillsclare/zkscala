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
	val webPluginRepo = "Web plugin repo" at "http://siasia.github.com/maven2"
	val jettyRepo = "Jetty Repo" at "http://repo1.maven.org/maven2/org/mortbay/jetty"
}

object Dependencies {
	val zkce = "org.zkoss.zk" % "zk" % "5.0.8" 
	val scalatest = "org.scalatest" % "scalatest_2.9.0" % "1.4.1" % "test"
	
	val webPluginDeps = Seq(
	    "org.mortbay.jetty" % "jetty" % "6.1.26" % "jetty", // The last part is "jetty" not "test".
	    "javax.servlet" % "servlet-api" % "2.5" % "provided->default"
	)
}

object ZKScalaBuild extends Build {
	import BuildSettings._
	import Resolvers._
	import Dependencies._
	import com.github.siasia.WebPlugin._	
	
	lazy val root = Project(
		"zkscala-examples",
		file("."),
		settings = buildSettings ++ Seq(
			resolvers := Seq(zkcerepo),
			libraryDependencies ++= Seq(zkce) ++ webPluginDeps
		) ++ webSettings
	) dependsOn(zkscala)

	lazy val zkscala = ProjectRef(
		uri("../lib"),
		"zkscala"
	)

	
}
