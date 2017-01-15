package example

import org.scalajs.dom
import dom.document

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object Hello extends JSApp with App {

  @JSExport
  override def main(): Unit = {
    println("Hello World!")
    appendPar(document.body, greeting + " World")
  }

  def greeting: String = {
    "hello"
  }

  @JSExport
  def addClickedMessage(): Unit = {
    appendPar(document.body, "You clicked the button!")
  }

  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    targetNode.appendChild(parNode)
  }

}
