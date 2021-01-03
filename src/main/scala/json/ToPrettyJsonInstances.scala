package json

import json.ToPrettyJsonSyntax._

object ToPrettyJsonInstances {

  implicit val toPrettyJsonAddress: ToPrettyJson[Address] = new ToPrettyJson[Address] {
    def prettyJson(address: Address, level: Int = 0): String = {
      val (outdent, indent) = indentation(level)
      s"""{
         |${indent}"street": "${address.street}",
         |${indent}"city": "${address.city}"
         |$outdent}""".stripMargin
    }
  }

  implicit val toPrettyJsonPerson: ToPrettyJson[Person] = new ToPrettyJson[Person] {
    def prettyJson(person: Person, level: Int = 0): String = {
      val (outdent, indent) = indentation(level)
      s"""{
         |${indent}"name": "${person.name}",
         |${indent}"address": ${person.address.toPrettyJson(level + 1)}
         |$outdent}""".stripMargin
    }
  }

}
