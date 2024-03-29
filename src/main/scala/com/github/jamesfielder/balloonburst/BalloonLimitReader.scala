package com.github.jamesfielder.balloonburst

import scala.io.StdIn.readLine
import scala.util.Try

trait BalloonLimitReader extends ProgramFunctions {
  def getBalloonLimits: Seq[Int] = {
    val balloonsTry: Try[Seq[Int]] = for {
      line <- Try(readLine())
      split <- Try(line.split(" ")).filter(l => l.length > 0 && l.nonEmpty)
      splitSeq <- Try(split.toSeq)
      nums <- Try(splitSeq.map(_.toInt)).filter(_.forall(_ > 0))
    } yield nums

    balloonsTry.recover {
      case n: NoSuchElementException => exitWithMessage("Make sure that all of your balloon inflation limits are greater than 0", None)
      case e: NumberFormatException => exitWithMessage("Unable to parse input into balloon pump limit", Some(e))
      case _ => exitWithMessage("Unknown failure, exiting", None)
    }

    // Since we've used recover above we can now safely call .get on the Try
    balloonsTry.getOrElse(Seq())
  }
}
