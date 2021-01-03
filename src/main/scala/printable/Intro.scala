package printable

import PrintableInstances._
import PrintableSyntax._
import printable.Cat

object Intro extends App {

  val cat = Cat("A", 1, "B")

  Printable.print(cat)

  cat.print

}