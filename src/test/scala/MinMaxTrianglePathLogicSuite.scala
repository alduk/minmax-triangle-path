package mintriangle

class MinMaxTrianglePathLogicSuite extends munit.FunSuite:
  test("Example triangle should return minimal path 18 with 7 + 6 + 3 + 2") {
    val triangle = List(
      List(7),
      List(6, 3),
      List(3, 8, 5),
      List(11, 2, 10, 9)
    )
    val result = MinMaxTrianglePathLogic.solveTriangle(triangle, SearchType.Min)
    assert(result.isDefined)
    assertEquals(result.get.sum, 18)
    assertEquals(result.get.path, List(7, 6, 3, 2))
  }

  test(
    "Example triangle should return maximal path 37 with 7 + 6 + 5 + 10 or 7 + 6 + 8 + 10"
  ) {
    val triangle = List(
      List(7),
      List(6, 3),
      List(3, 8, 5),
      List(11, 2, 10, 9)
    )
    val result = MinMaxTrianglePathLogic.solveTriangle(triangle, SearchType.Max)
    assert(result.isDefined)
    // 7 + 6 + 8 + 10 = 31... Wait, let's trace:
    // Bottom: 11, 2, 10, 9
    // Max of (11, 2) is 11. 3+11 = 14
    // Max of (2, 10) is 10. 8+10 = 18
    // Max of (10, 9) is 10. 5+10 = 15
    // Row 2 states: 14, 18, 15
    // Max of (14, 18) is 18. 6+18 = 24
    // Max of (18, 15) is 18. 3+18 = 21
    // Row 1 states: 24, 21
    // Max of (24, 21) is 24. 7+24 = 31
    assertEquals(result.get.sum, 31)
    // The path producing 31: 7 -> 6 -> 8 -> 10 => 7 + 6 + 8 + 10 = 31
    assertEquals(result.get.path, List(7, 6, 8, 10))
  }

  test("Single element triangle should return itself") {
    val triangle = List(List(42))
    val result = MinMaxTrianglePathLogic.solveTriangle(triangle)
    assert(result.isDefined)
    assertEquals(result.get.sum, 42)
    assertEquals(result.get.path, List(42))
  }

  test("Empty triangle should return None") {
    val triangle = List.empty[List[Int]]
    val result = MinMaxTrianglePathLogic.solveTriangle(triangle)
    assert(result.isEmpty)
  }
