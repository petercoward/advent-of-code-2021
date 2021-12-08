package peter.coward.puzzles

object Day2 {
  case class Position(horizontal: Int, depth: Int)
  case class PositionWithAim(horizontal: Int, depth: Int, aim: Int)

  def calculateCourseProduct(input: List[String]): Int = {
    val position = parseInput(input)
      .foldLeft(Position(0, 0)){ (pos, currDir) =>
        currDir match {
          case ("forward", x) => pos.copy(horizontal = pos.horizontal + x)
          case ("up", x) => pos.copy(depth = pos.depth - x)
          case ("down", x) => pos.copy(depth = pos.depth + x)
        }
      }

    position.horizontal * position.depth
  }

  def parseInput(input: List[String]): List[(String, Int)] =
    input.map { x =>
      val direction = x.split(" ")
      (direction.head.toLowerCase, direction.tail.head.toInt)
    }

  def calculateCourseProductWithAim(input: List[String]): Int = {
    val positionWithAim = parseInput(input)
      .foldLeft(PositionWithAim(0, 0, 0)){ (pos, currDir) =>
        currDir match {
          case ("forward", x) => pos.copy(horizontal = pos.horizontal + x, depth = pos.depth + (x * pos.aim))
          case ("up", x) =>pos.copy(aim = pos.aim - x)
          case ("down", x) => pos.copy(aim = pos.aim + x)
        }
      }
    positionWithAim.horizontal * positionWithAim.depth
  }
}
