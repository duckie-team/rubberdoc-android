/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/rubberdoc-android/blob/main/LICENSE
 */

package team.duckie.rubberdoc.visitor.color

import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrValueParameter
import org.jetbrains.kotlin.ir.util.companionObject
import org.jetbrains.kotlin.ir.util.defaultConstructor
import org.jetbrains.kotlin.ir.util.primaryConstructor
import org.jetbrains.kotlin.ir.util.properties
import team.duckie.quackquack.util.backend.kotlinc.Logger
import team.duckie.rubberdoc.node.Color
import team.duckie.rubberdoc.node.ColorNode
import team.duckie.rubberdoc.node.signature.Signature
import team.duckie.rubberdoc.utils.MutableVector
import team.duckie.rubberdoc.utils.fastMap
import team.duckie.rubberdoc.visitor.base.DrVisitor
import team.duckie.rubberdoc.visitor.utils.getAnnotationSpecs
import team.duckie.rubberdoc.visitor.utils.getIOFileAndFileLocationPair
import team.duckie.rubberdoc.visitor.utils.getKModifiers
import team.duckie.rubberdoc.visitor.utils.toSignature

public class ColorDrVisitor(
  @Suppress("unused") private val context: IrPluginContext,
  private val logger: Logger,
) : DrVisitor() {
  override fun visitClass(declaration: IrClass) {
    with(declaration) {
      val name = name.asString()
      val annotations = getAnnotationSpecs()
      val modifiers = getKModifiers()

      val constructor = defaultConstructor ?: primaryConstructor
      val arguments = constructor?.valueParameters?.fastMap(IrValueParameter::toSignature)

      val (containingFile, fileLocation) = getIOFileAndFileLocationPair()

      val colors =
        companionObject()?.let { companion ->
          val colorableProperties = companion.properties.filter { property ->
            property.visibility.isPublicAPI
          }
          colorableProperties.map { property ->
            property.backingField!!
          }
        } ?: MutableVector<Color>()

      val node = ColorNode(
        signature = Signature(
          name = name,
          annotations = annotations,
          modifiers = modifiers,
          arguments = arguments,
          returnType = null,
          description = "TODO",
          containingFile = containingFile,
          fileLocation = fileLocation,
        ),
        colors = colors,
      )
    }
  }
}
