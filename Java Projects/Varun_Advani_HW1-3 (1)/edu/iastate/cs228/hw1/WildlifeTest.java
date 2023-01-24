package edu.iastate.cs228.hw1;
/**
 *  
 * @author Varun Advani
 *
 */

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class WildlifeTest {

  @Before
  public void setUp() throws Exception {}

  @Test
  public void updatePlainTest() throws Exception {
    Plain pNew = new Plain(3);
    Plain pOld = new Plain("public1-3x3.txt");
    Wildlife.updatePlain(pOld, pNew);
    Assert.assertEquals(pNew.toString(), "G  F0 E  \n" +
      "E  E  F0 \n" +
      "E  F0 G  \n");
  }

  @Test
  public void bigTest() throws Exception {
    Plain even = new Plain("public3-10x10.txt");
    Plain odd = new Plain(even.getWidth());
    int step = 6;
    for (int i = 0; i < step; i++) {
      if (i % 2 == 0) {
        Wildlife.updatePlain(even, odd);
      } else {
        Wildlife.updatePlain(odd, even);
      }
    }
    Assert.assertEquals(even.toString(), "B0 E  B0 E  E  R0 E  R0 R2 E  \n" +
      "G  E  B0 R0 B4 R0 E  R0 R3 R1 \n" +
      "G  G  R2 R0 E  R0 R0 E  E  E  \n" +
      "G  F5 R3 R0 E  R0 R0 E  E  R0 \n" +
      "R2 E  E  E  R0 R0 E  E  B1 G  \n" +
      "R0 R0 R0 R0 R0 E  B1 R0 G  G  \n" +
      "E  E  R0 E  R0 E  B1 R0 G  G  \n" +
      "B4 E  R0 E  R0 E  E  E  R0 G  \n" +
      "G  R2 R3 E  R0 E  R0 R3 R1 G  \n" +
      "G  R0 R1 E  R0 E  R0 R2 R0 G  \n");
  }
}