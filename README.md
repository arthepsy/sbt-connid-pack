# sbt-connid-pack

[![Build Status](https://travis-ci.org/arthepsy/sbt-connid-pack.svg)](https://travis-ci.org/arthepsy/sbt-connid-pack)
[![Codacy Badge](https://api.codacy.com/project/badge/Grade/67ae6b7de7434fef85e8f8b12558aff7)](https://www.codacy.com/app/arthepsy/sbt-connid-pack)
[![Download](https://api.bintray.com/packages/arthepsy/sbt-plugins/sbt-connid-pack/images/download.svg)](https://bintray.com/arthepsy/sbt-plugins/sbt-connid-pack/_latestVersion)

sbt plugin for packaging [ConnId](https://github.com/Tirasa/ConnId) (Connectors for Identity Management) framework connector, for example, can be used to package connectors for [midPoint](https://evolveum.com/midpoint) identity management platform.

## installation

Add the following line to `project/plugins.sbt`:

``` scala
addSbtPlugin("eu.arthepsy.sbt" %% "sbt-connid-pack" % "1.0.0")
```

This plugin is released both for sbt 0.13 and sbt 1.0.

## usage
To enable the plugin:

``` scala
enablePlugins(ConnIdPackPlugin)
```

To specify particular connector framework version (default: **1.4.2.35**):

``` scala
connIdVersion in ConnId := "1.4.2.18"
```

Now just create a package as You normally would and the resulting package will contain required dependencies and meta-information.
