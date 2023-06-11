/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/rubberdoc-android/blob/main/LICENSE
 */

@file:Suppress("DSL_SCOPE_VIOLATION", "INLINE_FROM_HIGHER_PLATFORM")

plugins {
  rubberdoc("jvm-kotlin")
  // rubberdoc("rubberdoc-publishing")
  alias(libs.plugins.kotlin.ksp)
}

dependencies {
  compileOnly(libs.kotlin.embeddable.compiler)
  implementations(
    libs.google.autoservice.annotation,
    // projects.utilBackendKotlinc,
  )
  ksp(libs.google.autoservice.ksp.processor)
}
