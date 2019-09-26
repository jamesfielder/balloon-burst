package com.github.jamesfielder.balloonburst.Console

import scala.io.StdIn.readLine
import scala.util.Try

trait Reader {
  def getLine: Try[String]
}

class ConsoleReader extends Reader {
  override def getLine: Try[String] = Try(readLine())
}