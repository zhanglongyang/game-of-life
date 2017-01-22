package model

import com.thoughtworks.binding.Binding.Var

sealed trait Status
case object Live extends Status
case object Dead extends Status

// TODO type-safe Status
case class Cell(status: Var[String]) {
  def isLive: Boolean = this.status.get.eq("1")
}
