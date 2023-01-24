package edu.iastate.cs228.hw1;
/**
 *  
 * @author Varun Advani
 *
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class PlainTest {
  private Plain p;
  @Before
  public void setUp() throws Exception {}

  @Test
  public void randomTest() {
    p = new Plain(3);
    p.randomInit();
    for (int r = 0; r < p.getWidth(); r++) {
      for (int c = 0; c < p.getWidth(); c++) {
        if ((p.grid[r][c].who() != State.GRASS) && (p.grid[r][c].who() != State.EMPTY)) {
          Animal a = (Animal) p.grid[r][c];
          Assert.assertEquals(0, a.myAge());
        }
      }
    }
  }

  @Test
  public void randomTest2() {
    p = new Plain(3);
    p.randomInit();
    for (int r = 0; r < p.getWidth(); r++) {
      for (int c = 0; c < p.getWidth(); c++) {
        Assert.assertNotNull(p.grid[r][c]);
      }
    }
  }

  @Test
  public void testWidth() throws Exception {
    p = new Plain("public3-10x10.txt");
    Assert.assertEquals(10, p.getWidth());
  }

  @Test
  public void testToString() throws Exception {
    p = new Plain("public1-3x3.txt");
    Assert.assertEquals(p.toString(), "G  B0 F0 \nF0 F0 R0 \nF0 E  G  \n");
  }

  @Test
  public void testOutputWrite() throws Exception {
    p = new Plain("public1-3x3.txt");
    p.write("output.txt");
    Plain q = new Plain("output.txt");
    Assert.assertEquals(p.toString(), q.toString());
  }
}