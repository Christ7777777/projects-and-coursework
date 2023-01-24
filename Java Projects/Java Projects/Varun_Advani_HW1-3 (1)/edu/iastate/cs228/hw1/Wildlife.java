package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *  
 * @author Varun Advani
 *
 */

/**
 * 
 * The Wildlife class performs a simulation of a grid plain with
 * squares inhabited by badgers, foxes, rabbits, grass, or none. 
 *
 */
public class Wildlife {
  /**
   * Update the new plain from the old plain in one cycle. 
   * @param pOld  old plain
   * @param pNew  new plain 
   */
  public static void updatePlain(Plain pOld, Plain pNew) {
    for (int r = 0; r < pOld.grid.length; r++) {
      for (int c = 0; c < pOld.grid[r].length; c++) {
        pNew.grid[r][c] = pOld.grid[r][c].next(pNew);
      }
    }
  }

  /**
   * Repeatedly generates plains either randomly or from reading files. 
   * Over each plain, carries out an input number of cycles of evolution. 
   * @param args
   * @throws FileNotFoundException
   */
  public static void main(String[] args) throws FileNotFoundException {

    Scanner scanner = new Scanner(System.in);
    int user_enter = 0;
    System.out.println("Simulation of Wildlife of the Plain");
    System.out.println("keys: 1 (random plain) 2 (file input) 3 (exit)\n");
    int trial = 1;
    while (user_enter != 3) {
      System.out.printf("Trial %d: ", trial);
      trial += 1;
      user_enter = scanner.nextInt();

      if (user_enter == 1) {
        System.out.println("Random plain");
        System.out.printf("Enter grid width: ");
        int width = scanner.nextInt();
        Plain even = new Plain(width);
        even.randomInit();
        System.out.printf("Enter the number of cycles: ");
        int step = scanner.nextInt();

        System.out.println("\nInitial plain:\n");
        System.out.println(even.toString());
        Plain odd = new Plain(width);
        for (int i = 0; i < step; i++) {
          if (i % 2 == 0) {
            updatePlain(even, odd);
          } else {
            updatePlain(odd, even);
          }
        }
        System.out.println("Final plain:\n");
        if (step % 2 == 0) {
          System.out.println(even.toString());
        } else {
          System.out.println(odd.toString());
        }
      } else if (user_enter == 2) {
        System.out.println("Plain input from a file");
        System.out.print("File name: ");
        Plain even = new Plain(scanner.next());
        System.out.printf("Enter the number of cycles: ");
        int step = scanner.nextInt();
        System.out.println("\nInitial plain:\n");
        System.out.println(even.toString());
        Plain odd = new Plain(even.getWidth());
        for (int i = 0; i < step; i++) {
          if (i % 2 == 0) {
            updatePlain(even, odd);
          } else {
            updatePlain(odd, even);
          }
        }
        System.out.println("Final plain:\n");
        if (step % 2 == 0) {
          System.out.println(even.toString());
        } else {
          System.out.println(odd.toString());
        }
      }
    }
    System.out.println("Exited :(");
    scanner.close();
  }
}