package printable.answer

object PrintableInstances {
  implicit val printableString: Printable[String] = (a: String) => a

  implicit val printableInt: Printable[Int] = (a: Int) => a.toString

  implicit val printableCat: Printable[Person] = (a: Person) => s"${a.name} is a ${a.age} year-old"
}
