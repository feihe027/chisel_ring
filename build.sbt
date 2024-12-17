scalaVersion := "2.12.15"  

scalacOptions ++= Seq(  
  "-deprecation",  
  "-feature",  
  "-unchecked",  
  "-Xfatal-warnings",  
  "-language:reflectiveCalls",  
)  

val chiselVersion = "3.5.3"  
addCompilerPlugin("edu.berkeley.cs" %% "chisel3-plugin" % chiselVersion cross CrossVersion.full)  
libraryDependencies += "edu.berkeley.cs" %% "chisel3" % chiselVersion  
libraryDependencies += "edu.berkeley.cs" %% "chiseltest" % "0.5.3"  // 与 Chisel 3.5.3 兼容的版本  
libraryDependencies += "org.slf4j" % "slf4j-api" % "1.7.36"  // 兼容 Scala 2.12 的版本  
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.11"  // 兼容 Scala 2.12 的版本  
libraryDependencies += "edu.berkeley.cs" %% "dsptools" % "1.4.3"  // 对应 Scala 2.12 的版本