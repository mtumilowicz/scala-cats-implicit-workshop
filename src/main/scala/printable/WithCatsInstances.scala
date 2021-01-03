package printable

import cats._

object WithCatsInstances {

  implicit val personShow: Show[Person] =
    Show.show(person => s"${person.name} is a ${person.age} year-old")

}
