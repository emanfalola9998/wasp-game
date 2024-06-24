package WaspPackage

import scala.io.StdIn

object Playground extends App {


  val workerWasps = WaspFactory.generateWorkerWasps()
  val droneWasps = WaspFactory.generateDroneWasps()
  val queenWasps = WaspFactory.generateQueenWasp()

  // accessing the public list
  // concatenating the lists
  val allWasps = new Wasps(workerWasps.waspList ++ droneWasps.waspList ++ queenWasps.waspList)

  // We are instantiating the class within the object so the new keyword is applied to the nested class
  val commander = new Commander.Command(allWasps)
  commander.intro()
  println()
  commander.fire()
}
