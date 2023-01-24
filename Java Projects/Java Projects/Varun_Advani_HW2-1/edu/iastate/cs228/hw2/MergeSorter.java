package edu.iastate.cs228.hw2;
/**
 * @author Varun Advani
 */
/**
 *
 * This class implements the mergesort algorithm.   
 *
 */
public class MergeSorter extends AbstractSorter {
    // Other private instance variables if needed
    /**
     * Constructor takes an array of points.  It invokes the superclass constructor, and also
     * set the instance variables algorithm in the superclass.
     *
     * @param pts   input array of integers
     */
    public MergeSorter(Point[] pts) {
        super(pts);
        algorithm = "mergesort";
    }
    /**
     * Perform mergesort on the array points[] of the parent class AbstractSorter.
     *
     */
    @Override
    public void sort() {
        mergeSortRec(points);
    }
    /**
     * This is a recursive method that carries out mergesort on an array pts[] of points. One
     * way is to make copies of the two halves of pts[], recursively call mergeSort on them,
     * and merge the two sorted subarrays into pts[].
     *
     * @param pts    point array
     */
    private void mergeSortRec(Point[] pts) {
        if (pts.length < 2) {
            return;
        }
        int middle = pts.length / 2;
        Point[] left = new Point[middle];
        for (int i = 0; i < middle; i++) {
            left[i] = pts[i];
        }
        Point[] right = new Point[pts.length - middle];
        for (int j = middle; j < pts.length; j++) {
            right[j - middle] = pts[j];
        }
        mergeSortRec(left);
        mergeSortRec(right);
        merge(pts, left, right);
    }
    // Other private methods if needed ...
    private void merge(Point[] target, Point[] left, Point[] right) {
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < left.length || j < right.length) {
            if (i < left.length && j < right.length) {
                if (pointComparator.compare(left[i], right[j]) < 0) {
                    target[k++] = left[i++];
                } else {
                    target[k++] = right[j++];
                }
            } else if (i < left.length) {
                target[k++] = left[i++];
            } else {
                target[k++] = right[j++];
            }
        }
    }
}