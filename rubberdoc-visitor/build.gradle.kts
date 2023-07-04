/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/rubberdoc-android/blob/main/LICENSE
 */

plugins {
  rubberdoc("jvm-kotlin")
  rubberdoc("kotlin-explicit-api")
  // rubberdoc("rubberdoc-publishing")
}

dependencies {
  implementations(
    libs.kotlin.embeddable.compiler,
    libs.quackquack.util.backend.kotlinc,
  )
}
