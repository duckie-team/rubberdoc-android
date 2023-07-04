/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/rubberdoc-android/blob/main/LICENSE
 */

package team.duckie.rubberdoc.node.signature

import com.squareup.kotlinpoet.KModifier
import java.nio.file.Path

public data class Signature(
  public val name: String,
  public val annotations: List<Annotation>,
  public val modifiers: List<KModifier>,
  public val arguments: List<Signature>,
  public val returnType: Class<*>,
  public val description: String,
  public val filePath: Path,
  public val fileOffset: FileOffset,
)

@Suppress("UnusedReceiverParameter", "UNUSED_PARAMETER")
public fun Signature.toSpec(withConstructor: Boolean) {
  throw NotImplementedError()
}
