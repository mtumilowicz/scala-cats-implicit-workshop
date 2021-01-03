package json

object PersonOps {

  implicit class PersonToPrettyJson(person: Person) extends ToPrettyJson {
    def prettyJson(level: Int = 0): String = {
      val (outdent, indent) = indentation(level)
      s"""{
         |${indent}"name": "${person.name}",
         |${indent}"address": ${person.address.prettyJson(level + 1)}
         |$outdent}""".stripMargin
    }
  }

  implicit class AddressToPrettyJson(address: Address) extends ToPrettyJson {
    def prettyJson(level: Int = 0): String = {
      val (outdent, indent) = indentation(level)
      s"""{
         |${indent}"street": "${address.street}",
         |${indent}"city": "${address.city}"
         |$outdent}""".stripMargin
    }
  }

}
