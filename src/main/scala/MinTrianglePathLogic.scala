package mintriangle

case class PathResult(sum: Int, path: List[Int])

object MinTrianglePathLogic:
  def solveTriangle(triangle: List[List[Int]]): Option[PathResult] =
    if triangle.isEmpty then None
    else
      // Starting from the bottom row, represented as PathResults
      val initialStates = triangle.last.map(v => PathResult(v, List(v)))
      
      // Fold right from the second-to-last row up to the top row
      val resultStates = triangle.init.foldRight(initialStates) { (currentRow, previousStates) =>
        currentRow.zipWithIndex.map { case (value, index) =>
          val left = previousStates(index)
          val right = previousStates(index + 1)
          
          if left.sum <= right.sum then
            PathResult(value + left.sum, value :: left.path)
          else
            PathResult(value + right.sum, value :: right.path)
        }
      }
      
      resultStates.headOption
