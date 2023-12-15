package dev.librecybernetics.data

import cats.parse.Parser
import org.scalatest.Assertion
import org.scalatest.Assertions.fail
import org.scalatest.matchers.should.Matchers.shouldBe

extension [A](parser: Parser[A])
  def shouldParse(input: String, expected: A): Assertion =
    parser.parseAll(input) match
      case Right(actual) => actual shouldBe expected
      case Left(error)   => fail(s"$input failed to parse: $error")
    end match
  end shouldParse
end extension
