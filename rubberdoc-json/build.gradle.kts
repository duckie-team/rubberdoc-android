/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/rubberdoc-android/blob/main/LICENSE
 */

plugins {
  rubberdoc("jvm-kotlin")
  rubberdoc("kotlin-explicit-api")
  rubberdoc("test-kotest")
  // rubberdoc("rubberdoc-publishing")
}

dependencies {
  implementations(
    libs.jackson.databind,
    projects.rubberdocMaterial,
    projects.utils,
  )
}