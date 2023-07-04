/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/rubberdoc-android/blob/main/LICENSE
 */

@file:Suppress("DSL_SCOPE_VIOLATION", "UnstableApiUsage", "Since15")

import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jlleitschuh.gradle.ktlint.KtlintExtension

plugins {
  alias(libs.plugins.kotlin.detekt)
  alias(libs.plugins.kotlin.ktlint)
  alias(libs.plugins.kotlin.dokka)
  alias(libs.plugins.gradle.dependency.graph)
  alias(libs.plugins.gradle.dependency.handler.extensions)
  idea
}

tasks.dokkaHtmlMultiModule {
  outputDirectory.set(file("website/static/api"))
}

// TODO
/*
private val quackquackColor = "#36bcf5"
private val projectDependencyInfoMap = mapOf(
  "casa" to DependencyInfo(color = "#D4C5F9"),
  "catalog" to DependencyInfo(color = "#8ED610", isBoxShape = true),
  "aide" to DependencyInfo(color = "#98E1CF"),
  "sugar" to DependencyInfo(color = "#BFD4F2"),
  "runtime" to DependencyInfo(color = quackquackColor),
  "material" to DependencyInfo(color = quackquackColor),
  "animation" to DependencyInfo(color = quackquackColor),
  "ui" to DependencyInfo(color = quackquackColor),
)

dependencyGraphConfig {
  dotFilePath = "assets/project-dependency-graph.dot"
  outputFormat = OutputFormat.SVG

  dependencyBuilder { project ->
    val projectSimpleName = project.name.split("-").first()
    projectDependencyInfoMap[projectSimpleName]
  }
}
*/

@Suppress("ktlint")
tasks.matching { task ->
  task.name.contains("dependencyGraph")
}.configureEach {
  notCompatibleWithConfigurationCache("https://github.com/jisungbin/dependency-graph-plugin/issues/8")
}

idea {
  module {
    excludeDirs = excludeDirs + file("website")
  }
}

buildscript {
  repositories {
    google()
    mavenCentral()
  }

  dependencies {
    classpath(libs.kotlin.dokka)
    classpath(libs.kotlin.gradle)
  }
}

allprojects {
  val nospotless = File(projectDir, ".nospotless").exists()

  repositories {
    google()
    mavenCentral()
  }

  apply {
    plugin(rootProject.libs.plugins.kotlin.dokka.get().pluginId)
    plugin(rootProject.libs.plugins.gradle.dependency.handler.extensions.get().pluginId)
    if (!nospotless) {
      plugin(rootProject.libs.plugins.kotlin.detekt.get().pluginId)
      plugin(rootProject.libs.plugins.kotlin.ktlint.get().pluginId)
    }
  }

  afterEvaluate {
    if (pluginManager.hasPlugin(rootProject.libs.plugins.kotlin.detekt.get().pluginId)) {
      extensions.configure<DetektExtension> {
        parallel = true
        buildUponDefaultConfig = true
        toolVersion = libs.versions.kotlin.detekt.get()
        config.setFrom(files("$rootDir/detekt-config.yml"))
      }
    }

    if (pluginManager.hasPlugin(rootProject.libs.plugins.kotlin.ktlint.get().pluginId)) {
      extensions.configure<KtlintExtension> {
        version.set(rootProject.libs.versions.kotlin.ktlint.source.get())
        android.set(true)
        verbose.set(true)
      }
    }

    tasks.withType<KotlinCompile> {
      kotlinOptions {
        freeCompilerArgs = freeCompilerArgs + listOf(
          "-opt-in=kotlin.OptIn",
          "-opt-in=kotlin.RequiresOptIn",
        )
      }
    }
  }
}

tasks.register("cleanAll", type = Delete::class) {
  allprojects.map(Project::getBuildDir).forEach(::delete)
}
