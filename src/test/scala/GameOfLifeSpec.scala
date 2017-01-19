import GameOfLife.{Cell, Location}
import com.thoughtworks.binding.Binding.Var
import org.scalatest.{FlatSpec, Matchers}

class GameOfLifeSpec extends FlatSpec with Matchers {
  "Cell" should "should be alive" in {
    val cell = Cell(Var("1"))
    cell.isLive shouldBe true
  }

  "Location" should "be comparable" in {
    Location(1, 2) == Location(1, 2) shouldBe true
    Location(1, 2) < Location(1, 3) shouldBe true
    Location(1, 2) < Location(2, 3) shouldBe true
    Location(1, 4) < Location(2, 1) shouldBe true
    Location(2, 2) > Location(1, 10) shouldBe true
  }
}
