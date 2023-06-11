/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/rubberdoc-android/blob/main/LICENSE
 */

@file:Suppress("DSL_SCOPE_VIOLATION", "INLINE_FROM_HIGHER_PLATFORM")

plugins {
  `kotlin-dsl`
  alias(libs.plugins.kotlin.jvm)
  alias(libs.plugins.gradle.dependency.handler.extensions)
}

gradlePlugin {
  val pluginClasses = listOf(
    "JvmKotlinPlugin" to "jvm-kotlin",
    "TestJUnitPlugin" to "test-junit",
    "TestKotestPlugin" to "test-kotest",
    "KotlinExplicitApiPlugin" to "kotlin-explicit-api",
    "RubberdocMavenPublishingPlugin" to "rubberdoc-publishing",
  )

  plugins {
    pluginClasses.forEach { pluginClass ->
      pluginRegister(pluginClass)
    }
  }
}

repositories {
  google()
  mavenCentral()
  gradlePluginPortal()
}

kotlin {
  jvmToolchain(17)
}

sourceSets {
  getByName("main").java.srcDir("src/main/kotlin")
}

dependencies {
  implementations(libs.kotlin.gradle, libs.gradle.publish.maven)
}

// Pair<ClassName, PluginName>
fun NamedDomainObjectContainer<PluginDeclaration>.pluginRegister(data: Pair<String, String>) {
  val (className, pluginName) = data
  register(pluginName) {
    implementationClass = className
    id = "rubberdoc.plugin.$pluginName"
  }
}
