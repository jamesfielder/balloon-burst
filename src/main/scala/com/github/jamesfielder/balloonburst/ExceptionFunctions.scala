package com.github.jamesfielder.balloonburst

object ExceptionFunctions {
  def exitWithMessage(message: String, e: Option[Throwable]): Unit = {
    println(message)
    e.foreach(ex => println("Exception is:" + ex.toString))

    System.exit(1)
  }
}
