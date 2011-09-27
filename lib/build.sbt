name := "zkscala"

version := "0.5"

organization := "me.timothyclare"

libraryDependencies ++= Seq(
    "org.zkoss.zk" % "zk" % "5.0.8"
)

resolvers += "ZK CE" at "http://mavensync.zkoss.org/maven2"