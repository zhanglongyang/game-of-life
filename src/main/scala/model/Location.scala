package model

case class Location(x: Integer, y: Integer)(implicit val size: Integer) extends Ordered[Location] {
  def isValid: Boolean = {
    (this.x >= 0 && this.x < size) && (this.y >= 0 && this.y < size)
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
