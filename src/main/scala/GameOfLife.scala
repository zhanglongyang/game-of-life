import com.thoughtworks.binding.Binding.{BindingSeq, Var}
import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.raw.Event
import org.scalajs.dom.{Node, document}

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport
import scala.util.Random

object GameOfLife extends JSApp {

  case class Location(x: Integer, y: Integer) {
    def isNeighbourOf(loc: Location): Boolean = {
      (Math.abs(this.x - loc.x) <= 1 && Math.abs(this.y - loc.y) <= 1) && !(this.equals(loc))
    }
  }

  case class Cell(status: Var[String], location: Location) {
    def isAlive: Boolean = this.status.get.eq("1")

//    def aliveNeighbours: BindingSeq[Cell] = {
//      for (
//        row <- data.cells;
//        cell <- row
//        if cell.isAlive && this.location.isNeighbourOf(cell.location)
//      ) yield {
//        cell
//      }
//    }
  }

  case class World(cells: List[List[Cell]])

  val size = 9

  val data: World = World(
    List(
      List(
        Cell(Var(randomStatus), Location(0, 0)),
        Cell(Var(randomStatus), Location(0, 1))
      ),
      List(
        Cell(Var(randomStatus), Location(1, 0)),
        Cell(Var(randomStatus), Location(1, 1))
      )
    )
  )

  def randomStatus: String = {
    Random.nextInt(7).toString
  }

  @dom
  def world: Binding[BindingSeq[Node]] = {
    <table border="1" cellPadding="5">
      {
        import scalaz.std.list._
        data.cells.map(row => {
          <tr>
            {
              row.map(cell => {
                <td>{cell.status.bind}</td>
              })
            }
          </tr>
        })
      }
    </table>

    <br></br>

    <button onclick={event: Event =>

      }>
      Evolve
    </button>
  }

  @JSExport
  override def main(): Unit = {
    dom.render(document.body, world)
  }
}

