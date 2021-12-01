package peter.coward.puzzles

object Day1 {

  sealed trait DepthMeasure
  final case object NoPreviousMeasure extends DepthMeasure
  final case object DepthIncrease extends DepthMeasure
  final case object DepthDecrease extends DepthMeasure
  final case object DepthSame extends DepthMeasure

  def calculateSonarDepthIncreasesSingle(depths: List[String]): Int =
    calculateSonarDepthIncreases(depths.map(_.toInt))

  def calculateSonarDepthIncreases(depths: List[Int]): Int =
    depths
      .foldLeft(List.empty[(Int, DepthMeasure)]) { (agg, currDepth) =>
        agg.reverse.headOption match {
          case Some(prevDepth) =>
            val depthMeasure =
              if (prevDepth._1 > currDepth) DepthDecrease
              else if (prevDepth._1 < currDepth) DepthIncrease
              else DepthSame
            agg :+ (currDepth, depthMeasure)
          case None => agg :+ (currDepth, NoPreviousMeasure)
        }
      }
      .groupBy(_._2)
      .getOrElse(DepthIncrease, List.empty[(Int, DepthMeasure)])
      .length

  def calculateSonarDepthIncreasesSliding(depths: List[String]): Int = {
    val depthWindows = depths
      .map(_.toInt)
      .sliding(3)
      .takeWhile(_.length == 3)
      .map(_.sum)
      .toList

    calculateSonarDepthIncreases(depthWindows)
  }
}
