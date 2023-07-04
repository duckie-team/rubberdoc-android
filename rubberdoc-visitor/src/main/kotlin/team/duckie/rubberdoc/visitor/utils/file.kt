/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/rubberdoc-android/blob/main/LICENSE
 */

package team.duckie.rubberdoc.visitor.utils

import java.io.File
import org.jetbrains.kotlin.backend.jvm.ir.getIoFile
import org.jetbrains.kotlin.cli.common.messages.CompilerMessageSourceLocation
import org.jetbrains.kotlin.ir.declarations.IrDeclaration
import org.jetbrains.kotlin.ir.util.file
import team.duckie.quackquack.util.backend.kotlinc.locationOf
import team.duckie.rubberdoc.node.signature.FileLocation

internal fun IrDeclaration.getIOFileAndFileLocationPair(): Pair<File?, FileLocation> {
  val file = file
  val containingFile = file.getIoFile()
  val sourceLocation = file.locationOf(this)

  return containingFile to sourceLocation.asFileLocation()
}

internal fun CompilerMessageSourceLocation.asFileLocation() =
  FileLocation(
    startLine = line,
    startColumn = column,
    endLine = lineEnd,
    endColumn = columnEnd,
  )