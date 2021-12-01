package peter.coward.puzzles

import peter.coward.TestFixtures

class Day1Test extends TestFixtures {

  val input = List("199",
    "200",
    "208",
    "210",
    "200",
    "207",
    "240",
    "269",
    "260",
    "263")

  "calculateSonarDepthIncreases" should "count the number of depth increases" in {
    val result = Day1.calculateSonarDepthIncreasesSingle(input)
    result shouldBe 7
  }

  it should "count the number of depth increase windows" in {
    val result = Day1.calculateSonarDepthIncreasesSliding(input)
    result shouldBe 5
  }
}
