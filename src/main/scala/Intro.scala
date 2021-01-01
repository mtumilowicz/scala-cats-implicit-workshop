import PrintableInstances._
import PrintableSyntax._

object Intro extends App {

  val cat = Cat("A", 1, "B")

  Printable.print(cat)

  cat.print

}
