package peter.coward.puzzles

import peter.coward.TestFixtures
import peter.coward.puzzles.Day4.{BingoCard, BingoNumber}

class Day4Test extends TestFixtures {
  val input = List(
    "7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1",
    "",
    "22 13 17 11  0",
    "8  2 23  4 24",
    "21  9 14 16  7",
    "6 10  3 18  5",
    "1 12 20 15 19",
    "",
    "3 15  0  2 22",
    "9 18 13 17  5",
    "19  8  7 25 23",
    "20 11 10 24  4",
    "14 21 16 12  6",
    "",
    "14 21 17 24  4",
    "10 16 15  9 19",
    "18  8 23 26 20",
    "22 11 13  6  5",
    "2  0 12  3  7"
  )

  "Day 4" should "correctly parse input to BingoCards" in {
    val expected = List(
      BingoCard(
        card = Array(
          Array(BingoNumber(22), BingoNumber(13), BingoNumber(17), BingoNumber(11), BingoNumber(0)),
          Array(BingoNumber(8), BingoNumber(2), BingoNumber(23), BingoNumber(4), BingoNumber(24)),
          Array(BingoNumber(21), BingoNumber(9), BingoNumber(14), BingoNumber(16), BingoNumber(7)),
          Array(BingoNumber(6), BingoNumber(10), BingoNumber(3), BingoNumber(18), BingoNumber(5)),
          Array(BingoNumber(1), BingoNumber(12), BingoNumber(20), BingoNumber(15), BingoNumber(19))
        )
      ),
      BingoCard(
        card = Array(
          Array(BingoNumber(3), BingoNumber(15), BingoNumber(0), BingoNumber(2), BingoNumber(22)),
          Array(BingoNumber(9), BingoNumber(18), BingoNumber(13), BingoNumber(17), BingoNumber(5)),
          Array(BingoNumber(19), BingoNumber(8), BingoNumber(7), BingoNumber(25), BingoNumber(23)),
          Array(BingoNumber(20), BingoNumber(11), BingoNumber(10), BingoNumber(24), BingoNumber(4)),
          Array(BingoNumber(14), BingoNumber(21), BingoNumber(16), BingoNumber(12), BingoNumber(6))
        )
      ),
      BingoCard(
        card = Array(
          Array(BingoNumber(14), BingoNumber(21), BingoNumber(17), BingoNumber(24), BingoNumber(4)),
          Array(BingoNumber(10), BingoNumber(16), BingoNumber(15), BingoNumber(9), BingoNumber(19)),
          Array(BingoNumber(18), BingoNumber(8), BingoNumber(23), BingoNumber(26), BingoNumber(20)),
          Array(BingoNumber(22), BingoNumber(11), BingoNumber(13), BingoNumber(6), BingoNumber(5)),
          Array(BingoNumber(2), BingoNumber(0), BingoNumber(12), BingoNumber(3), BingoNumber(7))
        )
      )
    )

    //Pass in the input but drop the first two lines (we just want the number matrices)
    val result = Day4.parseToBingoCards(input.drop(2))
    result.head.card should contain theSameElementsAs expected.head.card
    result.tail.head.card should contain theSameElementsAs expected.tail.head.card
    result.tail.tail.head.card should contain theSameElementsAs expected.tail.tail.head.card
  }

  it should "calculate the winning score" in {
    Day4.calculateWinningScore(input) shouldBe 4512
  }

  it should "calculate the last winning score" in {
    Day4.calculateLastScore(input) shouldBe 1924
  }
}
