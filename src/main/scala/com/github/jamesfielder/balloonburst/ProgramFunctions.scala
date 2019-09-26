package com.github.jamesfielder.balloonburst

trait ProgramFunctions {

  def exitFunction = System.exit(1)

  def exitWithMessage(message: String, e: Option[Throwable]): Unit = {
    println(message)
    e.foreach(ex => println("Exception is:" + ex.toString))

    exitFunction
  }
}
