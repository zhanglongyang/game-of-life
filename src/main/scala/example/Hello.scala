package example

import org.scalajs.dom
import dom.document

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object Hello extends JSApp with App {

  @JSExport
  override def main(): Unit = {
    println("Hello World!")
    appendPar(document.body, "Hello World")
  }

  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    targetNode.appendChild(parNode)
  }
}
