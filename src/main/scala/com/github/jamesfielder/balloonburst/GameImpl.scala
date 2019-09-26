package com.github.jamesfielder.balloonburst

import scala.annotation.tailrec
import scala.io.StdIn.readLine
import scala.util.{Failure, Success, Try}

case class GameImpl() extends BalloonLimitReader with ProgramFunctions {

  type Burst = String
  type Game = Either[Burst, Int]

  def runGame(): String = {
    // We're aiming to effectively have a function which goes
    // Seq[Int] => Seq[Game] => Int => String
    getBalloonLimits
      .map(b => playGame(b))
      .foldLeft(Right(0))((acc, game) => game match {
        case Right(score) => Right(acc.map(_ + score).getOrElse(0))
        case Left(_) => acc
      })
      .map(score => "SCORE: " + score)
      .getOrElse("SCORE: 0")
  }

  def playGame(limit: Int): Game = {
    playGame(limit, Right(0))
  }

  @tailrec
  final def playGame(limit: Int, game: Game): Game = {
    (Try(readLine()), limit) match {
      case (Success("INFLATE"), limit) if limit > 0 => playGame(limit - 1, game.map(_ + 1))
      case (Success("BANK"), limit) if limit >= 0 => game
      case (Success("INFLATE"), limit) if limit == 0 =>
        println("BURST")
        Left("BURST")
      case (Success(_), _) =>
        println("Sorry I don't know what that means, please try again")
        playGame(limit, game)
      case (Failure(ex), _) =>
        exitWithMessage("Failure to get input, exiting", Some(ex))
        game // Here to make the types line up, never returned
    }
  }
}
