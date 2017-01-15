import GameOfLife.{Cell, Location}
import com.thoughtworks.binding.Binding.Var
import org.scalatest.{FlatSpec, Matchers}

class GameOfLifeSpec extends FlatSpec with Matchers {
  "Cell" should "should be alive" in {
    val cell = Cell(Var("1"), Location(0, 0))
    cell.isAlive shouldEqual(true)
  }
}
