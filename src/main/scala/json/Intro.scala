package json

import json.PersonOps._

object Intro extends App {
  val a = Address("1 Scala Lane", "Anytown")
  val p = Person("Buck Trends", a)
  println(a.toJSON())
  println()
  println(p.toJSON())
}
