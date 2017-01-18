import com.thoughtworks.binding.Binding.Var
import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.{Event, document}
import org.scalajs.dom.html.{Button, Div, Table}

import scala.collection.immutable.TreeMap
import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport
import scala.util.Random

object GameOfLife extends JSApp {

  case class Location(x: Integer, y: Integer) extends Ordered[Location] {
    def isNeighbourOf(loc: Location): Boolean = {
      (Math.abs(this.x - loc.x) <= 1 && Math.abs(this.y - loc.y) <= 1) && !(this.equals(loc))
    }

    def isValid: Boolean = {
      (this.x > 0 && this.x < size) && (this.y > 0 && this.y < size)
    }

    def neighbours: List[Location] = {
      List(
        Location(x - 1, y - 1),
        Location(x - 1, y),
        Location(x - 1, y + 1),
        Location(x, y - 1),
        Location(x, y + 1),
        Location(x + 1, y - 1),
        Location(x + 1, y),
        Location(x + 1, y + 1)
      ).filter(_.isValid)
    }

    override def compare(that: Location): Int = {
      (this.x - that.x, this.y - that.y) match {
        case (0, j) => j
        case (i, _) => i
      }
    }
  }

  case class Cell(status: Var[String]) {
    def isAlive: Boolean = this.status.get.eq("1")
  }

  case class World(cellsMap: TreeMap[Location, Cell])

  val size = 10

  val data = World(TreeMap(({
    for (i <- 0 to size - 1; j <- 0 to size - 1) yield {
      (Location(i, j),  Cell(Var(randomStatus)))
    }
  }): _*))

  def randomStatus: String = {
    Random.nextInt(7).toString
  }

  @dom def world: Binding[Table] = {
    <table border="1" cellPadding="5">
      {
        import scalaz.std.list._
        for (row <- data.cellsMap.grouped(size).toList) yield {
          <tr>
            {
              for (cellMap <- row.toList) yield {
                <td style={s"width: 20px; height: 20px; ${if (cellMap._2.status.bind.eq("1")) "background-color: black"}"}></td>
              }
            }
          </tr>
        }
      }
    </table>
  }

  @dom def evolve: Binding[Button] = {
    <button onclick={e: Event => {
      for (
        (loc, cell) <- data.cellsMap
//        if loc.neighbours.filter(data.cellsMap.get(_).get.status.eq("1")).size > 0
      ) yield {
        println(loc + "|" + cell.status.get)
        cell.status := "0"
      }
    }}>Evolve</button>
  }

  @dom def render: Binding[Div] = {
    <div>
      {world.bind}
      {evolve.bind}
    </div>
  }

  @JSExport
  override def main(): Unit = {
    dom.render(document.body, render)
  }
}

