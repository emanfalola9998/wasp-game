package WaspPackage

import java.util.UUID
import scala.collection.immutable.List

case class Wasp(id: UUID, rank: String, lives: Int, deductable: Int) {
  override def equals(obj: Any): Boolean = obj match {
    case other: Wasp => id == other.id
    case _ => false
  }

  override def hashCode(): Int = {
    id.hashCode()
  }
}

// wasps.waspList is targeting this class and pulling out its public list wasps
class Wasps(val waspList: List[Wasp] = List())
