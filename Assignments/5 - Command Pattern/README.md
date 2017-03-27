**Assignment 6 - Command Design Pattern**
___

Demonstration of the Command design pattern.

This project consists of 6 Java files:

* Command.java
* ReversibleCommand.java
* ReversibleCommandQueue.java
* MoveCommand.java
* CartesianPlayer.java
* Main.java

**Command**

  The Command interface is the basis for our command pattern. The only thing inside this interface is an execute() method.

**ReversibleCommand**

  The ReversibleCommand interface is an extension of the Command interface for actions that can be reversed. It introduces the undo() method.

**ReversibleCommandQueue**

  The ReversibleCommandQueue class manages both executed and unexecuted reversible commands. Queued commands are executed in a FIFO fashion. Every time a command is executed, it is added to a list of executed commands. The executed commands list is treated as a LIFO so that the most recently executed command will be the next command reversed by calling undoLastCommand().

  **MoveCommand**


  The MoveCommand class is the only implementation of a command in this project. The MoveCommand takes in a player, direction, and a number of spaces to move the player by, which can then be passed to a ReversibleCommandQueue via its addCommand() method, and then later executed by executeNextCommand() once it reaches the head of the queue.

**CartesianPlayer**

  The CartesianPlayer class is the "receiver" in our command pattern. It consists of a position and a method to move in any Cartesian direction.

**Main**

  The test harness for this project. Creates a simple UI that prompts the user for a direction to move in, or additionally allows the user to go back if the user has already made moves. All moves can be reversed to get back to the origin.
