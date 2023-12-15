package dev.librecybernetics.data

import org.scalatest.matchers.should.Matchers.*
import org.scalatest.wordspec.AnyWordSpec

class PropTermSpec extends AnyWordSpec {
  "constants" should {
    "false" in {
      PropTerm.parser.shouldParse("false", PropTerm.False)
      PropTerm.parser.shouldParse("⊥", PropTerm.False)
    }

    "true" in {
      PropTerm.parser.shouldParse("true", PropTerm.True)
      PropTerm.parser.shouldParse("⊤", PropTerm.True)
    }
  }

  "variables" should {
    "parse" in {
      PropTerm.parser.shouldParse("a", PropTerm.PropVariable("a"))
      PropTerm.parser.shouldParse("b", PropTerm.PropVariable("b"))
      PropTerm.parser.shouldParse("c", PropTerm.PropVariable("c"))
    }
  }
}
