package edu.iastate.cs228.hw1;

/**
 *  
 * @author Varun Advani
 *
 */

/**
 * Grass remains if more than rabbits in the neighborhood; otherwise, it is eaten. 
 *
 */
public class Grass extends Living {
  public Grass(Plain p, int r, int c) {
    super(p, r, c);
  }

  public State who() {
    return State.GRASS;
  }

  /**
   * Grass can be eaten out by too many rabbits. Rabbits may also multiply fast enough to take over Grass.
   */
  public Living next(Plain pNew) {

    int[] totalPopulations = new int[NUM_LIFE_FORMS];
    this.census(totalPopulations);
    if (totalPopulations[RABBIT] >= (totalPopulations[GRASS] * 3)) {
      return new Empty(pNew, row, column);
    } else if (totalPopulations[RABBIT] >= 3) {
      return new Rabbit(pNew, row, column, 0);
    } else {
      return new Grass(pNew, row, column);
    }
  }
}