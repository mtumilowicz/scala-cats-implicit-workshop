package printable.workshop

import cats.Show
import printable.common.Car

object WithCatsInstancesWorkshop {

  implicit val carShow: Show[Car] = null // implement using Show.show

}
