package mintriangle

class MinTrianglePathLogicSuite extends munit.FunSuite:
  test("Example triangle should return minimal path 18 with 7 + 6 + 3 + 2") {
    val triangle = List(
      List(7),
      List(6, 3),
      List(3, 8, 5),
      List(11, 2, 10, 9)
    )
    val result = MinTrianglePathLogic.solveTriangle(triangle)
    assert(result.isDefined)
    assertEquals(result.get.sum, 18)
    assertEquals(result.get.path, List(7, 6, 3, 2))
  }

  test("Single element triangle should return itself") {
    val triangle = List(List(42))
    val result = MinTrianglePathLogic.solveTriangle(triangle)
    assert(result.isDefined)
    assertEquals(result.get.sum, 42)
    assertEquals(result.get.path, List(42))
  }

  test("Empty triangle should return None") {
    val triangle = List.empty[List[Int]]
    val result = MinTrianglePathLogic.solveTriangle(triangle)
    assert(result.isEmpty)
  }
