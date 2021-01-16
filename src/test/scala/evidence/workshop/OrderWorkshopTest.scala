package evidence.workshop

import evidence.workshop.ListSyntaxWorkshop._
import evidence.workshop.MonoidInstancesWorkshop._
import evidence.common.Order
import org.scalatest.Ignore
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

@Ignore
class OrderWorkshopTest extends org.scalatest.FunSuite {

  test("sum list of orders") {
    //    given
    val orders = List(Order(totalCost = 1.1, quantity = 2), Order(totalCost = 5.5, quantity = 1))

    //    expect
//    orders.sum2() shouldBe Order(6.6, 3.0) uncomment
  }

}
