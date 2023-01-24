package mini2;
import java.util.ArrayList;
public class ArrayOfSunshine
{
    // disable instantiation
    private ArrayOfSunshine() {}
    /**
     * Returns the longest string that is a prefix of
     * all strings in the given array.  For example,
     * if the array is ["foo", "four", "football"], the
     * method returns "fo".  If the array is empty, returns
     * an empty string.
     * @param arr
     *   any array of strings.
     * @return
     *   longest common prefix of the given strings
     */
    public static String longestCommonPrefix(String[] arr)
    {
        int length= arr.length;
        if (length == 0)
            return "";
        if (length == 1)
            return arr[0];
        for(int i = 0; i<length-1; i++)   
        {  
        for (int j = i+1; j<arr.length; j++)   
        {  
        if(arr[i].compareTo(arr[j])>0)   
        {  
        String temp = arr[i];  
        arr[i] = arr[j];  
        arr[j] = temp;  
        }  
        }  
        }  
        int minLength = Math.min(arr[0].length(), arr[length-1].length());
        int i = 0;
        while (i < minLength && arr[0].charAt(i) == arr[length-1].charAt(i) )
            i++;
        return arr[0].substring(0, i);
    }
    /**
     * Modifies the given array by exchanging the first half
     * with the second half.  If the array has odd length, the
     * center element is not moved.  For example, given array
     * <pre>
     * [2, 4, 6, 99, 1, 2, 3]
     * </pre>
     * after swapFirstLast the array
     * contains
     * <pre>
     * [1, 2, 3, 99, 2, 4, 6]
     * </pre>
     * Note this method modifies the given array and returns
     * void.
     * @param arr
     *   the int array to be modified
     */
    public static void swapFirstLast(int[] arr)
    {
        int length = arr.length / 2;
        int newLength = arr.length - length;
        for (int i = 0; i < length; i++) {
            int temp = arr[i];
            arr[i] = arr[newLength + i];
            arr[newLength + i] = temp;
        }
    }
    /**
     * Rearrange the elements of arr according to the given list of
     * indices.  After calling this method, arr[i] should be
     * the value formerly located at arr[swizzler[i]].
     * For example, if swizzler is [3, 0, 3, 1] and arr is
     * <pre>
     * [10, 20, 30, 40]
     * </pre>
     * and
     * then after this method executes, arr is
     * <pre>
     * [40, 10, 40, 20].
     * </pre>
     * If the swizzler length
     * does not match the array length, or if it contains any
     * number that is out of range as an index in arr,
     * the method does nothing.  Note that this method
     * modifies the given array and returns void.
     * @param arr
     *   the int array to be modified
     * @param swizzler
     *   array of indices indicating new positions of elements
     */
    public static void swizzle(int[] arr, int[] swizzler) {
        int[] temp = new int[arr.length];
        for (int j : swizzler) {
            if (j < 0 || j >= arr.length) {
                return;
            }
        }
        if(arr.length == swizzler.length) {
            for (int i=0; i<arr.length; i++) {
                temp[i] = arr[swizzler[i]];
            }
            for (int i=0; i<arr.length; i++)
            {
                if(swizzler[i] >= 0 && swizzler[i] <arr.length) {
                    arr[i]   = temp[i];
                    swizzler[i] = i;
                }
            }
        }
    }
    /**
     * Creates a new array from the given one by adding up groups
     * of k adjacent entries.  The length of the returned array
     * is always <code>arr.length / k</code> (integer division),
     * and its ith element is the sum
     * <p>
     * arr[ik] + arr[ik + 1] + arr[ik + 2] + ... + arr[ik + (k - 1)]
     * <p>
     * If the array length is not an exact multiple
     * of k, the excess elements are ignored.  For example, if
     * arr is [1, 2, 3, 4, 5, 6, 7] and k is 3, the
     * result is [6, 15].
     * @param arr
     *   any int array
     * @param k
     *   number of adjacent elements in each group
     * @return
     *   new array obtained by adding up groups of adjacent elements
     */
    public static int[] condense(int[] arr, int k)
    {
        int j = 0;
        int length = arr.length;
        int newLength = arr.length/k;
        int[] a = new int [newLength];
        int sum = arr[0];
        if(arr.length % k != 0) {
            length = length - arr.length % k;
        }
        while( j < newLength) {
            for(int i = 1; i <= length; i++) {
                if( i % k != 0){
                    sum += arr[i];
                }
                else {
                    a[j] = sum;
                    if(i == length) {
                        sum = 0;
                    }
                    else {
                        sum = arr[i];
                    }
                    j++;
                }
            } }
        return a;
    }
    /**
     * Determines whether arr is a permutation.  We define
     * a permutation as an array in which each number
     * 0 through n - 1 appears exactly once, where n is
     * the length of the array.
     * @param arr
     *   an int array
     * @return
     *   true if the given array is a permutation, false otherwise
     */
    public static boolean isPermutation(int[] arr)
    {
    	int length = arr.length;
   	 boolean isPerm = false;
   	 for(int i = 0; i<length-1; i++)   
      {  
      for (int j = i+1; j<arr.length; j++)   
      {  
      if(arr[i] >= (arr[j]))   
      {  
      int temp = arr[i];  
      arr[i] = arr[j];  
      arr[j] = temp;  
      }  
      }  
      } 
   	 for(int i = 0; i < length; i++) {
   			if(i == arr[i]) {
   				isPerm = true;
   			}
   			else {
   				isPerm = false;
   				break;
   			}
   		}
   	 if(isPerm) {
   		 return true;
   	 }
   return false;
    }
    /**
     * Helper method for cycle methods
     * @param arr
     * @return
     */
    private static boolean notPermutation(int[] arr) {
        int[] nextElement = new int[arr.length];
        for (int value : arr) {
            if (value < 0) {
                return true;
            }
            if (value >= nextElement.length) {
                return true;
            }
            if (nextElement[value] == 0) {
                nextElement[value] = 1;
            } else {
                return true;
            }
        }
        return false;
    }
    /**
     * Returns the cycle in arr beginning with the given starting index.
     * A <em>cycle</em> in an array is a sequence of indices
     * i<sub>0</sub>, i<sub>1</sub>, i<sub>2</sub>, ...i<sub>k</sub>
     * for some k &gt;= 0, such that:
     * <p>
     * i<sub>1</sub> = arr[i<sub>0</sub>], i<sub>2</sub> = arr[i<sub>1</sub>],
     * ..., and i<sub>0</sub> = arr[i<sub>k</sub>].
     * <p>The sequence
     * of indices is returned as an int array with the given start index
     * at position 0.
     * For example, given [2, 4, 0, 1, 3] and start = 3, the method
     * returns [3, 1, 4].  (Note that if arr[start] == start, then
     * the resulting cycle has length 1.)
     * Returns null if the given array is not
     * a permutation, or if the start index is out of bounds in arr.
     * @param arr
     *   an int array
     * @param start
     *   starting point for finding a cycle
     * @return
     *   array containing cycle indices, beginning with given start index
     *
     */
    public static int[] findOneCycle(int[] arr, int start)
    {
        if(notPermutation(arr)) {
            return null;
        }
        if(start == arr.length)
            return null;
        ArrayList<Integer> cycle = new ArrayList<>();
        cycle.add(start);
        if(arr[start] != start) {
            do {
                cycle.add(arr[start]);
                start = arr[start];
            } while (arr[start] != cycle.get(0));
        }
        int[] index= new int[cycle.size()];
        int i = 0;
        for (Integer n : cycle) {
            index[i++] = n;
        }
        return index;
    }
/**
     * Returns a list of all cycles in the given array.
     * For example, given [2, 1, 3, 0, 5, 4], the resulting
     * list should contain [0, 2, 3], [1], and [4, 5].
     * The cycles in the resulting list should be disjoint; that is,
     * for example,
     * the list should not contain [2, 3, 0] in addition to
     * [0, 2, 3], even though both arrays describe the same cycle.
     * The order of cycles in the list is not specified.
     * Returns an empty list if arr is not a permutation.
     * @param arr
     *   an int array
     * @return
     *   list of all cycles
     */
    public static ArrayList <int[]> findAllCycles(int[] arr)
    {
        ArrayList<int[]> arr1 = new ArrayList<>();
        if(notPermutation(arr))
            return arr1;
        ArrayList<int[]> allCycle = new ArrayList<>();
        ArrayList<Integer> exclusions = new ArrayList<>();
        for(int i = 0; i < arr.length; i++) {
            boolean isExcluded = false;
            for (Integer exclusion : exclusions) {
                if (i == exclusion) {
                    isExcluded = true;
                    break;
                }
            }
            if(!isExcluded) {
                int[] temp = findOneCycle(arr, i);
                allCycle.add(temp);
                for (int k : temp) {
                    exclusions.add(k);
                }
            }
        }
        return allCycle;
    }
}