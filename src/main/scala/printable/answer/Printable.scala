package printable.answer

trait Printable[A] {

  def format(a: A): String

}

object Printable {

  def print[A](a: A)(implicit printable: Printable[A]): Unit = Predef.print(format(a))

  def format[A](a: A)(implicit printable: Printable[A]): String = printable.format(a)
}