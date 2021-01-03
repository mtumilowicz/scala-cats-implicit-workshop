package evidence

import cats.Monoid

object MonoidInstances {

  implicit val monoid: Monoid[Order] = new Monoid[Order] {
    def combine(o1: Order, o2: Order): Order =
      Order(
        o1.totalCost + o2.totalCost,
        o1.quantity + o2.quantity
      )

    def empty: Order = Order(0, 0)
  }

}
