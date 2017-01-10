package example

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport

object Hello extends JSApp with App {

  @JSExport
  override def main(): Unit = println("Hello World!")
}
