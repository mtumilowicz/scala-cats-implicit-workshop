package json.answer

trait ToPrettyJson[A] {

  def prettyJson(a: A, level: Int = 0): String
}

object ToPrettyJson {
  val INDENTATION = " "

  def prettyJson[A](a: A, level: Int = 0)(implicit toPrettyJson: ToPrettyJson[A]): String =
    toPrettyJson.prettyJson(a, level)

  def indentation(level: Int = 0): (String, String) =
    (INDENTATION * level, INDENTATION * (level + 1))
}