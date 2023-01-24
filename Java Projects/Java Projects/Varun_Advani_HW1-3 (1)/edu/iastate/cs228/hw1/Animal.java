package edu.iastate.cs228.hw1;
/**
 *  
 * @author Varun Advani
 *
 */
/*
 * This class is to be extended by the Badger, Fox, and Rabbit classes. 
 */
public abstract class Animal extends Living implements MyAge {
  protected int age; // age of the animal 
  /**
   * 
   * @return age of the animal 
   */
  public Animal(Plain p, int r, int c, int a) {
	 super(p, r, c);
	 age = a;
  }
  public int myAge() {
    return age;
  }
}