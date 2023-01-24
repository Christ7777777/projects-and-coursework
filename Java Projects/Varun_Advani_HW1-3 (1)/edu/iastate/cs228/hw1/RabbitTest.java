package edu.iastate.cs228.hw1;
/**
 *  
 * @author Varun Advani
 *
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class RabbitTest {
  private Plain p;
  private Plain pNew;
  @Before
  public void setUp() throws Exception {
    p = new Plain(3);
    pNew = new Plain(3);
    for (int r = 0; r < 3; r++) {
      for (int c = 0; c < 3; c++) {
        p.grid[r][c] = new Empty(this.p, r, c);
      }
    }
  }

  @Test
  public void rabbitToEmptyCauseMaxAge() {
    p.grid[1][1] = new Rabbit(this.p, 1, 1, Living.RABBIT_MAX_AGE);
    Assert.assertEquals(State.EMPTY, p.grid[1][1].next(pNew).who());
  }

  @Test
  public void rabbitToEmptyCauseNoGrass() {
    p.grid[1][1] = new Rabbit(this.p, 1, 1, 0);
    Assert.assertEquals(State.EMPTY, p.grid[1][1].next(pNew).who());
  }

  @Test
  public void rabbitToFox() {
    p.grid[1][1] = new Rabbit(this.p, 1, 1, 0);
    p.grid[0][1] = new Grass(this.p, 0, 1);
    p.grid[1][2] = new Badger(this.p, 1, 2, 0);
    p.grid[2][1] = new Fox(this.p, 2, 1, 0);
    p.grid[1][0] = new Fox(this.p, 1, 0, 0);
    p.grid[2][2] = new Rabbit(this.p, 2, 2, 0);
    Assert.assertEquals(State.FOX, p.grid[1][1].next(pNew).who());
  }

  @Test
  public void rabbitToBadger() {
    p.grid[1][1] = new Rabbit(this.p, 1, 1, 0);
    p.grid[1][2] = new Grass(this.p, 1, 2);
    p.grid[0][0] = new Badger(this.p, 0, 0, 0);
    p.grid[0][1] = new Badger(this.p, 0, 1, 0);
    Assert.assertEquals(State.BADGER, p.grid[1][1].next(pNew).who());
  }

  @Test
  public void rabbitToRabbit() {
    p.grid[1][1] = new Rabbit(this.p, 1, 1, 0);
    p.grid[1][2] = new Grass(this.p, 1, 2);
    Assert.assertEquals(State.RABBIT, p.grid[1][1].next(pNew).who());
  }
  @Test
  public void rabbitAgeUpdate() {
	    p.grid[1][1] = new Rabbit(this.p, 1, 1, 0);
	    p.grid[1][2] = new Grass(this.p, 1, 2);
	    pNew.grid[1][1] = p.grid[1][1].next(pNew);
	    Assert.assertEquals(1, ((Rabbit)pNew.grid[1][1]).myAge());
  }
  @Test
  public void checkUpdate() {
	  Rabbit r = new Rabbit(p, 0, 1, 2);
	  Assert.assertEquals(State.RABBIT, r.who());
  }
}