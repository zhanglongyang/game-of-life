package example

import com.thoughtworks.binding.Binding.{BindingSeq, Constants, Var, Vars}
import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.{Node, document}
import org.scalajs.dom.html.Table

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object BindingScalaDemo extends JSApp {

  case class Contact(name: Var[String], email: Var[String])

  val data = Vars(
    Contact(Var("Steve"), Var("steve@apple.com")),
    Contact(Var("Bill"), Var("bill@microsoft.com"))
  )

  @dom
  def table: Binding[Table] = {
    <table border="1" cellPadding="5">
      <thead>
        <tr>
          <th>Name</th>
          <th>E-mail</th>
        </tr>
      </thead>
      <tbody>
        {for (contact <- data) yield {
        <tr>
          <td>
            {contact.name.bind}
          </td>
          <td>
            {contact.email.bind}
          </td>
        </tr>
      }}
      </tbody>
    </table>
  }

  @dom def header: Binding[Node] = {
    <header class="header">
      <h1>todos</h1>
      <input class="new-todo" autofocus={true} placeholder="What needs to be done?"/>
    </header>
  }

  val matrixData = List(1, 2, 3, 4, 5)

  @dom def matrix: Binding[Table] = {
    import scalaz.std.list._
    <table>
      <tr>
        {
          matrixData.map(i => <td>{i.toString}</td>)
        }
      </tr>
    </table>
  }

  @dom def render: Binding[Node] = {
    <div>
      {header.bind}
      {table.bind}
      {matrix.bind}
    </div>
  }

  @JSExport
  def main(): Unit = {
    dom.render(document.body, render)
  }

}
