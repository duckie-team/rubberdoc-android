/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/rubberdoc-android/blob/main/LICENSE
 */

@file:Suppress("UnstableApiUsage")

rootProject.name = "rubberdoc-android"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
enableFeaturePreview("STABLE_CONFIGURATION_CACHE")

pluginManagement {
  repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
  }

  includeBuild("build-logic")
}

buildCache {
  local {
    removeUnusedEntriesAfterDays = 7
  }
}

include(
  "rubberdoc",
  "rubberdoc-json",
  "rubberdoc-material",
  "utils",
)
