package example

import com.thoughtworks.binding.Binding.{Var, Vars}
import com.thoughtworks.binding.{Binding, dom}
import org.scalajs.dom.document
import org.scalajs.dom.html.Table

import scala.scalajs.js.annotation.JSExport

@JSExport
object BindingScalaDemo {

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
        {
          for (contact <- data) yield {
            <tr>
              <td>{contact.name.bind}</td>
              <td>{contact.email.bind}</td>
            </tr>
          }
        }
      </tbody>
    </table>
  }

  @JSExport
  def main(): Unit = {
    dom.render(document.body, table)
  }

}
