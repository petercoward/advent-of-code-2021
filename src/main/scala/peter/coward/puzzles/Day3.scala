package peter.coward.puzzles

import scala.annotation.tailrec

object Day3 {

  def mostCommon(in: List[Char]): Char = {
    val grouped = in.groupBy(identity)
    if (grouped.getOrElse('1', List.empty).length == grouped.getOrElse('0', List.empty).length) '1'
    else grouped.maxBy(_._2.length)._1
  }

  def leastCommon(in: List[Char]): Char = {
    val grouped = in.groupBy(identity)
    if(grouped.getOrElse('1', List.empty).length == grouped.getOrElse('0', List.empty).length) '0'
    else grouped.minBy(_._2.length)._1
  }

  def calculatePowerConsumption(input: List[String]): Int = {
    def calculateAndMap(input: List[String], commonFunc: List[Char] => Char): Int = {
      val res = input.map(_.toList)
        .transpose
        .map(commonFunc)
        .mkString("")
      Integer.parseInt(res, 2)
    }

    val gamma = calculateAndMap(input, mostCommon)
    val epsilon = calculateAndMap(input, leastCommon)
    gamma * epsilon
  }

  def filterDown(input: List[String], commonFunc: List[Char] => Char): Int = {
    @tailrec
    def filterByFrequency(binaryNumbers: List[List[Char]], commonDigits: List[Char], i: Int): List[Char] = {
      if (binaryNumbers.length == 1) binaryNumbers.head
      else {
        val remainder = binaryNumbers.filter { num => num(i) == commonDigits.head }
        val nextCommonDigits = remainder.transpose.map(commonFunc).drop(i + 1)
        filterByFrequency(remainder, nextCommonDigits, i + 1)
      }
    }

    val res = filterByFrequency(input.map(_.toList), input.map(_.toList).transpose.map(commonFunc), 0)
    Integer.parseInt(res.mkString(""), 2)
  }

  def calculateOxygen(input: List[String]): Int =
    filterDown(input, mostCommon)

  def calculateCO2(input: List[String]): Int =
    filterDown(input, leastCommon)

  def calculateLifeSupport(input: List[String]): Int =
    calculateOxygen(input) * calculateCO2(input)
}
