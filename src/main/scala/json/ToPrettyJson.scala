package json

trait ToPrettyJson[A] {

  def prettyJson(a: A, level: Int = 0): String
}

object ToPrettyJson {
  def prettyJson[A](a: A, level: Int = 0)(implicit toPrettyJson: ToPrettyJson[A]): String =
    toPrettyJson.prettyJson(a, level)

  val INDENTATION = " "

  def indentation(level: Int = 0): (String, String) =
    (INDENTATION * level, INDENTATION * (level + 1))
}