package peter.coward.puzzles

import scala.annotation.tailrec
import scala.collection.mutable

object Day6 {

  final case class LanternFish(timer: Int)

  def calculateLanternFishGrowth(input: List[String], numDays: Int): Long = {
    val fish = input.flatMap { line =>
      line.split(",").map { timer =>
        LanternFish(timer.toInt)
      }
    }.to(LazyList)

    @tailrec
    def growFish(fishes: LazyList[LanternFish], days: Int): LazyList[LanternFish] =
      if(days < 1) {
        fishes
      } else {
        val newFishes = fishes.flatMap {
          case og @ LanternFish(timer) if timer == 0 =>
            LazyList(og.copy(timer = 6), LanternFish(8))
          case LanternFish(timer) => LazyList(LanternFish(timer - 1))
        }
        growFish(newFishes, days - 1)
      }

    growFish(fish, numDays).foldLeft(0L) { (sum, _) =>
      sum + 1L
    }
  }

  def calculateLanternFishGrowthWithHashMap(input: List[String], numDays: Int): Long = {
    val fish = input.flatMap { line =>
      line.split(",").map(_.toInt)
    }.groupBy(identity)

    val fishMap = mutable.HashMap.from((0 to 8).map(x => x -> 0L).toMap)

    fish.foreach {
      case (i, value) => fishMap(i) = value.length.toLong
    }

    @tailrec
    def growFishes(fishes: mutable.HashMap[Int, Long], numDays: Int): mutable.HashMap[Int, Long] =
      if (numDays < 1) fishes
      else {
        val newMap = mutable.HashMap.from((0 to 8).map(x => x -> 0L).toMap)
        (0 to 8).foreach {
          case i if i == 0 =>
            newMap(8) = fishes(0)
            val age6 = newMap(6)
            newMap(6) = age6 + fishes(0)
          case i =>
            val currentAdults = newMap(i - 1)
            newMap(i -1) = currentAdults + fishes(i)
        }
        growFishes(newMap, numDays - 1)
      }

    growFishes(fishMap, numDays).foldLeft(0L) { (sum, curr) =>
      sum + curr._2
    }
  }
}
