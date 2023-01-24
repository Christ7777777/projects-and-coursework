package edu.iastate.cs228.hw2;
/**
 * 
 * @author Varun Advani
 *
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner {
    private Point[] points;
    private Point medianCoordinatePoint; // point whose x and y coordinates are respectively the medians of 
    // the x coordinates and y coordinates of those points in the array points[].
    private Algorithm sortingAlgorithm;
    protected long scanTime; // execution time in nanoseconds. 
    /**
     * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
     * the points into the array points[].
     * 
     * @param  pts  input array of points 
     * @throws IllegalArgumentException if pts == null or pts.length == 0.
     */
    public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException {
        if (pts == null || pts.length == 0) {
            throw new IllegalArgumentException();
        }
        points = new Point[pts.length];
        for (int i = 0; i < pts.length; i++) {
            points[i] = new Point(pts[i]);
        }
        sortingAlgorithm = algo;
    }
    /**
     * This constructor reads points from a file. 
     * 
     * @param  inputFileName
     * @throws FileNotFoundException 
     * @throws InputMismatchException   if the input file contains an odd number of integers
     */
    protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException {
        try (Scanner scanner = new Scanner(new File(inputFileName))) {
            ArrayList <Integer> ints = new ArrayList <> ();
            while (scanner.hasNextInt()) {
                ints.add(scanner.nextInt());
            }
            if (ints.size() % 2 == 1) {
                throw new InputMismatchException();
            }
            points = new Point[ints.size() / 2];
            for (int i = 0; i < ints.size(); i += 2) {
                points[i / 2] = new Point(ints.get(i), ints.get(i + 1));
            }
            sortingAlgorithm = algo;
        }
    }
    /**
     * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
     *    
     *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
     *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
     *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
     *  
     * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
     * or QuickSorter to carry out sorting.       
     * @return
     */
    public void scan() {
        AbstractSorter aSorter;
        switch (sortingAlgorithm) {
            case InsertionSort:
                aSorter = new InsertionSorter(points);
                break;
            case MergeSort:
                aSorter = new MergeSorter(points);
                break;
            case QuickSort:
                aSorter = new SelectionSorter(points);
                break;
            case SelectionSort:
                aSorter = new SelectionSorter(points);
                break;
            default:
                throw new IllegalStateException();
        }
        int tempX = 0;
        int tempY = 0;
        for (int i = 0; i < 2; i++) {
               long startTime = System.nanoTime();
                aSorter.setComparator(i);
                aSorter.sort();
            if(i == 0) {
            	tempX = aSorter.getMedian().getX();
            }
            if(i == 1) {
            	tempY = aSorter.getMedian().getY();
            }
                scanTime += (System.nanoTime() - startTime);
        }
                medianCoordinatePoint = new Point(tempX, tempY);
    }
    /**
     * Outputs performance statistics in the format: 
     * 
     * <sorting algorithm> <size>  <time>
     * 
     * For instance, 
     * 
     * selection sort   1000	  9200867
     * 
     * Use the spacing in the sample run in Section 2 of the project description. 
     */
    public String stats() {
        return String.format("%15s %8d  %s", sortingAlgorithm.toString(), points.length, scanTime);
    }
    /**
     * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
     * in between. 
     */
    @Override
    public String toString() {
        return medianCoordinatePoint.toString();
    }
    /**
     *  
     * This method, called after scanning, writes point data into a file by outputFileName. The format 
     * of data in the file is the same as printed out from toString().  The file can help you verify 
     * the full correctness of a sorting result and debug the underlying algorithm. 
     * 
     * @throws FileNotFoundException
     */
    public void writeMCPToFile() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter("output.txt");
        printWriter.println(this.toString());
        printWriter.close();
        
    }
}