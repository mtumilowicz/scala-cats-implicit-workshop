package printable

import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import PrintableInstances._
import PrintableSyntax._
import WithCatsInstances._
import cats.implicits._

import java.io.ByteArrayOutputStream

class PrintableTest extends org.scalatest.FunSuite {

  test("extension method: format") {
    //    given
    val person = Person(name = "Buck Milligun", age = 33)

    //    expect
    person.format shouldBe "Buck Milligun is a 33 year-old"
  }

  test("extension method: show") {
    //    given
    val person = Person(name = "Buck Milligun", age = 33)

    //    expect
    person.show shouldBe "Buck Milligun is a 33 year-old"
  }

  test("extension method: print") {
    //    given
    val person = Person(name = "Buck Milligun", age = 33)

    //    when
    val outCapture = new ByteArrayOutputStream
    Console.withOut(outCapture) {
      person.print
    }

    // then
    outCapture.toString("UTF-8") shouldBe "Buck Milligun is a 33 year-old"
  }

  test("companion object method: format") {
    //    given
    val person = Person(name = "Buck Milligun", age = 33)

    //    expect
    Printable.format(person) shouldBe "Buck Milligun is a 33 year-old"
  }

  test("companion object method: print") {
    //    given
    val person = Person(name = "Buck Milligun", age = 33)

    //    when
    val outCapture = new ByteArrayOutputStream
    Console.withOut(outCapture) {
      Printable.print(person)
    }

    // then
    outCapture.toString("UTF-8") shouldBe "Buck Milligun is a 33 year-old"
  }

}
