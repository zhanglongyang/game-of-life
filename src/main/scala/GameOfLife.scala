import com.thoughtworks.binding.Binding.{BindingSeq, Var, Vars}
import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.raw.Event
import org.scalajs.dom.{Node, document}

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object GameOfLife extends JSApp {

  case class Location(x: Var[Integer], y: Var[Integer])

  case class Cell(status: Var[String], location: Location)

  val lives = {
    Vars(
      Vars(
        Cell(Var("0"), Location(Var(0), Var(0))),
        Cell(Var("1"), Location(Var(1), Var(0))),
        Cell(Var("0"), Location(Var(2), Var(0))),
        Cell(Var("1"), Location(Var(3), Var(0))),
        Cell(Var("0"), Location(Var(4), Var(0)))),
      Vars(
        Cell(Var("0"), Location(Var(0), Var(1))),
        Cell(Var("1"), Location(Var(1), Var(1))),
        Cell(Var("0"), Location(Var(2), Var(1))),
        Cell(Var("0"), Location(Var(3), Var(1))),
        Cell(Var("0"), Location(Var(4), Var(1)))),
      Vars(
        Cell(Var("0"), Location(Var(0), Var(2))),
        Cell(Var("0"), Location(Var(1), Var(2))),
        Cell(Var("0"), Location(Var(2), Var(2))),
        Cell(Var("1"), Location(Var(3), Var(2))),
        Cell(Var("0"), Location(Var(4), Var(2)))),
      Vars(
        Cell(Var("0"), Location(Var(0), Var(3))),
        Cell(Var("1"), Location(Var(1), Var(3))),
        Cell(Var("1"), Location(Var(2), Var(3))),
        Cell(Var("1"), Location(Var(3), Var(3))),
        Cell(Var("0"), Location(Var(4), Var(3)))),
      Vars(
        Cell(Var("0"), Location(Var(0), Var(4))),
        Cell(Var("0"), Location(Var(1), Var(4))),
        Cell(Var("0"), Location(Var(2), Var(4))),
        Cell(Var("1"), Location(Var(3), Var(4))),
        Cell(Var("0"), Location(Var(4), Var(4))))
    )
  }

  @dom
  def world: Binding[BindingSeq[Node]] = {
    <table border="1" cellPadding="5">
      <tbody>
        {for (row <- lives) yield {
        <tr style="text-align: center">
          {row.map { cell =>
          <td style="width: 20px; height: 20px">
            <a href="#" onclick={event: Event =>
              cell.status := "0"
            }>
              {cell.status.bind}
            </a>
          </td>
        }}
        </tr>
      }}
      </tbody>
    </table>
    <br></br>
    <button onclick={event: Event =>
      lives.get += Vars(
        Cell(Var("1"), Location(Var(0), Var(5))),
        Cell(Var("0"), Location(Var(1), Var(5))),
        Cell(Var("1"), Location(Var(2), Var(5))),
        Cell(Var("0"), Location(Var(3), Var(5))),
        Cell(Var("1"), Location(Var(4), Var(5)))
      )}>
      Evolve
    </button>
  }


  @JSExport
  override def main(): Unit = {
    dom.render(document.body, world)
  }
}
