package dev.librecybernetics.data

import cats.data.NonEmptyList
import cats.parse.Parser

enum PropExpression:
  case Negation(operand: PropTerm | PropExpression)
  case Conjunction(operands: NonEmptyList[PropTerm | PropExpression])
  case Disjunction(operands: NonEmptyList[PropTerm | PropExpression])
  case MaterialImplication(antecedent: PropTerm | PropExpression, consequent: PropTerm | PropExpression)
end PropExpression

object PropExpression:
  object Conjunction:
    def apply(operands: (PropTerm | PropExpression)*): Conjunction =
      new Conjunction(NonEmptyList.fromListUnsafe(operands.toList))
  end Conjunction

  object Disjunction:
    def apply(operands: (PropTerm | PropExpression)*): Disjunction =
      new Disjunction(NonEmptyList.fromListUnsafe(operands.toList))
  end Disjunction

  private val space                                                  = Parser.char(' ')
  def spaceSep[A](sep: Parser[A]): Parser[A]                         =
    space.rep0.with1 *> sep <* space.rep0
  def negation(expr: Parser[PropExpression]): Parser[PropExpression] =
    Parser.charIn('!', '¬') *> (PropTerm.parser | expr).map(PropExpression.Negation(_))

  def conjunction(expr: Parser[PropExpression]): Parser[PropExpression] =
    (PropTerm.parser | expr)
      .repSep(min = 2, sep = spaceSep(Parser.charIn('&', '∧')))
      .map(operands => PropExpression.Conjunction(operands))
      .between(Parser.char('('), Parser.char(')'))

  def disjunction(expr: Parser[PropExpression]): Parser[PropExpression] =
    (PropTerm.parser | expr)
      .repSep(min = 2, sep = spaceSep(Parser.charIn('|', '∨')))
      .map(operands => PropExpression.Disjunction(operands))
      .between(Parser.char('('), Parser.char(')'))

  def materialImplication(expr: Parser[PropExpression]) =
    (for
      antecedent <- PropTerm.parser | expr
      _          <- spaceSep(Parser.string("->") | Parser.char('→'))
      consequent <- PropTerm.parser | expr
    yield PropExpression.MaterialImplication(antecedent, consequent))
      .between(Parser.char('('), Parser.char(')'))

  lazy val parser: Parser[PropExpression] = Parser.recursive { parser =>
    Parser.oneOf(List(negation, conjunction, disjunction, materialImplication).map(_(parser).backtrack))
  }
end PropExpression
