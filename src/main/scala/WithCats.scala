import cats._
import cats.implicits._

object WithCats extends App {

  implicit val catShow: Show[Cat] =
    Show.show(cat => s"${cat.name} is a ${cat.age} year-old ${cat.color} cat")

  val cat = Cat("A", 1, "B")

  cat.show

}
