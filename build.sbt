lazy val root = (project in file("."))
  .enablePlugins(GitVersioning)
  .settings(
    organization := "eu.arthepsy.sbt",
    name := "sbt-connid-pack",
    description := "sbt plugin for packaging ConnId framework connector",

    sbtPlugin := true,
    crossSbtVersions := Seq("1.0.0", "0.13.16"),

    git.useGitDescribe := true,
    git.uncommittedSignifier := None,

    bintrayOrganization := None,
    bintrayRepository := "sbt-plugins",
    publishMavenStyle := false,

    PgpKeys.useGpg in Global := true,
    PgpKeys.gpgCommand in Global := "gpg2",

    headerLicense := Some(HeaderLicense.Custom(IO.read(baseDirectory.value / "LICENSE.md"))),
    licenses := Seq("MIT" -> url("https://opensource.org/licenses/MIT"))
  )
