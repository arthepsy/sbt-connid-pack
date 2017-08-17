lazy val root = (project in file("."))
  .settings(
    organizationName := "eu.arthepsy.sbt",
    name := "sbt-connid-pack",
    version := "1.0.0-SNAPSHOT",

    sbtPlugin := true,
    crossSbtVersions := Seq("1.0.0", "0.13.16"),

    headerLicense := Some(HeaderLicense.Custom(IO.read(baseDirectory.value / "LICENSE.md"))),
    licenses := Seq("MIT" -> url("https://opensource.org/licenses/MIT"))
  )