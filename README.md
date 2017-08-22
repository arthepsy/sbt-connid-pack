# sbt-connid-pack
sbt plugin for packaging [ConnId](https://github.com/Tirasa/ConnId) (Connectors for Identity Management) framework connector, for example, can be used to package connectors for [midPoint](https://evolveum.com/midpoint) identity management platform.

## installation

Add the following line to `project/plugins.sbt`:

``` scala
addSbtPlugin("eu.arthepsy.sbt" %% "sbt-connid-pack" % "1.0.0")
```

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
