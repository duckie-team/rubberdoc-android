/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/rubberdoc-android/blob/main/LICENSE
 */

// @formatter:off

@file:Suppress("UnstableApiUsage","unused")

import internal.ApplicationConstants
import internal.applyPlugins
import internal.libs
import internal.setupJunit
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.SourceSetContainer
import org.gradle.api.tasks.testing.AbstractTestTask
import org.gradle.api.tasks.testing.Test
import org.gradle.api.tasks.testing.TestResult
import org.gradle.api.tasks.testing.TestDescriptor
import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.withType
import org.gradle.kotlin.dsl.KotlinClosure2
import org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

private const val EXPLICIT_API = "-Xexplicit-api=strict"

internal abstract class BuildLogicPlugin(private val block: Project.() -> Unit) : Plugin<Project> {
  final override fun apply(project: Project) {
    with(project, block = block)
  }
}

internal class JvmKotlinPlugin : BuildLogicPlugin({
  applyPlugins(Plugins.JavaLibrary, Plugins.KotlinJvm)

  extensions.configure<KotlinProjectExtension> {
    jvmToolchain(ApplicationConstants.JavaVersionAsInt)
  }

  extensions.configure<SourceSetContainer> {
    getByName("main").java.srcDir("src/main/kotlin")
    getByName("test").java.srcDir("src/test/kotlin")
  }

  dependencies.add("detektPlugins", libs.findLibrary("detekt-plugin-formatting").get())
})

internal class TestJUnitPlugin : BuildLogicPlugin({
  useTestPlatformForTarget()
  dependencies {
    setupJunit(
      core = libs.findLibrary("test-junit-core").get(),
      engine = libs.findLibrary("test-junit-engine").get(),
    )
  }
})

internal class TestKotestPlugin : BuildLogicPlugin({
  useTestPlatformForTarget()
  dependencies.add("testImplementation", libs.findLibrary("test-kotest-framework").get())
})

internal class KotlinExplicitApiPlugin : BuildLogicPlugin({
  tasks
    .matching { task ->
      task is KotlinCompile && !task.name.contains("test", ignoreCase = true)
    }
    .configureEach {
      if (!project.hasProperty("kotlin.optOutExplicitApi")) {
        val kotlinCompile = this as KotlinCompile
        if (EXPLICIT_API !in kotlinCompile.kotlinOptions.freeCompilerArgs) {
          kotlinCompile.kotlinOptions.freeCompilerArgs += EXPLICIT_API
        }
      }
    }
})

// ref: https://kotest.io/docs/quickstart#test-framework
private fun Project.useTestPlatformForTarget() {
  fun AbstractTestTask.setTestConfiguration() {
    // https://stackoverflow.com/a/36178581/14299073
    outputs.upToDateWhen { false }
    testLogging {
      events = setOf(
        TestLogEvent.PASSED,
        TestLogEvent.SKIPPED,
        TestLogEvent.FAILED,
      )
    }
    afterSuite(
      KotlinClosure2<TestDescriptor, TestResult, Unit>({ desc, result ->
        if (desc.parent == null) { // will match the outermost suite
          val output = "Results: ${result.resultType} " +
            "(${result.testCount} tests, " +
            "${result.successfulTestCount} passed, " +
            "${result.failedTestCount} failed, " +
            "${result.skippedTestCount} skipped)"
          println(output)
        }
      })
    )
  }

  tasks.withType<Test>().configureEach {
    useJUnitPlatform()
    setTestConfiguration()
  }
}
