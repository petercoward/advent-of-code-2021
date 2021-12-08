package peter.coward.puzzles

import peter.coward.TestFixtures

class Day6Test extends TestFixtures {
  val input = List(
    "3,4,3,1,2"
  )

  "Day6" should "calculate the number of lantern fish" in {
    Day6.calculateLanternFishGrowthWithHashMap(input, 18) shouldBe 26
    Day6.calculateLanternFishGrowthWithHashMap(input, 80) shouldBe 5934
    Day6.calculateLanternFishGrowthWithHashMap(input, 256) shouldBe 26984457539L
  }
}
