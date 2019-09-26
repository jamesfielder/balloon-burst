package com.github.jamesfielder.balloonburst

import com.github.jamesfielder.balloonburst.Console.BalloonReader.getBalloonLimits
import com.github.jamesfielder.balloonburst.GameImpl.playGame

object BalloonGame extends App {

  type Burst = String
  type Game = Either[Burst, Int]

  // We're aiming to effectively have a function which goes
  // Seq[Int] => Seq[Game] => Int
  getBalloonLimits
    .map(b => playGame(b))
    .foldLeft(Right(0))((acc, game) => game match {
      case Right(score) => Right(acc.map(_ + score).getOrElse(0))
      case Left(_) => acc
    })
    .map(score => println("SCORE: " + score))
}
