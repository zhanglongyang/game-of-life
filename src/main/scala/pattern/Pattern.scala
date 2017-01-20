package pattern

import com.thoughtworks.binding.Binding.Var
import model.{Cell, Location}

import scala.collection.immutable.TreeMap
import scala.util.Random

object Pattern {
  implicit val size: Integer = 20

  def random: TreeMap[Location, Cell] = {
    TreeMap(({
      for (i <- 0 to size - 1; j <- 0 to size - 1) yield {
        (Location(i, j), Cell(Var({
          Random.nextInt(7).toString
        })))
      }
    }): _*)
  }
}
