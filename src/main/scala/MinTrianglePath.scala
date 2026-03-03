package mintriangle

import scala.io.Source
import scala.util.Try

object MinTrianglePath:
  def main(args: Array[String]): Unit =
    // Read lines from stdin
    val lines = Source.fromInputStream(System.in).getLines().toList

    // Parse lines into a triangle (List[List[Int]])
    // We ignore empty lines or lines that don't contain integers
    val triangle: List[List[Int]] = lines
      .map(_.trim)
      .filter(_.nonEmpty)
      .map { line =>
        line.split("\\s+").flatMap(s => Try(s.toInt).toOption).toList
      }
      .filter(_.nonEmpty)

    if triangle.isEmpty then
      println("Error: Empty or invalid triangle provided.")
      sys.exit(1)

    // Calculate the minimal path
    MinTrianglePathLogic.solveTriangle(triangle) match
      case Some(result) =>
        // Format the output: Minimal path is: 7 + 6 + 3 + 2 = 18
        val pathStr = result.path.mkString(" + ")
        println(s"Minimal path is: $pathStr = ${result.sum}")
      case None =>
        println("Error: Could not calculate minimal path.")
        sys.exit(1)
