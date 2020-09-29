import java.util.Arrays;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner6;

/**
 * 351. Count Negative Numbers in a Sorted Matrix
 * https://leetcode.com/problems/count-negative-numbers-in-a-sorted-matrix/
 */
public class Solution {


    /**
     * Create and populate grid
     */
    static int[][] populateGrid(int m, String[] strs) {

        // **** get 'n' ****
        String[] ints = strs[0].split(",");
        int n = ints.length;

        // ???? ????
        System.out.println("populateGrid <<< n: " + n);

        // **** allocate grid ****
        int[][] grid = new int[m][n];

        // **** populate grid ****
        for (int i = 0; i < m; i++) {

            // **** ****
            ints = strs[i].split(",");

            // **** ****
            for (int j = 0; j < grid[i].length; j++)
                grid[i][j] = Integer.parseInt(ints[j]);
        }

        // **** return the grid ****
        return grid;
    }


    /**
     * Runtime: 0 ms, faster than 100.00% of Java online submissions.
     * Memory Usage: 39.6 MB, less than 95.07% of Java online submissions.
     * Runtime O(n)
     */
    static int countNegatives(int[][] grid) {
        
        // **** count of negative numbers ****
        int count = 0;

        // **** numbers per row ****
        int n = grid[0].length;

        // **** loop once per array ****
        for (int i = 0; i < grid.length; i++) {

            // **** locate first negative number ****
            int j = 0;
            for ( ; j < n && grid[i][j] >= 0; j++);

            // **** update count ****
            count += (n - j);
        }

        // **** return count of negative numbers ****
        return count;
    }


    /**
     * Find the location of the first negative number in the array.
     * Recursive call.
     * Runtime O(log(n))
     */
    static int findNegLocation(int[] arr, int left, int right) {

        // **** base condition (negative number not found) ****
        if (left > right) {
            return -1;
        }

        // **** compute mid ****
        int mid = (left + right) / 2;

        // **** continue search on right ****
        if (arr[mid] >= 0)
            return findNegLocation(arr, mid + 1, right);

        // **** continue search on left ****
        else if (mid > left)
            return findNegLocation(arr, left, mid);

        // **** found location ****
        else
            return mid;
    }


    /**
     * Use binary search per row.
     * Runtime: 0 ms, faster than 100.00% of Java online submissions.
     * Memory Usage: 39.2 MB, less than 99.79% of Java online submissions.
     * Runtime O(log(n) * m)
     */
    static int countNegatives1(int[][] grid) {

        // **** count of negative numbers ****
        int count = 0; 

        // **** numbers per row ****
        int right = grid[0].length;

        // **** traverse all rows in the grid ****
        for (int i = 0; i < grid.length; i++) {

            // **** find the location of the first negative number (binary search) ****
            int firstNegLoc = findNegLocation(grid[i], 0, right - 1);

            // **** increment the count (if needed) ****
            if (firstNegLoc >= 0)
                count += (right - firstNegLoc);
        }

        // **** return count of negative numbers ****
        return count;
    }


    /**
     * Test scaffolding
     */
    public static void main(String[] args) {
        
        // **** open scanner ****
        Scanner sc = new Scanner(System.in);

        // **** read matrix data ****
        String[] strs = sc.nextLine().split("\\],\\[");

        // **** close scanner ****
        sc.close();

        // **** extract m ****
        int m = strs.length;

        // ???? ????
        System.out.println("main <<< m: " + m);

        // **** loop removing '[' and ']' ****
        for (int i = 0; i < m; i++) {
            strs[i] = strs[i].replace("[", "");
            strs[i] = strs[i].replace("]", "");
        }

        // **** create and populate grid ****
        int[][] grid = populateGrid(m, strs);

        // ???? ????
        System.out.println("main <<< grid: ");
        for (int i = 0; i < m; i++)
            System.out.println(Arrays.toString(grid[i]));

        // **** count and display count of negative numbers in grid ****
        System.out.println("main <<<  countNegatives: " + countNegatives(grid));

        // **** count and display count of negative numbers in grid ****
        System.out.println("main <<< countNegatives1: " + countNegatives1(grid));
    }
}