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
    def isValid: Boolean = {
      (this.x >= 0 && this.x < size) && (this.y >= 0 && this.y < size)
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
    def isLive: Boolean = this.status.get.eq("1")
  }

  case class World(cellsMap: TreeMap[Location, Cell])

  val size = 20

  val data = World(TreeMap(({
    for (i <- 0 to size - 1; j <- 0 to size - 1) yield {
      (Location(i, j),  Cell(Var({
        Random.nextInt(7).toString
      })))
    }
  }): _*))

  @dom def universe: Binding[Table] = {
    <table border="1" cellPadding="5">
      {
        import scalaz.std.list._
        for (row <- data.cellsMap.grouped(size).toList) yield {
          <tr style="text-align: center">
            {
              for (cellMap <- row.toList) yield {
                <td style={s"width: 20px; height: 20px; ${
                  if (cellMap._2.status.bind == "1") "background-color: grey"
                  else if (cellMap._2.status.bind == "-1") "background-color: red"}"
                }>
                  {cellMap._2.status.bind}
                </td>
              }
            }
          </tr>
        }
      }
    </table>
  }

  def judge(cell: Cell, location: Location): String = {
    val liveNeighbours = location.neighbours.filter(data.cellsMap.get(_).get.isLive);

    cell.status.get match {
      case "1" => liveNeighbours.size match {
        case n if n < 2 => "-1"
        case n if n < 4 => "1"
        case n if n > 3 => "-1"
      }
      case "-1" if liveNeighbours.size == 3 => "1"
      case _ => cell.status.get
    }
  }

  @dom def evolve: Binding[Button] = {
    <button onclick={e: Event => {
      data.cellsMap
        .map{case (loc, cell) => (cell, judge(cell, loc))}
        .foreach(result => (result._1.status := result._2))
    }}>Evolve</button>
  }

  @dom def render: Binding[Div] = {
    <div>
      {universe.bind}
      {evolve.bind}
    </div>
  }

  @JSExport
  override def main(): Unit = {
    dom.render(document.body, render)
  }
}

