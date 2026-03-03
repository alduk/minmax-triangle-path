package mintriangle

case class PathResult(sum: Int, path: List[Int])

enum SearchType:
  case Min, Max

object MinTrianglePathLogic:
  def solveTriangle(
      triangle: List[List[Int]],
      searchType: SearchType = SearchType.Min
  ): Option[PathResult] =
    if triangle.isEmpty then None
    else
      // Starting from the bottom row, represented as PathResults
      val initialStates = triangle.last.map(v => PathResult(v, List(v)))

      // Fold right from the second-to-last row up to the top row
      val resultStates = triangle.init.foldRight(initialStates) {
        (currentRow, previousStates) =>
          currentRow.zipWithIndex.map { case (value, index) =>
            val left = previousStates(index)
            val right = previousStates(index + 1)

            val condition = searchType match
              case SearchType.Min => left.sum <= right.sum
              case SearchType.Max => left.sum >= right.sum

            if condition then PathResult(value + left.sum, value :: left.path)
            else PathResult(value + right.sum, value :: right.path)
          }
      }

      resultStates.headOption
