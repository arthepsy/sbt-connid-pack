scapegoatVersion in ThisBuild := "1.3.1"

val scapegoatAvailable = Def.setting {
  val versions = "sbt " + sbtVersion.value +
    ", cross-sbt " + (sbtVersion in pluginCrossBuild).value +
    ", Scala " + scalaVersion.value
  CrossVersion.partialVersion((sbtVersion in pluginCrossBuild).value) match {
    case Some((1, 0)) => (true, versions)
    case _            => (false, versions)
  }
}

libraryDependencies --= Seq(
  "com.sksamuel.scapegoat" % "scalac-scapegoat-plugin_2.10" % scapegoatVersion.value % "compile"
)

libraryDependencies ++= {
  if (scapegoatAvailable.value._1) {
    Seq(
      "com.sksamuel.scapegoat" % ("scalac-scapegoat-plugin" + "_" + scalaBinaryVersion.value) % scapegoatVersion.value % "compile"
    )
  } else {
    Seq.empty
  }
}

scapegoat := Def.taskDyn {
  val default = scapegoat.taskValue
  if (scapegoatAvailable.value._1) Def.task(default.value)
  else
    Def.task {
      val log = streams.value.log
      log.warn(s"scapegoat not supported (${scapegoatAvailable.value._2})")
    }
}.value
