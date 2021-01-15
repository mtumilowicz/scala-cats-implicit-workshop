package printable.answer

import cats.implicits._
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import printable.answer.PrintableInstances._
import printable.answer.PrintableSyntax._
import printable.answer.WithCatsInstances._
import printable.common.Car

import java.io.ByteArrayOutputStream

class PrintableTest extends org.scalatest.FunSuite {

  test("extension method: format") {
    //    given
    val car = Car(name = "ford", age = 33)

    //    expect
    car.format shouldBe "this ford is a 33 years old"
  }

  test("extension method: show") {
    //    given
    val car = Car(name = "ford", age = 33)

    //    expect
    car.show shouldBe "this ford is a 33 years old"
  }

  test("extension method: print") {
    //    given
    val car = Car(name = "ford", age = 33)

    //    when
    val outCapture = new ByteArrayOutputStream
    Console.withOut(outCapture) {
      car.print
    }

    // then
    outCapture.toString("UTF-8") shouldBe "this ford is a 33 years old"
  }

  test("companion object method: format") {
    //    given
    val car = Car(name = "ford", age = 33)

    //    expect
    Printable.format(car) shouldBe "this ford is a 33 years old"
  }

  test("companion object method: print") {
    //    given
    val car = Car(name = "ford", age = 33)

    //    when
    val outCapture = new ByteArrayOutputStream
    Console.withOut(outCapture) {
      Printable.print(car)
    }

    // then
    outCapture.toString("UTF-8") shouldBe "this ford is a 33 years old"
  }

}
