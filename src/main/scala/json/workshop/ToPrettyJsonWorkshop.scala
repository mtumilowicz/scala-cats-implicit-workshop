package json.workshop

trait ToPrettyJsonWorkshop[A] {

  def prettyJson(a: A, level: Int = 0): String
}

object ToPrettyJsonWorkshop {
  val INDENTATION = " "

  def prettyJson[A](a: A, level: Int = 0)(implicit toPrettyJson: ToPrettyJsonWorkshop[A]): String = null
  // toPrettyJson.prettyJson

  def indentation(level: Int = 0): (String, String) = null
  // count: indent: x * level, outdent: x * (level + 1)
}