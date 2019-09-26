package com.github.jamesfielder.balloonburst

import java.io.{ByteArrayOutputStream, StringReader}

import org.scalatest._
import com.github.jamesfielder.balloonburst.ConsoleTestUtils._

class GameImplSpec extends FlatSpec with Matchers {

  import GameMocks._

  "runGame" should "return SCORE: 0 on an empty game" in {
    noGame.runGame() should be ("SCORE: 0")
  }

  it should "return SCORE: 1 on a correctly played game with one balloon" in {
    shortGame.runGame() should be ("SCORE: 1")
  }

  it should "return SCORE: 3 on a correctly played game with three balloons" in {
    game.runGame() should be ("SCORE: 3")
  }

  it should "correctly score for a real game" in {
    val input =
      """|2 4 1
         |INFLATE
         |INFLATE
         |INFLATE
         |INFLATE
         |BANK
         |INFLATE
         |BANK""".stripMargin
    val in = new StringReader(input)
    val out = new ByteArrayOutputStream()

    testConsole(in, out) {
      GameImpl().runGame() should include("SCORE: 2")
    }

    out.toString should include("BURST")
  }
}

object GameMocks {

  trait BalloonLimitReaderMock extends BalloonLimitReader {
    override def getBalloonLimits: Seq[Int] = Seq(1, 2, 3)
  }

  trait BalloonLimitReaderMockShort extends BalloonLimitReader {
    override def getBalloonLimits: Seq[Int] = Seq(1)
  }

  trait BallonLimitReaderMockNone extends BalloonLimitReader {
    override def getBalloonLimits: Seq[Int] = Seq()
  }

  val shortGame = new GameImpl with BalloonLimitReaderMockShort {
    override def playGame(limit: Int): Game = Right(1)
  }

  val game = new GameImpl with BalloonLimitReaderMock {
    override def playGame(limit: Int): Game = Right(1)
  }

  val noGame = new GameImpl with BallonLimitReaderMockNone
}
