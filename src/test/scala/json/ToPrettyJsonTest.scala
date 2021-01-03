package json

import json.ToPrettyJsonInstances._
import json.ToPrettyJsonSyntax._
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class ToPrettyJsonTest extends org.scalatest.FunSuite {

  test("address toPrettyJson") {
    //    given
    val address = Address(street = "Tamka 4", city = "Warsaw")

    //    expect
    address.toPrettyJson() shouldBe s"""{
                                       | "street": "Tamka 4",
                                       | "city": "Warsaw"
                                       |}""".stripMargin
  }

  test("person toPrettyJson") {
    //    given
    val address = Address(street = "Tamka 4", city = "Warsaw")
    val person = Person(name = "Buck Mulligun", address = address)

    //    expect
    person.toPrettyJson() shouldBe s"""{
                                      | "name": "Buck Mulligun",
                                      | "address": {
                                      |  "street": "Tamka 4",
                                      |  "city": "Warsaw"
                                      | }
                                      |}""".stripMargin
  }

}
