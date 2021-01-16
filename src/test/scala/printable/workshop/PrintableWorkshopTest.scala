package printable.workshop

import cats.implicits._
import org.scalatest.Ignore
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper
import printable.workshop.PrintableInstancesWorkshop._
import printable.workshop.PrintableSyntaxWorkshop._
import printable.workshop.WithCatsInstancesWorkshop._
import printable.common.Car

import java.io.ByteArrayOutputStream

@Ignore
class PrintableWorkshopTest extends org.scalatest.FunSuite {

  test("extension method: format") {
    //    given
    val car = Car(name = "ford", age = 33)

    //    expect
    car.format shouldBe "this ford is 33 years old"
  }

  test("extension method: show") {
    //    given
    val car = Car(name = "ford", age = 33)

    //    expect
    car.show shouldBe "this ford is 33 years old"
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
    outCapture.toString("UTF-8") shouldBe "this ford is 33 years old"
  }

  test("companion object method: format") {
    //    given
    val car = Car(name = "ford", age = 33)

    //    expect
    PrintableWorkshop.format(car) shouldBe "this ford is 33 years old"
  }

  test("companion object method: print") {
    //    given
    val car = Car(name = "ford", age = 33)

    //    when
    val outCapture = new ByteArrayOutputStream
    Console.withOut(outCapture) {
      PrintableWorkshop.print(car)
    }

    // then
    outCapture.toString("UTF-8") shouldBe "this ford is 33 years old"
  }

}
