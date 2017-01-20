package pattern

import com.thoughtworks.binding.Binding.Var
import model.{Cell, Location}

import scala.collection.immutable.TreeMap

object Oscillators {
  implicit val size: Integer = 5

  def blinker: TreeMap[Location, Cell] = {
    TreeMap(
      (Location(0, 0), Cell(Var("-1"))),
      (Location(0, 1), Cell(Var("-1"))),
      (Location(0, 2), Cell(Var("-1"))),
      (Location(0, 3), Cell(Var("-1"))),
      (Location(0, 4), Cell(Var("-1"))),
      (Location(1, 0), Cell(Var("-1"))),
      (Location(1, 1), Cell(Var("-1"))),
      (Location(1, 2), Cell(Var("-1"))),
      (Location(1, 3), Cell(Var("-1"))),
      (Location(1, 4), Cell(Var("-1"))),
      (Location(2, 0), Cell(Var("-1"))),
      (Location(2, 1), Cell(Var("1"))),
      (Location(2, 2), Cell(Var("1"))),
      (Location(2, 3), Cell(Var("1"))),
      (Location(2, 4), Cell(Var("-1"))),
      (Location(3, 0), Cell(Var("-1"))),
      (Location(3, 1), Cell(Var("-1"))),
      (Location(3, 2), Cell(Var("-1"))),
      (Location(3, 3), Cell(Var("-1"))),
      (Location(3, 4), Cell(Var("-1"))),
      (Location(4, 0), Cell(Var("-1"))),
      (Location(4, 1), Cell(Var("-1"))),
      (Location(4, 2), Cell(Var("-1"))),
      (Location(4, 3), Cell(Var("-1"))),
      (Location(4, 4), Cell(Var("-1")))
    )
  }

}
