package edu.iastate.cs228.hw1;
/**
 *  
 * @author Varun Advani
 *
 */
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

public class AnimalTest {
  private Plain p;
  private Animal a;
  @Before
  public void setUp() throws Exception {
    p = new Plain("public1-3x3.txt");
  }

  @Test
  public void test1() {
    a = new Fox(p, 1, 1, 2);
    Assert.assertEquals(2, a.myAge());
  }

  @Test
  public void test2() {
    a = new Badger(p, 2, 1, 1);
    Assert.assertEquals(1, a.myAge());
  }
  @Test
  public void test3() {
    Plain p1 = new Plain(2);
    a = new Rabbit(p1, 1, 0, 1);
    Assert.assertEquals(1, a.myAge());
  }
}