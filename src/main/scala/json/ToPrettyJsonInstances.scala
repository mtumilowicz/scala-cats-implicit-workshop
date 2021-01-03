package json

import json.ToPrettyJsonSyntax._

object ToPrettyJsonInstances {

  implicit val toPrettyJsonAddress: ToPrettyJson[Address] = (address: Address, level: Int) => {
    val (outdent, indent) = ToPrettyJson.indentation(level)
    s"""{
       |${indent}"street": "${address.street}",
       |${indent}"city": "${address.city}"
       |$outdent}""".stripMargin
  }

  implicit val toPrettyJsonPerson: ToPrettyJson[Person] = (person: Person, level: Int) => {
    val (outdent, indent) = ToPrettyJson.indentation(level)
    s"""{
       |${indent}"name": "${person.name}",
       |${indent}"address": ${person.address.toPrettyJson(level + 1)}
       |$outdent}""".stripMargin
  }

}
