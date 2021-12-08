package peter.coward

import peter.coward.puzzles.{Day1, Day2, Day3, Day4, Day5, Day6}
import peter.coward.utils.Utils

object Main extends App {
  val day1Result1 = Day1.calculateSonarDepthIncreasesSingle(Utils.getResource("/day1Input.txt"))
  println(day1Result1)
  //1288
  val day1Result2 = Day1.calculateSonarDepthIncreasesSliding(Utils.getResource("/day1Input.txt"))
  println(day1Result2)
  //1311

  val day2Result1 = Day2.calculateCourseProduct(Utils.getResource("/day2Input.txt"))
  println(day2Result1) //1507611
  val day2Result2 = Day2.calculateCourseProductWithAim(Utils.getResource("/day2Input.txt"))
  println(day2Result2) //1880593125

  val day3result1 = Day3.calculatePowerConsumption(Utils.getResource("/day3Input.txt"))
  println(day3result1) //2724524
  val day3result2 = Day3.calculateLifeSupport(Utils.getResource("/day3Input.txt"))
  println(day3result2) //2775870

  val day4Result1 = Day4.calculateWinningScore(Utils.getResource("/day4Input.txt"))
  println(day4Result1) //87456
  val day4Result2 = Day4.calculateLastScore(Utils.getResource("/day4Input.txt"))
  println(day4Result2) //15561

  val day5Result1 = Day5.calculateDangerZones(Utils.getResource("/day5Input.txt"))
  println(day5Result1) //6461
  val day5Result2 = Day5.calculateDangerZones(Utils.getResource("/day5Input.txt"), withDiagonals = true)
  println(day5Result2) //18065

  val day6Result1 = Day6.calculateLanternFishGrowthWithHashMap(Utils.getResource("/day6Input.txt"), 80)
  println(day6Result1) //385391
  val day6Result2 = Day6.calculateLanternFishGrowthWithHashMap(Utils.getResource("/day6Input.txt"), 256)
  println(day6Result2) //1728611055389
}
