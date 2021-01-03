package evidence

import cats.Monoid
import cats.implicits.catsSyntaxSemigroup

object ListSyntax {

  implicit class ListOps[A: Monoid](list: List[A]) {
    def sum2(): A = {
      list.foldLeft(Monoid[A].empty)(_ |+| _)
    }
  }

}
