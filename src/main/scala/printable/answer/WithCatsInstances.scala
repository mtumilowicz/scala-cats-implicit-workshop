package printable.answer

import cats.Show
import printable.common.Car

object WithCatsInstances {

  implicit val carShow: Show[Car] =
    Show.show(car => s"this ${car.name} is ${car.age} years old")

}
