package model

// TODO: implicit to pass size
case class Location(x: Integer, y: Integer) extends Ordered[Location] {
  def isValid: Boolean = {
    (this.x >= 0 && this.x < 5) && (this.y >= 0 && this.y < 5)
  }

  def neighbours: List[Location] = {
    List(
      Location(x - 1, y - 1),
      Location(x - 1, y),
      Location(x - 1, y + 1),
      Location(x, y - 1),
      Location(x, y + 1),
      Location(x + 1, y - 1),
      Location(x + 1, y),
      Location(x + 1, y + 1)
    ).filter(_.isValid)
  }

  override def compare(that: Location): Int = {
    (this.x - that.x, this.y - that.y) match {
      case (0, j) => j
      case (i, _) => i
    }
  }
}
