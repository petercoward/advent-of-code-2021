package peter.coward.puzzles

import peter.coward.TestFixtures
import peter.coward.puzzles.Day5.Point

class Day5Test extends TestFixtures {

  val testInput = List(
    "0,9 -> 5,9",
    "8,0 -> 0,8",
    "9,4 -> 3,4",
    "2,2 -> 2,1",
    "7,0 -> 7,4",
    "6,4 -> 2,0",
    "0,9 -> 2,9",
    "3,4 -> 1,4",
    "0,0 -> 8,8",
    "5,5 -> 8,2"
  )

  "Day5" should "calculate the number of danger zones" in {
    Day5.calculateDangerZones(testInput) shouldBe 5
  }

  it should "calculate the number of danger zones with diagonals" in {
    Day5.calculateDangerZones(testInput, withDiagonals = true) shouldBe 12
  }

  "makeDiagonalLine" should "generate all the points in a diagonal line" in {
    Day5.makeDiagonalLine(Point(1, 1), Point(3, 3)) shouldBe List(
      Point(1, 1),
      Point(2, 2),
      Point(3, 3)
    )
  }
}
