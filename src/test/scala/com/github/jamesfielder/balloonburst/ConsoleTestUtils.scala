package com.github.jamesfielder.balloonburst

import java.io.{ByteArrayOutputStream, StringReader}

object ConsoleTestUtils {
  def testConsole[T](in: StringReader, out: ByteArrayOutputStream)(test: => T): Unit = {
      Console.withOut(out) {
        Console.withIn(in) {
          test
        }
      }
  }
}
