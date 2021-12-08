package peter.coward.puzzles

import peter.coward.TestFixtures

class Day2Test extends TestFixtures {
  val input = List(
    "forward 5",
    "down 5",
    "forward 8",
    "up 3",
    "down 8",
    "forward 2"
  )

  "Day2" should "calculate the final depth and horizontal position and multiply them" in {
    Day2.calculateCourseProduct(input) shouldBe 150
  }

  it should "calculate the final depth and horizontal position with aim" in {
    Day2.calculateCourseProductWithAim(input) shouldBe 900
  }
}
