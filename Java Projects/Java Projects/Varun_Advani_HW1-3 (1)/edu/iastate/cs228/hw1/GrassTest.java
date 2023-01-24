package edu.iastate.cs228.hw1;
/**
 *  
 * @author Varun Advani
 *
 */
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class GrassTest {
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
  public void grassToEmpty() {
    p.grid[1][1] = new Grass(this.p, 1, 1);
    p.grid[1][2] = new Rabbit(this.p, 1, 2, 0);
    p.grid[2][2] = new Rabbit(this.p, 2, 2, 0);
    p.grid[2][1] = new Rabbit(this.p, 2, 1, 0);
    Assert.assertEquals(State.EMPTY, p.grid[1][1].next(pNew).who());
  }

  @Test
  public void grassToRabbit() {
    p.grid[1][1] = new Grass(this.p, 1, 1);
    p.grid[0][1] = new Grass(this.p, 0, 1);
    p.grid[1][2] = new Rabbit(this.p, 1, 2, 0);
    p.grid[2][2] = new Rabbit(this.p, 2, 2, 0);
    p.grid[2][1] = new Rabbit(this.p, 2, 1, 0);
    Assert.assertEquals(State.RABBIT, p.grid[1][1].next(pNew).who());
  }

  @Test
  public void grassToGrass() {
    p.grid[1][1] = new Grass(this.p, 1, 1);
    p.grid[1][2] = new Rabbit(this.p, 1, 2, 0);
    p.grid[2][2] = new Rabbit(this.p, 2, 2, 0);
    Assert.assertEquals(State.GRASS, p.grid[1][1].next(pNew).who());
  }
  @Test
  public void checkState() {
	  Grass g = new Grass(p, 1, 1);
	  Assert.assertEquals(State.GRASS, g.who());
  }
}