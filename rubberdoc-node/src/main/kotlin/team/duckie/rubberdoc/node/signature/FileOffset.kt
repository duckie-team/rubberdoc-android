/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/rubberdoc-android/blob/main/LICENSE
 */

package team.duckie.rubberdoc.node.signature

public data class FileOffset(
  public val line: Int,
  public val column: Int,
  public val offset: Int,
)
