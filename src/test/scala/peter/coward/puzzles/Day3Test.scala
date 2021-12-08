package peter.coward.puzzles

import peter.coward.TestFixtures

class Day3Test extends TestFixtures {
  val input = List(
    "00100",
    "11110",
    "10110",
    "10111",
    "10101",
    "01111",
    "00111",
    "11100",
    "10000",
    "11001",
    "00010",
    "01010"
  )

  "Day3" should "calculate power consumption" in {
    Day3.calculatePowerConsumption(input) shouldBe 198
  }

  it should "calculate life support" in {
    Day3.calculateLifeSupport(input) shouldBe 230
  }

  "calculateOxygen" should "calculate the oxygen reading" in {
    Day3.calculateOxygen(input) shouldBe 23
  }

  "calculateC02" should "calculate the co2 reading" in {
    Day3.calculateCO2(input) shouldBe 10
  }
}
