package printable

object PrintableInstances {
  implicit val printableString: Printable[String] = (a: String) => a

  implicit val printableInt: Printable[Int] = (a: Int) => a.toString

  implicit val printableCat: Printable[Cat] = (a: Cat) => s"${a.name} is a ${a.age} year-old ${a.color} cat"
}
