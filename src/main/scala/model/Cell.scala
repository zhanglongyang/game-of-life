package model

import com.thoughtworks.binding.Binding.Var

case class Cell(status: Var[String]) {
  def isLive: Boolean = this.status.get.eq("1")
}
