/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/rubberdoc-android/blob/main/LICENSE
 */

package team.duckie.rubberdoc.node

import team.duckie.rubberdoc.node.base.DRChild
import team.duckie.rubberdoc.node.signature.Signature
import team.duckie.rubberdoc.utils.MutableVector

public data class ClassNode(
  override val signature: Signature,
  public val properties: MutableVector<Signature>,
  public val functions: MutableVector<Signature>,
) : DRChild
