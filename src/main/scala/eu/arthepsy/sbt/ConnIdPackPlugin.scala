/*
 * The MIT License
 * Copyright (c) 2017 Andris Raugulis (moo@arthepsy.eu)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package eu.arthepsy.sbt

import sbt._
import sbt.Keys._

object ConnIdPackPlugin extends AutoPlugin {
  override def requires = plugins.JvmPlugin
  override def trigger = allRequirements

  object autoImport {
    val ConnId: Configuration = config("connId").hide
    val connIdVersion: SettingKey[String] = settingKey[String]("ConnID version to use")
  }

  import autoImport._
  override def projectConfigurations: Seq[Configuration] = Seq(ConnId)
  override lazy val projectSettings: Seq[Def.Setting[_]] = inConfig(ConnId)(
    defaultSettings) ++ connIdSettings

  def defaultSettings = Seq(
    connIdVersion := "1.4.2.35"
  )

  def connIdSettings = Seq(
    resolvers ++= Seq(
      "Evolveum releases" at "https://nexus.evolveum.com/nexus/content/repositories/releases/",
      "Evolveum snapshots" at "https://nexus.evolveum.com/nexus/content/repositories/snapshots/"
    ),
    libraryDependencies ++= Seq(
      "net.tirasa.connid" % "connector-framework" % (connIdVersion in ConnId).value % Provided.name,
      "net.tirasa.connid" % "connector-framework-contract" % (connIdVersion in ConnId).value % Provided.name
    ),
    mappings in (Compile, packageBin) ++= {
      val libs = for {
        c <- update.value
          .filter(configurationFilter(name = Runtime.name))
          .configurations
        m <- c.modules if !m.evicted
        (a, f) <- m.artifacts
      } yield {
        (f, (file("lib") / f.name).toString)
      }
      libs
    },
    packageOptions in (Compile, packageBin) ++= Seq(
      Package.ManifestAttributes("ConnectorBundle-Name" -> s"${organizationName.value}.${name.value}"),
      Package.ManifestAttributes("ConnectorBundle-Version" -> version.value),
      Package.ManifestAttributes("ConnectorBundle-FrameworkVersion" -> (connIdVersion in ConnId).value)
    )
  )
}