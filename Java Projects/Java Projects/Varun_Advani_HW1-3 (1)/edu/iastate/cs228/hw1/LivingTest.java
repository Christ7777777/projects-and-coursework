package edu.iastate.cs228.hw1;
/**
 *  
 * @author Varun Advani
 *
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class LivingTest {
  private Plain p;
  private int[] pops;
  @Before
  public void setUp() throws Exception {
    p = new Plain("public3-10x10.txt");
    pops = new int[5];
  }

  @Test
  public void test1() {
    Living c = p.grid[1][1];
    c.census(pops);
    int[] neighborhood = new int[5];
    neighborhood[0] = 3;
    neighborhood[1] = 2;
    neighborhood[2] = 0;
    neighborhood[3] = 4;
    neighborhood[4] = 0;
    Assert.assertArrayEquals(neighborhood, pops);
  }

  @Test
  public void test2() {
    Living c = p.grid[2][2];
    c.census(pops);
    int[] neighborhood = new int[5];
    neighborhood[0] = 1;
    neighborhood[1] = 3;
    neighborhood[2] = 0;
    neighborhood[3] = 5;
    neighborhood[4] = 0;
    Assert.assertArrayEquals(neighborhood, pops);
  }

  @Test
  public void test3() {
    Living c = p.grid[3][3];
    c.census(pops);
    int[] neighborhood = new int[5];
    neighborhood[0] = 0;
    neighborhood[1] = 5;
    neighborhood[2] = 0;
    neighborhood[3] = 4;
    neighborhood[4] = 0;
    Assert.assertArrayEquals(neighborhood, pops);
  }
  @Test
  public void test4() {
	  Living c = p.grid[9][9];
	  int[] neighborhood = new int[5];
	  c.census(pops);
	  neighborhood[0] = 0;
	  neighborhood[1] = 0;
	  neighborhood[2] = 0;
	  neighborhood[3] = 4;
	  neighborhood[4] = 0;
	  Assert.assertArrayEquals(neighborhood, pops);
  }
}