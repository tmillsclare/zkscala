import sbt._
import Keys._

object BuildSettings {
	val buildOrganization = "zkoss"
	val buildVersion      = "0.7.5"

	val buildSettings = Defaults.defaultSettings ++ Seq (
		name := "zkscala-examples",
		organization := buildOrganization,
		version := buildVersion
  	)
}

object Resolvers {
	val zkcerepo = "ZK CE" at "http://mavensync.zkoss.org/maven2"
	val webPluginRepo = "Web plugin repo" at "http://siasia.github.com/maven2"
	val jettyRepo = "Jetty Repo" at "http://repository.codehaus.org"
}

object Dependencies {
	
	val zkversion = "5.0.8"

	val zkce = "org.zkoss.zk" % "zk" % zkversion
	val zkplus = "org.zkoss.zk" % "zkplus" % zkversion
	val zkzul = "org.zkoss.zk" % "zul" % zkversion
	val zkzhtml = "org.zkoss.zk" % "zhtml" % zkversion
	val zkzcommon = "org.zkoss.common" % "zcommon" % zkversion
	val zkzweb = "org.zkoss.common" % "zweb" % zkversion	
	val zkbreeze = "org.zkoss.theme" % "breeze" % zkversion
	val zkel = "org.zkoss.zkforge.el" % "zcommons-el" % "1.1.0" 

	val commonslogging = "commons-logging" % "commons-logging" % "1.1.1"
	val commonsfileupload = "commons-fileupload" % "commons-fileupload" % "1.2.1"
	val commonsio = "commons-io" % "commons-io" % "1.3.1"
	val commonslang = "commons-lang" % "commons-lang" % "2.4"

	val scalatest = "org.scalatest" % "scalatest_2.9.0" % "1.4.1" % "test"
	
	val webPluginDeps = Seq(
		"org.mortbay.jetty" % "jetty" % "6.1.22" % "jetty,test", 
		"org.eclipse.jetty" % "jetty-server" % "7.4.5.v20110725",// % "compile,jetty",
		"org.eclipse.jetty" % "jetty-servlet" % "7.4.5.v20110725",// % "compile,jetty",
		"javax.servlet" % "servlet-api" % "2.5" % "provided->default"
	)

	val zkDeps = Seq(
		zkce,
		zkplus,
		zkzul,
		zkzhtml,
		zkzcommon,
		zkzweb,
		zkbreeze,
		zkel
	)
	
	val commonsDep = Seq(
		commonslogging,
		commonsfileupload,
		commonsio,
		commonslang
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
			libraryDependencies ++= zkDeps ++ commonsDep ++ webPluginDeps
		) ++ webSettings
	) dependsOn(zkscala)

	lazy val zkscala = ProjectRef(
		uri("../lib"),
		"zkscala"
	)

	
}
