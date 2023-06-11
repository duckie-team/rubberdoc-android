/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/rubberdoc-android/blob/main/LICENSE
 */

@file:Suppress("UnstableApiUsage")

import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost
import internal.applyPlugins
import internal.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.publish.maven.MavenPom
import org.gradle.kotlin.dsl.configure

private const val RepositoryName = "duckie-team/rubberdoc-android"

class RubberdocMavenPublishingPlugin : Plugin<Project> {
  override fun apply(project: Project) {
    with(project) {
      applyPlugins(
        libs.findPlugin("gradle-publish-maven-core").get().get().pluginId,
        libs.findPlugin("gradle-publish-maven-base").get().get().pluginId,
      )

      tasks.matching { task ->
        task.name.contains("signMavenPublication")
      }.configureEach {
        notCompatibleWithConfigurationCache("https://github.com/vanniktech/gradle-maven-publish-plugin/issues/259")
      }

      extensions.configure<MavenPublishBaseExtension> {
        publishToMavenCentral(host = SonatypeHost.S01, automaticRelease = true)
        signAllPublications()
        pom { configureMavenPom() }
      }
    }
  }
}

private fun MavenPom.configureMavenPom() {
  description.set("https://github.com/$RepositoryName")
  inceptionYear.set("2023")
  url.set("https://github.com/$RepositoryName")
  licenses {
    license {
      name.set("MIT License")
      url.set("https://github.com/$RepositoryName/blob/main/LICENSE")
    }
  }
  developers {
    developer {
      id.set("jisungbin")
      name.set("Ji Sungbin")
      url.set("https://sungb.in")
      email.set("ji@sungb.in")
    }
  }
  scm {
    url.set("https://github.com/$RepositoryName")
    connection.set("scm:git:github.com/$RepositoryName.git")
    developerConnection.set("scm:git:ssh://github.com/$RepositoryName.git")
  }
}
