/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/rubberdoc-android/blob/main/LICENSE
 */

package team.duckie.rubberdoc.android

public enum class RubberdocType {
  Icon,
  Color,
  Component,
}

@Target(AnnotationTarget.FILE)
@Retention(AnnotationRetention.SOURCE)
public annotation class Rubberdoc(
  public val title: String,
  public val document: String,
  public val type: RubberdocType = RubberdocType.Component,
)
