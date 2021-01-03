package json

import json.PersonOps._

object Intro extends App {
  val a = Address(street = "1 Scala Lane", city = "Anytown")
  val p = Person(name = "Buck Trends", a)
  println(a.prettyJson())
  println()
  println(p.prettyJson())
}
