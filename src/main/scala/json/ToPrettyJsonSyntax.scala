package json

object ToPrettyJsonSyntax {

  implicit class ToPrettyJsonOps[A](value: A) {
    def toPrettyJson(level: Int = 0)(implicit printable: ToPrettyJson[A]): String = ToPrettyJson.prettyJson(value, level)
  }

}
