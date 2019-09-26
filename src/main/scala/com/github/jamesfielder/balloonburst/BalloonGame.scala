package com.github.jamesfielder.balloonburst

import scala.io.StdIn.readLine
import scala.util.Try
import com.github.jamesfielder.balloonburst.ExceptionFunctions.exitWithMessage

object BalloonGame extends App {

  val balloons: Try[Seq[Int]] = for {
    line <- Try(readLine())
    split <- Try(line.split(" "))
    splitSeq <- Try(split.toSeq)
    nums <- Try(splitSeq.map(_.toInt))
  } yield nums

  balloons.recover {
    case e: NumberFormatException => exitWithMessage("Unable to parse input into balloon pump limit", Some(e))
  }

  println(balloons)
}

class BalloonGame {
}
