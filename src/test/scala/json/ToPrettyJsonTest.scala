package json

import json.ToPrettyJsonInstances._
import json.ToPrettyJsonSyntax._
import org.scalatest.matchers.should.Matchers.convertToAnyShouldWrapper

class ToPrettyJsonTest extends org.scalatest.FunSuite {

  test("companion object method: address toPrettyJson") {
    //    given
    val address = Address(street = "Tamka 4", city = "Warsaw")

    //    expect
    ToPrettyJson.prettyJson(address) shouldBe s"""{
                                       | "street": "Tamka 4",
                                       | "city": "Warsaw"
                                       |}""".stripMargin
  }

  test("companion object method: person toPrettyJson") {
    //    given
    val address = Address(street = "Tamka 4", city = "Warsaw")
    val person = Person(name = "Buck Milligun", address = address)

    //    expect
    ToPrettyJson.prettyJson(person) shouldBe s"""{
                                      | "name": "Buck Milligun",
                                      | "address": {
                                      |  "street": "Tamka 4",
                                      |  "city": "Warsaw"
                                      | }
                                      |}""".stripMargin
  }

  test("extension method: address toPrettyJson") {
    //    given
    val address = Address(street = "Tamka 4", city = "Warsaw")

    //    expect
    address.toPrettyJson() shouldBe s"""{
                                       | "street": "Tamka 4",
                                       | "city": "Warsaw"
                                       |}""".stripMargin
  }

  test("extension method: person toPrettyJson") {
    //    given
    val address = Address(street = "Tamka 4", city = "Warsaw")
    val person = Person(name = "Buck Milligun", address = address)

    //    expect
    person.toPrettyJson() shouldBe s"""{
                                      | "name": "Buck Milligun",
                                      | "address": {
                                      |  "street": "Tamka 4",
                                      |  "city": "Warsaw"
                                      | }
                                      |}""".stripMargin
  }

}
