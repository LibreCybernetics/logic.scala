package dev.librecybernetics.data

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class PropExpressionSpec extends AnyWordSpec {
  "simple expressions" should {
    "negation" in {
      PropExpression.parser.shouldParse("!false", PropExpression.Negation(PropTerm.False))
      PropExpression.parser.shouldParse("!⊥", PropExpression.Negation(PropTerm.False))
      PropExpression.parser.shouldParse("!true", PropExpression.Negation(PropTerm.True))
      PropExpression.parser.shouldParse("!⊤", PropExpression.Negation(PropTerm.True))
      PropExpression.parser.shouldParse("!a", PropExpression.Negation(PropTerm.PropVariable("a")))
    }

    "conjunction" in {
      PropExpression.parser.shouldParse("(false ∧ false)", PropExpression.Conjunction(PropTerm.False, PropTerm.False))
      PropExpression.parser.shouldParse("(⊥ ∧ ⊥)", PropExpression.Conjunction(PropTerm.False, PropTerm.False))
      PropExpression.parser.shouldParse("(false ∧ true)", PropExpression.Conjunction(PropTerm.False, PropTerm.True))
      PropExpression.parser.shouldParse("(⊥ ∧ ⊤)", PropExpression.Conjunction(PropTerm.False, PropTerm.True))
      PropExpression.parser.shouldParse("(true ∧ false)", PropExpression.Conjunction(PropTerm.True, PropTerm.False))
      PropExpression.parser.shouldParse("(⊤ ∧ ⊥)", PropExpression.Conjunction(PropTerm.True, PropTerm.False))
      PropExpression.parser.shouldParse("(true ∧ true)", PropExpression.Conjunction(PropTerm.True, PropTerm.True))
      PropExpression.parser.shouldParse("(⊤ ∧ ⊤)", PropExpression.Conjunction(PropTerm.True, PropTerm.True))
      PropExpression.parser.shouldParse(
        "(a ∧ b)",
        PropExpression.Conjunction(PropTerm.PropVariable("a"), PropTerm.PropVariable("b"))
      )
      PropExpression.parser.shouldParse(
        "(a ∧ b ∧ c)",
        PropExpression.Conjunction(PropTerm.PropVariable("a"), PropTerm.PropVariable("b"), PropTerm.PropVariable("c"))
      )
      PropExpression.parser.shouldParse(
        "(a ∧ b ∧ c ∧ d)",
        PropExpression.Conjunction(
          PropTerm.PropVariable("a"),
          PropTerm.PropVariable("b"),
          PropTerm.PropVariable("c"),
          PropTerm.PropVariable("d")
        )
      )
      PropExpression.parser.shouldParse("(false & false)", PropExpression.Conjunction(PropTerm.False, PropTerm.False))
      PropExpression.parser.shouldParse("(⊥ & ⊥)", PropExpression.Conjunction(PropTerm.False, PropTerm.False))
      PropExpression.parser.shouldParse("(false & true)", PropExpression.Conjunction(PropTerm.False, PropTerm.True))
      PropExpression.parser.shouldParse("(⊥ & ⊤)", PropExpression.Conjunction(PropTerm.False, PropTerm.True))
      PropExpression.parser.shouldParse("(true & false)", PropExpression.Conjunction(PropTerm.True, PropTerm.False))
      PropExpression.parser.shouldParse("(⊤ & ⊥)", PropExpression.Conjunction(PropTerm.True, PropTerm.False))
      PropExpression.parser.shouldParse("(true & true)", PropExpression.Conjunction(PropTerm.True, PropTerm.True))
      PropExpression.parser.shouldParse("(⊤ & ⊤)", PropExpression.Conjunction(PropTerm.True, PropTerm.True))
      PropExpression.parser.shouldParse(
        "(a & b)",
        PropExpression.Conjunction(PropTerm.PropVariable("a"), PropTerm.PropVariable("b"))
      )
      PropExpression.parser.shouldParse(
        "(a & b & c)",
        PropExpression.Conjunction(PropTerm.PropVariable("a"), PropTerm.PropVariable("b"), PropTerm.PropVariable("c"))
      )
      PropExpression.parser.shouldParse(
        "(a & b & c & d)",
        PropExpression.Conjunction(
          PropTerm.PropVariable("a"),
          PropTerm.PropVariable("b"),
          PropTerm.PropVariable("c"),
          PropTerm.PropVariable("d")
        )
      )
    }

    "disjunction" in {
      PropExpression.parser.shouldParse("(false ∨ false)", PropExpression.Disjunction(PropTerm.False, PropTerm.False))
      PropExpression.parser.shouldParse("(⊥ ∨ ⊥)", PropExpression.Disjunction(PropTerm.False, PropTerm.False))
      PropExpression.parser.shouldParse("(false ∨ true)", PropExpression.Disjunction(PropTerm.False, PropTerm.True))
      PropExpression.parser.shouldParse("(⊥ ∨ ⊤)", PropExpression.Disjunction(PropTerm.False, PropTerm.True))
      PropExpression.parser.shouldParse("(true ∨ false)", PropExpression.Disjunction(PropTerm.True, PropTerm.False))
      PropExpression.parser.shouldParse("(⊤ ∨ ⊥)", PropExpression.Disjunction(PropTerm.True, PropTerm.False))
      PropExpression.parser.shouldParse("(true ∨ true)", PropExpression.Disjunction(PropTerm.True, PropTerm.True))
      PropExpression.parser.shouldParse("(⊤ ∨ ⊤)", PropExpression.Disjunction(PropTerm.True, PropTerm.True))
      PropExpression.parser.shouldParse(
        "(a ∨ b)",
        PropExpression.Disjunction(PropTerm.PropVariable("a"), PropTerm.PropVariable("b"))
      )
      PropExpression.parser.shouldParse(
        "(c ∨ d)",
        PropExpression.Disjunction(PropTerm.PropVariable("c"), PropTerm.PropVariable("d"))
      )
      PropExpression.parser.shouldParse(
        "(a ∨ b ∨ c)",
        PropExpression.Disjunction(PropTerm.PropVariable("a"), PropTerm.PropVariable("b"), PropTerm.PropVariable("c"))
      )
      PropExpression.parser.shouldParse(
        "(a ∨ b ∨ c ∨ d)",
        PropExpression.Disjunction(
          PropTerm.PropVariable("a"),
          PropTerm.PropVariable("b"),
          PropTerm.PropVariable("c"),
          PropTerm.PropVariable("d")
        )
      )
      PropExpression.parser.shouldParse("(false | false)", PropExpression.Disjunction(PropTerm.False, PropTerm.False))
      PropExpression.parser.shouldParse("(⊥ | ⊥)", PropExpression.Disjunction(PropTerm.False, PropTerm.False))
      PropExpression.parser.shouldParse("(false | true)", PropExpression.Disjunction(PropTerm.False, PropTerm.True))
      PropExpression.parser.shouldParse("(⊥ | ⊤)", PropExpression.Disjunction(PropTerm.False, PropTerm.True))
      PropExpression.parser.shouldParse("(true | false)", PropExpression.Disjunction(PropTerm.True, PropTerm.False))
      PropExpression.parser.shouldParse("(⊤ | ⊥)", PropExpression.Disjunction(PropTerm.True, PropTerm.False))
      PropExpression.parser.shouldParse("(true | true)", PropExpression.Disjunction(PropTerm.True, PropTerm.True))
      PropExpression.parser.shouldParse("(⊤ | ⊤)", PropExpression.Disjunction(PropTerm.True, PropTerm.True))
      PropExpression.parser.shouldParse(
        "(a | b)",
        PropExpression.Disjunction(PropTerm.PropVariable("a"), PropTerm.PropVariable("b"))
      )
      PropExpression.parser.shouldParse(
        "(a | b | c)",
        PropExpression.Disjunction(PropTerm.PropVariable("a"), PropTerm.PropVariable("b"), PropTerm.PropVariable("c"))
      )
      PropExpression.parser.shouldParse(
        "(a | b | c | d)",
        PropExpression.Disjunction(
          PropTerm.PropVariable("a"),
          PropTerm.PropVariable("b"),
          PropTerm.PropVariable("c"),
          PropTerm.PropVariable("d")
        )
      )
    }

    "material implication" in {
      PropExpression.parser.shouldParse(
        "(false → false)",
        PropExpression.MaterialImplication(PropTerm.False, PropTerm.False)
      )
      PropExpression.parser.shouldParse("(⊥ → ⊥)", PropExpression.MaterialImplication(PropTerm.False, PropTerm.False))
      PropExpression.parser.shouldParse(
        "(false → true)",
        PropExpression.MaterialImplication(PropTerm.False, PropTerm.True)
      )
      PropExpression.parser.shouldParse("(⊥ → ⊤)", PropExpression.MaterialImplication(PropTerm.False, PropTerm.True))
      PropExpression.parser.shouldParse(
        "(true → false)",
        PropExpression.MaterialImplication(PropTerm.True, PropTerm.False)
      )
      PropExpression.parser.shouldParse("(⊤ → ⊥)", PropExpression.MaterialImplication(PropTerm.True, PropTerm.False))
      PropExpression.parser.shouldParse(
        "(true → true)",
        PropExpression.MaterialImplication(PropTerm.True, PropTerm.True)
      )
      PropExpression.parser.shouldParse("(⊤ → ⊤)", PropExpression.MaterialImplication(PropTerm.True, PropTerm.True))
      PropExpression.parser.shouldParse(
        "(a → b)",
        PropExpression.MaterialImplication(PropTerm.PropVariable("a"), PropTerm.PropVariable("b"))
      )
    }
  }
}
