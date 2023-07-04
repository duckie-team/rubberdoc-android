/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/rubberdoc-android/blob/main/LICENSE
 */

package team.duckie.rubberdoc.node.signature

public data class FileLocation(
  public val startLine: Int,
  public val startColumn: Int,
  public val endLine: Int,
  public val endColumn: Int,
)
