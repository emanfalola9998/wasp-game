package WaspPackage

import WaspPackage.Playground.allWasps

import scala.io.StdIn
import scala.util.Random

object Commander {
  class Command(val wasps: Wasps) {
    def intro(): Unit = {
      println("What is your name?")
      val name = StdIn.readLine()
      println(s"Welcome ${name}, I hope you are as good as they say!")
    }

    def waspRecursion(currentWasps: Wasps): Boolean = {
      if (currentWasps.waspList.isEmpty) {
        println("Game Over")
        false
      } else {
        val random = new Random()
        val targetIndex = random.nextInt(currentWasps.waspList.length)
        var targetWasp = currentWasps.waspList(targetIndex)

        println(s"\nA random Wasp has been selected ${targetWasp.rank}\n Would you like to fire: \n f:fire")
        val choice = StdIn.readLine()

        if (choice == "f") {
          println(s"Firing at wasp with rank ${targetWasp.rank} who has ${targetWasp.lives} lives!\n")

          // Used the copy method on case class to override the lives property
          targetWasp = targetWasp.copy(lives = targetWasp.lives - targetWasp.deductable)

          if (targetWasp.lives <= 0) {
            // Remove the dead wasp from currentWasps
            println(s"${targetWasp.rank} has been eliminated!")
            val newWaspsList = currentWasps.waspList.filterNot(_.id == targetWasp.id)
            // New List is created (instantiated) with the dead wasp removed
            val newWasps = new Wasps(newWaspsList)
            println(s"${targetWasp.rank} remaining lives: ${targetWasp.lives}")
            println("-" * 100)
            newWasps.waspList.foreach(wasp => println(s"Rank: ${wasp.rank} lives Remaining: ${wasp.lives}"))
            // .find returns type Option[A]
            newWasps.waspList.find(wasp => wasp.rank == "queenWasp") match {
              case None =>
                println("The Queen is dead!")
                false
              case Some(_) => // Changed: case _ replaced with case Some(_)
                println("-" * 100)
                waspRecursion(newWasps) // If its not a queen then the recursion is triggered
            }
          } else {
            // Update the currentWasps list with the modified targetWasp
            val newWaspsList = currentWasps.waspList.updated(targetIndex, targetWasp)
            val newWasps = new Wasps(newWaspsList)
            println(s"${targetWasp.rank} remaining lives: ${targetWasp.lives}")
            println("All Wasps")
            newWasps.waspList.foreach(wasp => println(s"Rank: ${wasp.rank} lives Remaining: ${wasp.lives}"))
            waspRecursion(newWasps) // Changed: no return keyword, just the recursive call
          }
        } else {
          println("Invalid choice, exiting.")
          false
        }
      }
    }


    def fire(): Unit = {
      val continueLoop = waspRecursion(wasps)
      println("-".repeat(100))
      if (!continueLoop) {
        println("Would you like to play again?\n p: play again")
        val option = StdIn.readLine()
        if (option == "p") {
          val commander = new Commander.Command(allWasps)
          commander.intro()
          commander.fire() // Start a new game if the player wants to play again
        } else {
          println("Game Over")
        }
      } else {
        println("Game Over")
      }
    }
  }
}
