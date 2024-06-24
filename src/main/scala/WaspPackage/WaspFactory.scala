package WaspPackage

import java.util.UUID

object WaspFactory {
  def generateWorkerWasps(): Wasps = {
    val numberOfWasps = 5
    val wasps = (1 to numberOfWasps).map(_ => new Wasp(UUID.randomUUID(), "workerWasps", 68, 68)).toList
    new Wasps(wasps)
  }

  def generateDroneWasps(): Wasps = {
    val numberOfWasps = 8
    val wasps = (1 to numberOfWasps).map(_ => new Wasp(UUID.randomUUID(), "droneWasps", 60 , 60)).toList
    new Wasps(wasps)
  }

  def generateQueenWasp(): Wasps = {
    val queenWasp = List(new Wasp(UUID.randomUUID(), "queenWasp", 80, 80))
    new Wasps(queenWasp)
  }
}
