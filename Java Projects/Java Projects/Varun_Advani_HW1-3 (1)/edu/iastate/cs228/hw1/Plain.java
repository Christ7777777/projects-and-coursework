package edu.iastate.cs228.hw1;

/**
 *  
 * @author Varun Advani
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;

/**
 * 
 * The plain is represented as a square grid of size width x width. 
 *
 */
public class Plain {
  private int width; // grid size: width X width 

  public Living[][] grid;

  /**
   *  Default constructor reads from a file 
   */
  public Plain(String inputFileName) throws FileNotFoundException {

    Scanner scanner = new Scanner(new File(inputFileName));
    width = scanner.nextLine().split("\\s+").length;
    scanner = new Scanner(new File(inputFileName));
    grid = new Living[width][width];
    for (int r = 0; r < width; r++) {
      for (int c = 0; c < width; c++) {
        String tempo = scanner.next();
        if (tempo.charAt(0) == 'B') {
          grid[r][c] = new Badger(this, r, c, Character.getNumericValue(tempo.charAt(1)));
        } else if (tempo.charAt(0) == 'E') {
          grid[r][c] = new Empty(this, r, c);
        } else if (tempo.charAt(0) == 'F') {
          grid[r][c] = new Fox(this, r, c, Character.getNumericValue(tempo.charAt(1)));
        } else if (tempo.charAt(0) == 'G') {
          grid[r][c] = new Grass(this, r, c);
        } else {
          grid[r][c] = new Rabbit(this, r, c, Character.getNumericValue(tempo.charAt(1)));
        }
      }
    }
  }

  /**
   * Constructor that builds a w x w grid without initializing it. 
   * @param width  the grid 
   */
  public Plain(int w) {
    width = w;
    grid = new Living[width][width];
  }

  public int getWidth() {
    return width;
  }

  /**
   * Initialize the plain by randomly assigning to every square of the grid  
   * one of BADGER, FOX, RABBIT, GRASS, or EMPTY.  
   * 
   * Every animal starts at age 0.
   */
  public void randomInit() {
    Random generator = new Random();

    for (int r = 0; r < width; r++) {
      for (int c = 0; c < width; c++) {
        int random_number = generator.nextInt(5);
        if (random_number == 0) {
          grid[r][c] = new Badger(this, r, c, 0);
        } else if (random_number == 1) {
          grid[r][c] = new Empty(this, r, c);
        } else if (random_number == 2) {
          grid[r][c] = new Fox(this, r, c, 0);
        } else if (random_number == 3) {
          grid[r][c] = new Grass(this, r, c);
        } else {
          grid[r][c] = new Rabbit(this, r, c, 0);
        }
      }
    }
  }

  /**
   * Output the plain grid. For each square, output the first letter of the living form
   * occupying the square. If the living form is an animal, then output the age of the animal 
   * followed by a blank space; otherwise, output two blanks.  
   */
  @Override
  public String toString() {

    String string = "";
    for (int r = 0; r < width; r++) {
      for (int c = 0; c < width; c++) {
        if (grid[r][c].who() == State.BADGER) {
          Badger badger = (Badger) grid[r][c];
          string = string + "B" + badger.myAge();
        } else if (grid[r][c].who() == State.EMPTY) {
          string = string + "E ";
        } else if (grid[r][c].who() == State.FOX) {
          Fox fox = (Fox) grid[r][c];
          string = string + "F" + fox.myAge();
        } else if (grid[r][c].who() == State.GRASS) {
          string = string + "G ";
        } else {
          Rabbit rabbit = (Rabbit) grid[r][c];
          string = string + "R" + rabbit.myAge();
        }
        string = string + " ";
      }
      string = string + "\n";
    }
    return string;
  }

  /**
   * Write the plain grid to an output file.  Also useful for saving a randomly 
   * generated plain for debugging purpose. 
   * @throws FileNotFoundException
   */
  public void write(String outputFileName) throws FileNotFoundException {
    PrintWriter printWriter = new PrintWriter(outputFileName);
    printWriter.println(this.toString());
    printWriter.close();
  }
}