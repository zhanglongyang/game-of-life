import com.thoughtworks.binding.Binding.{BindingSeq, Var, Vars}
import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.raw.Event
import org.scalajs.dom.{Node, document}

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport
import scala.util.Random

object GameOfLife extends JSApp {

  case class Location(x: Integer, y: Integer)

  case class Cell(status: Var[String], location: Location) {
    def isAlive: Boolean = this.status.get.eq("1")
  }

  case class World(cells: Vars[Vars[Cell]])

  val size = 9

  val data: World = World({
    var vv = Vars.empty[Vars[Cell]]
    for (i <- 0 to size) yield {
      var ww = Vars.empty[Cell]
      for (j <- 0 to size) yield {
        ww.get += Cell(Var(randomStatus), Location(i, j))
      }
      vv.get += ww
    }
    vv
  })

  def randomStatus: String = {
    Random.nextInt(7).toString
  }

  @dom
  def world: Binding[BindingSeq[Node]] = {
    <table border="1" cellPadding="5">
      {for (row <- data.cells) yield {
      <tr>
        {for (cell <- row) yield {
        <td style={s"width: 20px; height: 20px; ${if (cell.status.get.eq("1")) "background-color: black"}"}>
        </td>
      }}
      </tr>
    }}
    </table>
      <br></br>
      <button onclick={event: Event =>
        data.cells.get += Vars(
          Cell(Var("1"), Location(0, 5)),
          Cell(Var("0"), Location(1, 5)),
          Cell(Var("1"), Location(2, 5)),
          Cell(Var("0"), Location(3, 5)),
          Cell(Var("1"), Location(4, 5)))}>
        Evolve
      </button>
  }

  @JSExport
  override def main(): Unit = {
    dom.render(document.body, world)
  }
}

