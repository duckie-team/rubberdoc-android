/*
 * Designed and developed by Duckie Team 2023.
 *
 * Licensed under the MIT.
 * Please see full license: https://github.com/duckie-team/rubberdoc-android/blob/main/LICENSE
 */

package team.duckie.rubberdoc.node.base

import java.nio.file.Path
import team.duckie.rubberdoc.material.Rubberdoc
import team.duckie.rubberdoc.utils.MutableVector

public interface DRRoot {
  public val rubberdoc: Rubberdoc
  public val filePath: Path
  public val items: MutableVector<DRChild>
}
