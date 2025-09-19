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
      //    if you land on a trap, spring a traap to increase score: you must first check if there is a trap, if none exists, penalty
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
      */

      
  public static void main(String[] args) 
  {      
    // welcome message
    System.out.println("Welcome to EscapeRoom!");
    System.out.println("Get to the other side of the room, avoiding walls and invisible traps,");
    System.out.println("pick up all the prizes.\n");
    
    GameGUI game = new GameGUI();
    game.createBoard();

    // size of move
    int m = 60; 
    // individual player moves
    int px = 0;
    int py = 0; 
    
    int score = 0;

    Scanner in = new Scanner(System.in);
    String[] validCommands = { "right", "left", "up", "down", "r", "l", "u", "d",
    "jump", "jr", "jumpleft", "jl", "jumpup", "ju", "jumpdown", "jd",
    "pickup", "p", "quit", "q", "replay", "help", "?"};
  
    // set up game
    boolean play = true;
    while (play)
    {
      /* TODO: get all the commands working */
      /* Your code here */
      
      System.out.print("Enter your move: ");
      String move = in.nextLine().toLowerCase();

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
        System.out.println("Commands: right, left, up, down, jump, pickup, replay, quit, help");
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
      if (cmd.equals("springtrap")) {
        score += game.springTrap(px, py);
        System.out.println("score=" + score);
        System.out.println("steps=" + game.getSteps());
        
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
      }
      if (cmd.equals("replay")) {
        score = 0;
        game.replay();
        System.out.println("score=" + score);
        System.out.println("steps=" + game.getSteps());
        System.out.println("Board reset! Play again.");
    }
    }
      if(move.equals("right") || move.equals("r")){
          game.movePlayer(m,0);
          score++;

      } else if(move.equals("left") || move.equals("l")){
          game.movePlayer(-m,0);
          score++;

      } else if(move.equals("up") || move.equals("u")){
          game.movePlayer(0,-m);
          score++;

      } else if(move.equals("down") || move.equals("d")){
          game.movePlayer(0,m);
          score++;

      } else if(move.equals("jumpup") || move.equals("ju")){
          game.movePlayer(0,-2*m);
          score++;

      } else if(move.equals("jumpdown") || move.equals("jd")){
          game.movePlayer(0,2*m);
          score++;

      } else if(move.equals("jumpright") || move.equals("jr")){
          game.movePlayer(2*m,0);
          score++;



      } else if(move.equals("jumpleft") || move.equals("jl")){
          game.movePlayer(-2*m,0);
          score++;

      } else if(move.equals("pickup") || move.equals("p")){
          System.out.println("Picking up prize");
          score += game.pickupPrize();

      } else if(move.equals("replay")){
          System.out.println("Resetting the board. Your score: " + score + " Steps: " + game.getSteps());
          game.replay();
          score = 0;


      } else if(move.equals("help") || move.equals("?")){
          System.out.println("Try one of these: right/r, left/l, up/u, down/d, jump commands, pickup/p, replay, help/?");

      } else if(move.equals("quit") || move.equals("q")){
          System.out.println("Quitting the game");
          play = false;


      } else {
          System.out.println("That is not a valid command!");
          score -= 5;
      }



      if(game.isTrap(0,0)){
          System.out.println("Trap!");
          score += game.springTrap(0,0);
      }
    }




    score += game.endGame();

    System.out.println("score=" + score);
    System.out.println("steps=" + game.getSteps());
  }
}
