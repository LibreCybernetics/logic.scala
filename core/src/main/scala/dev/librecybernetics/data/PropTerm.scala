package dev.librecybernetics.data

import cats.parse.Parser

enum PropTerm:
  case False extends PropTerm with PropTerm.PropValue
  case True  extends PropTerm with PropTerm.PropValue

  case PropVariable(name: String)
end PropTerm

object PropTerm:
  sealed trait PropValue

  object PropValue:
    val falseParser: Parser[PropTerm.False.type] = Parser.stringIn(Set("false", "⊥")).as(PropTerm.False)
    val trueParser: Parser[PropTerm.True.type]   = Parser.stringIn(Set("true", "⊤")).as(PropTerm.True)

    val parser: Parser[PropTerm with PropValue] = Parser.oneOf(List(falseParser, trueParser))
  end PropValue

  object PropVariable:
    val parser: Parser[PropVariable] =
      Parser.charsWhile(_.isLetter).map(PropVariable(_))
  end PropVariable

  val parser: Parser[PropTerm] = Parser.oneOf(List(PropValue.parser, PropVariable.parser))
end PropTerm
