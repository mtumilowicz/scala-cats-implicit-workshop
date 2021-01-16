package json.workshop

object ToPrettyJsonSyntaxWorkshop {

  implicit class ToPrettyJsonOps[A](value: A) {
    // use  ToPrettyJsonWorkshop.prettyJson
    def toPrettyJson(level: Int = 0)(implicit printable: ToPrettyJsonWorkshop[A]): String = null
  }

}
