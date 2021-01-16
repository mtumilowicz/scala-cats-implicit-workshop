package printable.workshop

object PrintableSyntaxWorkshop {

  implicit class PrintableOps[A](value: A) {
    def format(implicit printable: PrintableWorkshop[A]): String = null // use PrintableWorkshop.format

    def print(implicit printable: PrintableWorkshop[A]): Unit = // use PrintableWorkshop.print
  }

}
