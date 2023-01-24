package edu.iastate.cs228.hw1;
/**
 *  
 * @author Varun Advani
 *
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class EmptyTest {
  private Plain p;
  private Plain pNew;
  @Before
  public void setUp() throws Exception {
    p = new Plain(3);
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        p.grid[r][c] = new Empty(this.p, r, c);
      }
    }
  }

  @Test
  public void emptyToRabbit() {
    p.grid[0][1] = new Rabbit(this.p, 0, 1, 0);
    p.grid[2][1] = new Rabbit(this.p, 2, 1, 0);
    Assert.assertEquals(State.RABBIT, p.grid[1][1].next(pNew).who());
  }

  @Test
  public void emptyToFox() {
    p.grid[0][1] = new Fox(this.p, 0, 1, 0);
    p.grid[2][1] = new Fox(this.p, 2, 1, 0);
    Assert.assertEquals(State.FOX, p.grid[1][1].next(pNew).who());
  }

  @Test
  public void emptyToBadger() {
    p.grid[0][1] = new Badger(this.p, 0, 1, 0);
    p.grid[2][1] = new Badger(this.p, 2, 1, 0);
    Assert.assertEquals(State.BADGER, p.grid[1][1].next(pNew).who());
  }

  @Test
  public void emptyToGrass() {
    p.grid[0][1] = new Grass(this.p, 0, 1);
    Assert.assertEquals(State.GRASS, p.grid[1][1].next(pNew).who());
  }

  @Test
  public void emptyToEmpty() {
    Assert.assertEquals(State.EMPTY, p.grid[1][1].next(pNew).who());
  }
  @Test
  public void checkState() {
	  Empty e = new Empty(p, 1, 1);
	  Assert.assertEquals(State.EMPTY, e.who());
  }
}