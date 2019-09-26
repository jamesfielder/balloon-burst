package com.github.jamesfielder.balloonburst

import scala.io.StdIn.readLine
import scala.util.{Success, Try}
import com.github.jamesfielder.balloonburst.ExceptionFunctions.exitWithMessage

object BalloonGame extends App {

  type Burst = String

  val balloonsTry: Try[Seq[Int]] = for {
    line <- Try(readLine())
    split <- Try(line.split(" ")).filter(_.length > 0)
    splitSeq <- Try(split.toSeq)
    nums <- Try(splitSeq.map(_.toInt)).filter(_.forall(_ > 0))
  } yield nums

  balloonsTry.recover {
    case n: NoSuchElementException => exitWithMessage("Make sure that all of your balloon inflation limits are greater than 0", None)
    case e: NumberFormatException => exitWithMessage("Unable to parse input into balloon pump limit", Some(e))
    case _ => exitWithMessage("Unknown failure, exiting", None)
  }

  // Since we've used recover above we can now safely call .get on the Try
  balloonsTry.get.map(b => {

  })


}
