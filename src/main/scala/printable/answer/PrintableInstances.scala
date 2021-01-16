package printable.answer

import printable.common.Car

object PrintableInstances {
  implicit val printableString: Printable[String] = (a: String) => a

  implicit val printableInt: Printable[Int] = (a: Int) => a.toString

  implicit val printableCat: Printable[Car] = (a: Car) => s"this ${a.name} is ${a.age} years old"
}
