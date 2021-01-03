package printable

import PrintableInstances._
import PrintableSyntax._
import printable.Person

import java.io.{ByteArrayOutputStream, StringReader}

object Intro extends App {

  val cat = Person(name = "A", age = 1)

  Printable.print(cat)

  cat.print

  val outCapture = new ByteArrayOutputStream
  Console.withOut(outCapture) {
    println("Hello world")
  }

  outCapture.toString.print

}