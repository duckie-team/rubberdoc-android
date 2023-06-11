/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/rubberdoc-android/blob/main/LICENSE
 */

import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

@Suppress("NOTHING_TO_INLINE")
inline fun PluginDependenciesSpec.rubberdoc(pluginName: String): PluginDependencySpec =
  id("rubberdoc.plugin.$pluginName")

