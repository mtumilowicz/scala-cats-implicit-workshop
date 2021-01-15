package evidence.answer

import ListSyntax._
import MonoidInstances._
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class OrderTest extends org.scalatest.FunSuite {

  test("sum list of orders") {
    //    given
    val orders = List(Order(totalCost = 1.1, quantity = 2), Order(totalCost = 5.5, quantity = 1))

    //    expect
    orders.sum2() shouldBe Order(6.6, 3.0)
  }

}
