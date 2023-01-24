package edu.iastate.cs228.hw2;
/**
 * @author Varun Advani
 *  This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the
 * execution times of these algorithms on the same input.
 * <p>
 * <p>
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the
 * execution times of these algorithms on the same input.
 */
/**
 *
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;
public class CompareSorters {
    /**
     * Repeatedly take integer sequences either randomly generated or read from files.
     * Use them as coordinates to construct points.  Scan these points with respect to their
     * median coordinate point four times, each time using a different sorting algorithm.
     *
     * @param args
     **/
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(System.in)) {
            boolean isOver = false;
            int trial = 0;
            System.out.println("Performances of Four Sorting Algorithms in Point Scanning");
            System.out.println();
            System.out.println("keys: 1 (random integers) 2 (file input) 3 (exit)");
            while (!isOver) {
                trial++;
                int choice = readChoice(trial, scanner);
                PointScanner[] pointScanner = new PointScanner[4];
                switch (choice) {
                    case 1:
                        System.out.print("Enter number of random points: ");
                        int num = Integer.parseInt(scanner.nextLine());
                        Point[] points = generateRandomPoints(num, new Random());
                        pointScanner[0] = new PointScanner(points, Algorithm.SelectionSort);
                        pointScanner[1] = new PointScanner(points, Algorithm.InsertionSort);
                        pointScanner[2] = new PointScanner(points, Algorithm.MergeSort);
                        pointScanner[3] = new PointScanner(points, Algorithm.QuickSort);
                        for (PointScanner ps: pointScanner) {
                            ps.scan();
                        }
                        System.out.println();
                        System.out.println("    Algorithm       Size  Time(ns)");
                        System.out.println("----------------------------------");
                        for (PointScanner ps: pointScanner) {
                            System.out.println(ps.stats());
                        }
                        System.out.println("----------------------------------");
                        System.out.println();
                        break;
                    case 2:
                        System.out.print("File name: ");
                        String fileName = scanner.nextLine().trim();
                        pointScanner[0] = new PointScanner(fileName, Algorithm.SelectionSort);
                        pointScanner[1] = new PointScanner(fileName, Algorithm.InsertionSort);
                        pointScanner[2] = new PointScanner(fileName, Algorithm.MergeSort);
                        pointScanner[3] = new PointScanner(fileName, Algorithm.QuickSort);
                        for (PointScanner ps: pointScanner) {
                            ps.scan();
                        }
                        System.out.println();
                        System.out.println("    Algorithm       Size  Time(ns)");
                        System.out.println("----------------------------------");
                        for (PointScanner ps: pointScanner) {
                            ps.writeMCPToFile();
                            System.out.println(ps.stats());
                        }
                        System.out.println("----------------------------------");
                        System.out.println();
                        break;
                    case 3:
                        isOver = true;
                        System.out.println("Goodbye:(");
                        break;
                    default:
                        throw new IllegalStateException();
                }
            }
        }
    }
    private static int readChoice(int trial, Scanner scanner) {
        while (true) {
            try {
                System.out.print("Trial " + trial + ": ");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice < 1 || choice > 3) {
                    throw new Exception();
                }
                return choice;
            } catch (Exception e) {
                System.out.println("Invalid input");
            }
        }
    }
    /**
     * This method generates a given number of random points.
     * The coordinates of these points are pseudo-random numbers within the range
     * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
     *
     * Ought to be private. Made public for testing.
     *
     * @param numPts    number of points
     * @param rand      Random object to allow seeding of the random number generator
     * @throws IllegalArgumentException if numPts < 1
     */
    public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException {
        if (numPts < 1) {
            throw new IllegalArgumentException();
        }
        Point[] result = new Point[numPts];
        for (int i = 0; i < numPts; i++) {
            result[i] = new Point(rand.nextInt(101) - 50, rand.nextInt(101) - 50);
        }
        return result;
    }
}