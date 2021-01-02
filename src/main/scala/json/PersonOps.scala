package json

object PersonOps {

  implicit class PersonToJSON(person: Person) extends ToJSON {
    def toJSON(level: Int = 0): String = {
      val (outdent, indent) = indentation(level)
      s"""{
         |${indent}"name": "${person.name}",
         |${indent}"address": ${person.address.toJSON(level + 1)}
         |$outdent}""".stripMargin
    }
  }

  implicit class AddressToJSON(address: Address) extends ToJSON {
    def toJSON(level: Int = 0): String = {
      val (outdent, indent) = indentation(level)
      s"""{
         |${indent}"street": "${address.street}",
         |${indent}"city": "${address.city}"
         |$outdent}""".stripMargin
    }
  }

}
