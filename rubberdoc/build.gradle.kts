/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/rubberdoc-android/blob/main/LICENSE
 */

plugins {
  rubberdoc("jvm-kotlin")
  // rubberdoc("rubberdoc-publishing")
  alias(libs.plugins.kotlin.ksp)
}

dependencies {
  compileOnly(libs.kotlin.embeddable.compiler)
  implementations(
    libs.google.autoservice.annotation,
    libs.quackquack.util.backend.kotlinc,
  )
  ksp(libs.google.autoservice.ksp.processor)
}
