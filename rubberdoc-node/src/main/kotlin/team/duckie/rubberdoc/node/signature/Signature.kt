/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/rubberdoc-android/blob/main/LICENSE
 */

package team.duckie.rubberdoc.node.signature

import com.squareup.kotlinpoet.AnnotationSpec
import com.squareup.kotlinpoet.ClassName
import com.squareup.kotlinpoet.KModifier
import java.io.File

public data class Signature(
  public val name: String,
  public val annotations: List<AnnotationSpec>,
  public val modifiers: List<KModifier>,
  public val arguments: List<Signature>?,
  public val returnType: ClassName?,
  public val description: String,
  public val containingFile: File?,
  public val fileLocation: FileLocation,
)
