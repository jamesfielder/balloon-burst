package com.github.jamesfielder.balloonburst

import org.scalatest._

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
