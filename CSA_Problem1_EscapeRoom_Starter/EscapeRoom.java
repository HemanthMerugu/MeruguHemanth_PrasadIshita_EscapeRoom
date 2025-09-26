/*
* Problem 1: Escape Room
* 
* V1.0
* 10/10/2019
* Copyright(c) 2019 PLTW to present. All rights reserved
*/
import java.util.Scanner;

/**
 * Create an escape room game where the player must navigate
 * to the other side of the screen in the fewest steps, while
 * avoiding obstacles and collecting prizes.
 */
public class EscapeRoom
{

      // describe the game with brief welcome message
      // determine the size (length and width) a player must move to stay within the grid markings
      // Allow game commands:
      //    right, left, up, down: if you try to go off grid or bump into wall, score decreases
      //    jump over 1 space: you cannot jump over walls
      //    if you land on a trap, spring a trap to increase score: you must first check if there is a trap, if none exists, penalty
      //    pick up prize: score increases, if there is no prize, penalty
      //    help: display all possible commands
      //    end: reach the far right wall, score increase, game ends, if game ended without reaching far right wall, penalty
      //    replay: shows number of player steps and resets the board, you or another player can play the same board
      // Note that you must adjust the score with any method that returns a score
      // Optional: create a custom image for your player use the file player.png on disk
    
      /**** provided code:
      // set up the game
      boolean play = true;
      while (play)
      {
        // get user input and call game methods to play 
        play = false;
      }
      }
      }
    
      */

  public static void main(String[] args) 
  {      
    // welcome message
    System.out.println("Welcome to EscapeRoom!");
    System.out.println("Get to the other side of the room, avoiding walls and invisible traps,");
    System.out.println("pick up all the prizes.\n");
    
    GameGUI game = new GameGUI();
    game.createBoard();

    // individual player moves
    int px = 0;
    int py = 0; 
    // m is how far the robot moves on the screen when the movement buttons are pressed
    int m = 60;    
    int score = 0;

    String[] validCommands = {
    "right", "left", "up", "down", "r", "l", "u", "d",
    "jump", "jr", "jumpleft", "jl", "jumpup", "ju", "jumpdown", "jd",
    "pickup", "p", "quit", "q", "replay", "help", "?", "unstuck", "us",
    "springtrap", "springtrap up", "springtrap down", "springtrap left", "springtrap right",
    "su", "sd", "sl", "sr"};

  
    // set up game
    // ...existing code...
    try (Scanner in = new Scanner(System.in)) {
      boolean play = true;
      while (play)
      {
        System.out.print("Enter command: ");
        String cmd = in.nextLine().toLowerCase();

      boolean isValid = false;
      for (String command : validCommands) {
        if (cmd.equals(command)) {
          isValid = true;
          break;
        }
      }
      
      if (!isValid) {
        System.out.println("Invalid command. Type 'help' or '?' for a list of commands.");
        continue;
      }

      if (cmd.equals("help") || cmd.equals("?")) {
        System.out.println("Commands: right, left, up, down, jump, pickup, replay, quit, help, stuck");
        System.out.println("Commands: springtrap right/left/up/down");
        System.out.println("When done, reach the far right side of the board to end the game.");
        continue;
      }
      if (cmd.equals("unstuck") || cmd.equals("us")) {
        game.randomizeWallsAndTraps();
        System.out.println("Walls and traps are randomized!");
        continue;
      }
      if (cmd.equals("quit") || cmd.equals("q")) {
        System.out.println("Quitting game.");
        play = false;
        continue;
      }
      if (cmd.equals("pickup") || cmd.equals("p")) {
        int pickupScore = game.pickupPrize();
        score += pickupScore;
        System.out.println("score=" + score);
        System.out.println("steps=" + game.getSteps());
        if (pickupScore > 0) {
            // Only randomize if a prize was actually picked up
            game.randomizeWallsAndTraps();
            System.out.println("Walls and traps are randomized!");
        }
        continue;
      }
      if (cmd.startsWith("springtrap")) {
          int dx = 0;
          int dy = 0;

          if (cmd.equals("springtrap up") || cmd.equals("su")) {
              dy = -m;
          } else if (cmd.equals("springtrap down") || cmd.equals("sd")) {
              dy = m;
          } else if (cmd.equals("springtrap left") || cmd.equals("sl")) {
              dx = -m;
          } else if (cmd.equals("springtrap right") || cmd.equals("sr")) {
              dx = m;
          } else if (!cmd.equals("springtrap")) {
              System.out.println("Invalid springtrap direction. Try: springtrap up/down/left/right.");
              continue;
          }

          score += game.springTrap(dx, dy);
          System.out.println("score=" + score);
          System.out.println("steps=" + game.getSteps());
          continue;
      }

      if (cmd.equals("right") || cmd.equals("d")) {
          score += game.movePlayer(m, 0); // move right by 60 pixels
          System.out.println("score=" + score);
          System.out.println("steps=" + game.getSteps());
          continue;
      }
      if (cmd.equals("left") || cmd.equals("a")) {
          score += game.movePlayer(-m, 0); // move left by 60 pixels
          System.out.println("score=" + score);
          System.out.println("steps=" + game.getSteps());
          continue;
      }
      if (cmd.equals("up") || cmd.equals("w")) {
          score += game.movePlayer(0, -m); // move up by 60 pixels
          System.out.println("score=" + score);
          System.out.println("steps=" + game.getSteps());
          continue;
          
      }
      if (cmd.equals("down") || cmd.equals("s")) {
          score += game.movePlayer(0, m); // move down by 60 pixels
          System.out.println("score=" + score);
          System.out.println("steps=" + game.getSteps());
          continue;
      }
      if (cmd.equals("jumpup") || cmd.equals("ju")) {
          score += game.movePlayer(0, -2*m); 
          System.out.println("score=" + score);
          System.out.println("steps=" + game.getSteps());
          continue;
      }
      if (cmd.equals("jumpdown") || cmd.equals("jd")) {
          score += game.movePlayer(0, 2*m); 
          System.out.println("score=" + score);
          System.out.println("steps=" + game.getSteps());
          continue;
      }
      if (cmd.equals("jumpright") || cmd.equals("jr")) {
          score += game.movePlayer(2*m, 0);
          System.out.println("score=" + score);
          System.out.println("steps=" + game.getSteps());
          continue;
      }
      if (cmd.equals("jumpleft") || cmd.equals("jl")) {
          score += game.movePlayer(-2*m, 0);
          System.out.println("score=" + score);
          System.out.println("steps=" + game.getSteps());
          continue;
      }
      if (cmd.equals("replay")) {
        score = 0;
        game.replay();
        System.out.println("score=" + score);
        System.out.println("steps=" + game.getSteps());
        System.out.println("Board reset! Play again.");
      }
    }

  

    score += game.endGame();

    System.out.println("score=" + score);
    System.out.println("steps=" + game.getSteps());
    //scanner is closed automatically by try-with-resources
  }
  }
}

