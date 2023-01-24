package hw3;
import java.util.ArrayList;
import api.BasketCell;
import api.Direction;
import api.SnakePiece;
import api.GridUtil;
/**
 * @author Varun Advani
 * This class represents the basic game state for a "Snake Escape" game,
 * including a 2d grid of cells and a list of snakes.
 */
public class SnakeBasket {
    /**
     * The 2D array of cells.
     */
    private BasketCell[][] grid;
    /**
     * The list of snakes.
     */
    private ArrayList < Snake > snakes;
    /**
     * Indicates if the current snake was grabbed by the head or tail.
     */
    private boolean grabbedByHead;
    /**
     * The snake that has been grabbed, initially none.
     */
    private Snake currentSnake;
    /**
     * Tracks how many times the snake has been moved.
     */
    private int moveCount;
    /**
     * Indicator if the snake has reached the exit which is when game ends.
     */
    private boolean gameOver;
    /**
     * Constructs an instance of this game from the given string array and list of
     * snakes. <em>Snake information, if any, in the given string array is
     * ignored</em>.
     * 
     * @param desc        array of strings representing the initial grid layout
     * @param givenSnakes array of snakes
     */
    public SnakeBasket(String[] desc, ArrayList < Snake > givenSnakes) {
        grid = GridUtil.createGridFromDescriptor(desc);
        snakes = givenSnakes;
        resetAllSnakes();
        currentSnake = null;
        moveCount = 0;
        gameOver = false;
    }
    /**
     * Constructs an instance of this game from the given string array.
     * 
     * @param desc array of strings representing the initial grid layout, including
     *             ids and placement of snakes
     */
    public SnakeBasket(String[] desc) {
        grid = GridUtil.createGridFromDescriptor(desc);
        snakes = SnakeUtil.findSnakes(desc);
        resetAllSnakes();
        currentSnake = null;
        moveCount = 0;
        gameOver = false;
    }
    /**
     * Returns the grid cell at the given row and column.
     * 
     * @param row given row
     * @param col given column
     * @return grid cell at the given row and column
     */
    public BasketCell getCell(int row, int col) {
        return grid[row][col];
    }
    /**
     * Returns the number of rows in this game.
     * 
     * @return number of rows
     */
    public int getRows() {
        return grid.length;
    }
    /**
     * Returns the number of columns in this game.
     * 
     * @return number of columns
     */
    public int getCols() {
        return grid[0].length;
    }
    /**
     * Returns the currently grabbed snake, if any. Returns null if there is no
     * current snake.
     * 
     * @return current snake, if any
     */
    public Snake currentSnake() {
        return currentSnake;
    }
    /**
     * Returns true if there is a current snake and it was grabbed at the head end,
     * false if it was grabbed by the tail.
     * 
     * @return true if current snake was grabbed by the head
     */
    public boolean currentWasGrabbedByHead() {
        if (currentSnake == null)
            return false;
        return grabbedByHead;
    }
    /**
     * Returns a list of all snakes.
     * 
     * @return list of all snakes
     */
    public ArrayList < Snake > getAllSnakes() {
        return new ArrayList < > (snakes);
    }
    /**
     * Returns true if the green snake is in the exit cell, false otherwise.
     * 
     * @return true if green snake is in the exit
     */
    public boolean isOver() {
        return gameOver;
    }
    /**
     * Returns the total number of moves that have been made so far in this game.
     * 
     * @return number of moves
     */
    public int getMoves() {
        return moveCount;
    }
    /**
     * Attempts to grab a snake by the head or tail at the given position. If there
     * is not already a current snake, and if the given position contains a snake
     * head or tail, that becomes the current snake. If this game already has a
     * current snake selected, this method does nothing.
     * 
     * @param row given row at which to grab a snake
     * @param col given column at which to grab a snake
     */
    public void grabSnake(int row, int col) {
        if (currentSnake != null)
            return;
        BasketCell cell = grid[row][col];
        if (!cell.hasSnake())
            return;
        Snake snake = cell.getSnake();
        if (!snake.isHead(row, col) && !snake.isTail(row, col))
            return;
        grabbedByHead = snake.isHead(row, col);
        currentSnake = snake;
    }
    /**
     * Sets the current snake to null, if any.
     */
    public void releaseSnake() {
        currentSnake = null;
    }
    /**
     * Attempt to move the current snake (head or tail) to an adjacent cell in the
     * given direction. If a move is possible, this method updates the current
     * snake, the move count, and the grid cells (via resetAllSnakes).
     * <p>
     * It is only possible to move in the following cases:
     * <ul>
     * <li>The adjacent cell is empty; then the snake (head or tail) moves into the
     * cell
     * <li>The adjacent cell is the exit and the current snake is the green one;
     * then the snake (head or tail) moves into the exit and the game ends
     * <li>The current snake was grabbed by the head, and the adjacent cell is
     * row/column of the current snake's tail; then the snake (head) moves into the
     * cell
     * <li>The current snake was grabbed by the tail, and the adjacent cell is
     * row/column of the current snake's head; then the snake (tail) moves into the
     * cell
     * <li>The current snake was grabbed by the head and the adjacent cell is an
     * apple; then the apple is removed and the snake (head) moves into the cell,
     * increasing its size by one piece
     * <li>The current snake was grabbed by the head, has at least three pieces, and
     * the adjacent cell is a mushroom; then the mushroom is removed and the snake
     * (head) moves into the cell, reducing its size by one piece
     * </ul>
     * If none of the above conditions is met, this method does nothing. If any of
     * the conditions is met and a move occurs, the move count is increased by 1. If
     * there is no currently grabbed snake, this method does nothing.
     * 
     * @param dir Direction in which to attempt to move
     */
    public void move(Direction dir) {
        if (currentSnake == null)
            return;
        // Identify which coordinate to validate for movement
        int adjacentRow, adjacentCol;
        if (grabbedByHead) {
            adjacentRow = currentSnake.getHead().row();
            adjacentCol = currentSnake.getHead().col();
        } else {
            adjacentRow = currentSnake.getTail().row();
            adjacentCol = currentSnake.getTail().col();
        }
        /*int adjacentRow = currentRow;
        int adjacentCol = currentCol;*/
        if (dir == Direction.UP)
            adjacentRow--;
        else if (dir == Direction.DOWN)
            adjacentRow++;
        else if (dir == Direction.LEFT)
            adjacentCol--;
        else if (dir == Direction.RIGHT)
            adjacentCol++;
        if (adjacentRow < 0 || adjacentRow >= grid.length || adjacentCol < 0 || adjacentCol >= grid[adjacentRow].length)
            return;
        BasketCell adjacentCell = grid[adjacentRow][adjacentCol];
        // The adjacent cell is empty; then the snake (head or tail) moves into the cell
        if (adjacentCell.isEmpty()) {
            if (grabbedByHead)
                currentSnake.moveHead(dir);
            else
                currentSnake.moveTail(dir);
            resetAllSnakes();
            moveCount++;
            return;
        }
        // The adjacent cell is the exit and the current snake is the green one;
        // then the snake (head or tail) moves into the exit and the game ends
        if (adjacentCell.isExit() && currentSnake.isGreen()) {
            if (grabbedByHead)
                currentSnake.moveHead(dir);
            else
                currentSnake.moveTail(dir);
            resetAllSnakes();
            gameOver = true;
            moveCount++;
            return;
        }
        // The current snake was grabbed by the head, and the adjacent cell is
        // row/column of the current snake's tail; then the snake (head) moves into the
        // cell
        if (grabbedByHead && currentSnake.getTail().row() == adjacentRow &&
            currentSnake.getTail().col() == adjacentCol) {
            currentSnake.moveHead(dir);
            resetAllSnakes();
            moveCount++;
            return;
        }
        // The current snake was grabbed by the tail, and the adjacent cell is
        // row/column of the   current snake's head; then the snake (tail) moves into the
        // cell
        if (!grabbedByHead && currentSnake.getHead().row() == adjacentRow &&
            currentSnake.getHead().col() == adjacentCol) {
            currentSnake.moveTail(dir);
            resetAllSnakes();
            moveCount++;
            return;
        }
        // The current snake was grabbed by the head and the adjacent cell is an
        // apple; then the apple is removed and the snake (head) moves into the cell,
        // increasing its size by one piece
        if (grabbedByHead && adjacentCell.isApple()) {
            adjacentCell.clearFood();
            currentSnake.moveHeadAndGrow(dir);
            resetAllSnakes();
            moveCount++;
            return;
        }
        // The current snake was grabbed by the head, has at least three pieces, and
        // the adjacent cell is a mushroom; then the mushroom is removed and the snake
        // (head) moves into the cell, reducing its size by one piece
        if (grabbedByHead && currentSnake.getPieces().size() >= 3 && adjacentCell.isMushroom()) {
            adjacentCell.clearFood();
            currentSnake.moveHeadAndShrink(dir);
            resetAllSnakes();
            moveCount++;
            return;
        }
    }
    /**
     * Clears all snake information from the grid, if any, and then sets it from the
     * current list of snakes. After executing this method, the information in the
     * grid cells and the information in the snake list should be fully consistent.
     */
    public void resetAllSnakes() {
        for(int r = 0; r < grid.length; r++)
	      for(int c = 0; c < grid[r].length; c++) {
		    grid[r][c].clearSnake();
	  }
        
    for(Snake snake : snakes) 
    	for(SnakePiece piece : snake.getPieces()) 
    		grid[piece.row()][piece.col()].setSnake(snake);
    	
    
}
}