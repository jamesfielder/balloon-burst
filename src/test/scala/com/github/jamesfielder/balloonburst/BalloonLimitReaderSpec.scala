package com.github.jamesfielder.balloonburst

import java.io.{ByteArrayOutputStream, StringReader}
import com.github.jamesfielder.balloonburst.ConsoleTestUtils._
import org.scalatest.{FlatSpec, Matchers}

class BalloonLimitReaderSpec extends FlatSpec with Matchers {
  val reader = new TestReader
  "BalloonLimitReader" should "read some balloon limits correctly" in {
    val input = "1 1"
    val in = new StringReader(input)
    val out = new ByteArrayOutputStream()

    testConsole(in, out) {
      reader.getBalloonLimits should be (Seq(1,1))
    }
  }

  it should "fail when no balloons are input" in {
    val input = sys.props("line.separator")
    val in = new StringReader(input)
    val out = new ByteArrayOutputStream()

    testConsole(in, out) {
      reader.getBalloonLimits
    }

    out.toString should include ("Unable to parse input")
  }

  it should "fail on unparsable inputs" in {
    val input = "1 n whatever, 112"
    val in = new StringReader(input)
    val out = new ByteArrayOutputStream()

    testConsole(in, out) {
      reader.getBalloonLimits
    }

    out.toString should include ("Unable to parse input")
  }
}

trait TestProgramFunctions extends ProgramFunctions {
  override def exitFunction: Unit = ()
}

class TestReader extends BalloonLimitReader with TestProgramFunctions
