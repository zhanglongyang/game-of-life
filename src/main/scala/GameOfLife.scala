import com.thoughtworks.binding.Binding.Var
import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.document
import org.scalajs.dom.html.Table

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
  }

  case class World(cells: List[Cell])

  val size = 20

  val data = World(({
    for (i <- 0 to size - 1; j <- 0 to size - 1) yield {
      Cell(Var(randomStatus), Location(i, j))
    }
  }).toList)

  def randomStatus: String = {
    Random.nextInt(7).toString
  }

  @dom
  def world: Binding[Table] = {
    <table border="1" cellPadding="5">
      {
        import scalaz.std.list._
        data.cells.grouped(size).toList.map(row => {
          <tr>
            {
              row.map(cell => {
                <td style={s"width: 20px; height: 20px; ${if (cell.status.get.eq("1")) "background-color: black"}"}></td>
              })
            }
          </tr>
        })
      }
    </table>
  }

  @JSExport
  override def main(): Unit = {
    dom.render(document.body, world)
  }
}

