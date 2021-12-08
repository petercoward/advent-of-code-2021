package peter.coward.puzzles

import scala.annotation.tailrec

object Day4 {
  final case class BingoNumber(num: Int, marked: Boolean = false)
  final case class BingoCard(card: Array[Array[BingoNumber]] = Array.ofDim(5, 5), lastCall: Int = 0) {
    def isWinner: Boolean =
      card.exists(row => row.forall(_.marked == true)) || card.transpose.exists(col => col.forall(_.marked == true))

    def calculateScore(lastNumber: Int): Int =
      this.card.foldLeft(0){ (sum, curr) =>
        sum + curr.filterNot(_.marked).map(_.num).sum
      } * lastNumber
  }

  def parseToBingoCards(input: List[String]): List[BingoCard] = {
    @tailrec
    def parse(total: List[BingoCard], currCard: BingoCard, remaining: List[String], i: Int = 0): List[BingoCard] =
      remaining match {
        case head :: tail if head.isBlank => parse(total :+ currCard, BingoCard(), tail, 0)
        case head :: tail =>
          val rowNumbers = head.stripLeading.split("\\s+").map(x => BingoNumber(x.toInt))
          val updatedCard = currCard.copy(card = currCard.card.updated(i, rowNumbers))
          parse(total, updatedCard, tail, i + 1)
        case Nil => total :+ currCard
      }

    parse(List.empty[BingoCard], BingoCard(), input)
  }

  def calculateWinningScore(input: List[String]): Int = {
    val bingoNumbers = input.head.split(",").map(_.toInt).toList

    val bingoCards = parseToBingoCards(input.drop(2))

    @tailrec
    def playBingoGame(remainingNumbers: List[Int], bingoCards: List[BingoCard]): Int =
      remainingNumbers match {
        case Nil => 0 //If no winners til the end of the numbers then just return 0
        case currNum :: tail =>
          val markedCards = bingoCards.map { bc =>
            bc.copy(card = bc.card.map(row =>
                row.map {
                  case BingoNumber(num, _) if num == currNum => BingoNumber(num, marked = true)
                  case b @ BingoNumber(_, _) => b
                }
              )
            )
          }
          if(markedCards.exists(_.isWinner)) {
            markedCards.filter(_.isWinner).head.calculateScore(currNum)
          } else {
            playBingoGame(tail, markedCards)
          }

      }

    playBingoGame(bingoNumbers, bingoCards)
  }

  def calculateLastScore(input: List[String]): Int = {
    val bingoNumbers = input.head.split(",").map(_.toInt).toList

    val bingoCards = parseToBingoCards(input.drop(2))

    @tailrec
    def playBingoGameTilTheEnd(remainingNumbers: List[Int], bingoCards: List[BingoCard], winningCards: List[BingoCard]): List[BingoCard] =
      remainingNumbers match {
        case Nil => winningCards
        case currNum :: tail =>
          val markedCards = bingoCards.map { bc =>
            val markedCard = bc.copy(card = bc.card.map(row =>
                row.map {
                  case BingoNumber(num, _) if num == currNum => BingoNumber(num, marked = true)
                  case b @ BingoNumber(_, _) => b
                }
              )
            )
            if(markedCard.isWinner) {
              markedCard.copy(lastCall = currNum)
            } else markedCard
          }
          playBingoGameTilTheEnd(tail, markedCards.filterNot(_.isWinner), winningCards ++ markedCards.filter(_.isWinner))
      }

    val finalCards = playBingoGameTilTheEnd(bingoNumbers, bingoCards, List.empty)
    val lastWinner = finalCards.filter(_.isWinner).last
    lastWinner.calculateScore(lastWinner.lastCall)
  }

}
