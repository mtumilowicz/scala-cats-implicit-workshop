package printable

import cats._
import cats.implicits._
import printable.Person

object WithCats extends App {

  implicit val catShow: Show[Person] =
    Show.show(cat => s"${cat.name} is a ${cat.age} year-old")

  val cat = Person(name = "A", age = 1)

  cat.show

}
