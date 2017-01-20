import com.thoughtworks.binding.{Binding, dom}
import model.{Cell, Location, Universe}
import org.scalajs.dom.{Event, document}
import org.scalajs.dom.html.{Button, Div, Table}
import pattern.{Oscillators, Pattern, StillLife}

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object GameOfLife extends JSApp {

//  val data = Universe(StillLife.block)
//  val data = Universe(Oscillators.blinker)
  val data = Universe(Pattern.random)

  @dom def universe: Binding[Table] = {
    <table border="1" cellPadding="5">
      {
        import scalaz.std.list._
        for (row <- data.cellsMap.groupBy(_._1.x).values.toList) yield {
          <tr style="text-align: center">
            {
              for (cellMap <- row.toList) yield {
                <td style={s"width: 20px; height: 20px; ${
                  if (cellMap._2.status.bind == "1") "background-color: black"}"
                }>
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
      <br></br>
      {evolve.bind}
    </div>
  }

  @JSExport
  override def main(): Unit = {
    dom.render(document.body, render)
  }
}

