package printable.workshop

import printable.common.Car

object PrintableInstancesWorkshop {
  implicit val printableString: PrintableWorkshop[String] = null // identity

  implicit val printableInt: PrintableWorkshop[Int] = null // convert to String

  implicit val printableCat: PrintableWorkshop[Car] = null // convert to String: this carName is carAge years old
}
