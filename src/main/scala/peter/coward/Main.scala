package peter.coward

import peter.coward.puzzles.Day1
import peter.coward.utils.Utils

object Main extends App {
  val day1Result1 = Day1.calculateSonarDepthIncreasesSingle(Utils.getResource("/day1Input.txt"))
  println(day1Result1)
  val day1Result2 = Day1.calculateSonarDepthIncreasesSliding(Utils.getResource("/day1Input.txt"))
  println(day1Result2)
}
