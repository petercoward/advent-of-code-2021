package peter.coward.puzzles

object Day5 {
  final case class Point(x: Int, y: Int)

  def parseInputToLines(input: List[String], withDiagonals: Boolean = false): List[Point] =
    input.flatMap { line =>
      val startEnd = line.split(" -> ").map { coords =>
        coords.splitAt(coords.indexOf(",")) match {
          case (x, y) => Point(x.toInt, y.drop(1).toInt)
        }
      }

      val start = startEnd(0)
      val end = startEnd(1)

      (start, end) match {
        case (Point(x1, y1), Point(x2, y2)) if x1 == x2 =>
          val step = if(y1 > y2) -1 else 1
          (y1 to y2 by step).map(Point(x1, _))
        case (Point(x1, y1), Point(x2, y2)) if y1 == y2 =>
          val step = if(x1 > x2) -1 else 1
          (x1 to x2 by step).map(Point(_, y1))
        case (s @ Point(x1, y1), e @ Point(x2, y2)) if x1 != x2 && y1 != y2 =>
          if (withDiagonals) makeDiagonalLine(s, e)
          else Nil
        case (_, _) => Nil
      }
    }

  def makeDiagonalLine(start: Point, end: Point): List[Point] = {
    val xStep = if(start.x > end.x) -1 else 1
    val yStep = if(start.y > end.y) -1 else 1

    (start.x to end.x by xStep).zip(start.y to end.y by yStep).map {
      case (x, y) => Point(x, y)
    }.toList
  }


  def calculateDangerZones(input: List[String], withDiagonals: Boolean = false): Int = {

    //res1.foreach(p => println("Point(" + p.x + ", " + p.y + ")"))
    parseInputToLines(input, withDiagonals)
      .groupBy(p => (p.x, p.y))
      .view
      .mapValues(_.length)
      .filter(_._2 > 1)
      .toList
      .length
  }
}
