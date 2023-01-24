package mini3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import snakes.api.Direction;
import snakes.api.GridUtil;
import snakes.api.SnakePiece;
import snakes.hw3.Snake;
import snakes.hw3.SnakeBasket;

/**
 * Class that manages the state information during a recursive
 * search for solutions to the SnakeEscape game.
 */
public class SnakeSolver
{
  /**
   * Maximum number of moves allowed in the search.
   */
  private int maxMoves;
  
  /**
   * Associates a signature with the move count to reach 
   * that grid layout.
   */
  private Map<String, Integer> seen = new HashMap<String, Integer>();
  
  /**
   * All solutions found in this search.
   */
  private ArrayList<String> solutions = new ArrayList<>();
  
  /**
   * Constructs a solver with the given maximum number of moves.
   * @param givenMaxMoves
   *   maximum number of moves
   */
  public SnakeSolver(int givenMaxMoves)
  {
    maxMoves = givenMaxMoves;
  }
  
  /**
   * Returns all solutions found in the search.  Each solution 
   * is a single string in the form returned by SnakeBasket#getMoveList.
   * @return
   *   list of all solutions
   */
  public ArrayList<String> getSolutions()
  {
    return solutions;
  }

//  String HeadTail[] = {"h", "t"};
  /**
   * Recursively search for solutions to the given game instance
   * according to the algorithm described in the miniassignment 3
   * pdf.
   * @param game
   *   any instance of SnakeBasket
   */
  public void solve(SnakeBasket game)
  {
    //if the number of moves is over the max then return
    int numMoves = game.getMoves();
    if (numMoves > maxMoves)//base case
    	return;
    //else if the game is over, record the solution
    if (game.isOver()) { //base case
      solutions.add(game.getMoveList());
      return;
    }
    String layout = game.getSignature();
    //if the grid layout is one we've seen before
    if (seen.containsKey(layout)) {
      //if the number of moves is lower than how we got there before
      if (numMoves < seen.get(layout))
      {
        //update the number of moves for how we got there
        seen.put(layout, numMoves);
      }
      else 
    	  return;
    }
    //game not over, and we haven't truncated the search, so keep searching
    //record the grid layout in the list of those we've seen
    seen.put(layout, numMoves);
    //for each snake in the game's list
    for (Snake snake : game.getAllSnakes()) {
      //for that snake's head and then tail
      for (int select = 0; select < 2; select++) {
        //for each possible direction in order LEFT, RIGHT, UP, DOWN
        for (Direction direction : Direction.values()) {
          SnakePiece piece = (select == 0) ? snake.getHead() : snake.getTail();
          game.releaseSnake();
          game.grabSnake(piece.row(), piece.col());
          //try moving the snake head/tail in that direction
          game.move(direction);
          //if it's a legitimate move
          if (numMoves != game.getMoves()) {
            solve(game);  //recursive case
            game.undoMove();
          }
        }
      }
    }
  }
}
