package json.workshop

import json.workshop.ToPrettyJsonSyntaxWorkshop._
import json.common.{Address, Person}

object ToPrettyJsonInstancesWorkshop {

  implicit val toPrettyJsonAddress: ToPrettyJsonWorkshop[Address] = (address: Address, level: Int) => {
    /*
    format:
    {
      street: ...,
      city: ...
    }
     */
    // hint: ToPrettyJsonWorkshop.indentation, s"""{...}""", stripMargin
    null
  }

  implicit val toPrettyJsonPerson: ToPrettyJsonWorkshop[Person] = (person: Person, level: Int) => {
    /*
    format:
    {
      name: ...,
      address: {
        street: ...,
        city: ...
      }

    }

     */
    // hint: ToPrettyJsonWorkshop.indentation, toPrettyJson(level + 1), s"""{...}""", stripMargin
    null
  }

}
