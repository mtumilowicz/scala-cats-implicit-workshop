package printable.workshop

trait PrintableWorkshop[A] {

  def format(a: A): String

}

object PrintableWorkshop {

  def print[A](a: A)(implicit printable: PrintableWorkshop[A]): Unit = null // use format and Predef.print

  def format[A](a: A)(implicit printable: PrintableWorkshop[A]): String = null // use printable.format
}