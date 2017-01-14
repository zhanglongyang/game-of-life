import com.thoughtworks.binding.Binding.{Var, Vars}
import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.document
import org.scalajs.dom.html.Table

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object GameOfLife extends JSApp {

  case class Location(x: Var[Integer], y: Var[Integer])
  case class Cell(status: Var[String], location: Location)

  @dom
  def space: Binding[Table] = {
    <table border="1" cellPadding="5">
      <tbody>
        {for (row <- lives) yield {
        <tr style="text-align: center">
          {row.map { cell =>
          <td style="width: 20px; height: 20px">
            {cell.status.bind}
          </td>
          }}
        </tr>
        }}
      </tbody>
    </table>
  }

  def lives: Vars[Vars[Cell]] = {
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

  @JSExport
  override def main(): Unit = {
    dom.render(document.body, space)
  }
}
