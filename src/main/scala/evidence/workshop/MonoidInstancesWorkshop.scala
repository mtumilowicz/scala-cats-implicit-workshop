package evidence.workshop

import cats.Monoid
import evidence.common.Order

object MonoidInstancesWorkshop {

  implicit val monoid: Monoid[Order] = new Monoid[Order] {
    def combine(o1: Order, o2: Order): Order = {
      // sum total cost, sum quantity
      null
    }

    def empty: Order = null // propose empty element
  }

}
