import com.thoughtworks.binding.Binding.{Var, Vars}
import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.document
import org.scalajs.dom.html.Table

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object GameOfLife extends JSApp {

  case class Life(status: Var[String])

  @dom
  def space: Binding[Table] = {
    <table border="1" cellPadding="5">
      <tbody>
        {for (row <- lives) yield {
        <tr>
          {row.map { life =>
          <td>
            {life.status.bind}
          </td>
          }}
        </tr>
        }}
      </tbody>
    </table>
  }

  def lives: Vars[Vars[Life]] = {
    Vars(
      Vars(Life(Var("0")), Life(Var("1")), Life(Var("0")), Life(Var("1")), Life(Var("0"))),
      Vars(Life(Var("0")), Life(Var("1")), Life(Var("0")), Life(Var("0")), Life(Var("0"))),
      Vars(Life(Var("0")), Life(Var("0")), Life(Var("0")), Life(Var("1")), Life(Var("0"))),
      Vars(Life(Var("0")), Life(Var("1")), Life(Var("1")), Life(Var("1")), Life(Var("0"))),
      Vars(Life(Var("0")), Life(Var("0")), Life(Var("0")), Life(Var("1")), Life(Var("0")))
    )
  }

  @JSExport
  override def main(): Unit = {
    dom.render(document.body, space)
  }
}
