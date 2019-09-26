package com.github.jamesfielder.balloonburst

import com.github.jamesfielder.balloonburst.BalloonGame.Game
import com.github.jamesfielder.balloonburst.ExceptionFunctions.exitWithMessage

import scala.io.StdIn.readLine
import scala.util.{Failure, Success, Try}

object GameImpl {
  def playGame(limit: Int): Game = {
    playGame(limit, Right(0))
  }

  def playGame(limit: Int, game: Game): Game = {
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
