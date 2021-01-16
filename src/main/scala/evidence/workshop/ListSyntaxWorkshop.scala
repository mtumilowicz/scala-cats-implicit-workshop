package evidence.workshop

import cats.Monoid
import cats.implicits.catsSyntaxSemigroup

object ListSyntaxWorkshop {

  implicit class ListOps[A: Monoid](list: List[A]) {
    // implement sum2 (simply sum elements in the list
    // hint: foldLeft, Monoid, |+|
  }

}
