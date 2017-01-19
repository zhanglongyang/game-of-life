package model

import scala.collection.immutable.TreeMap

case class Universe(cellsMap: TreeMap[Location, Cell])
