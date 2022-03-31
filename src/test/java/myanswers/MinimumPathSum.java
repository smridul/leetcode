package myanswers;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by smridul on 2/17/19.
 */
public class MinimumPathSum {
    @Test
    public void test() {
        int array[][] = new int[][]{
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };

        System.out.println(minPathSum(array));
        System.out.println(minPathSumDP(array));
        System.out.println(minPathSumDP1D(array));
    }

    public int minPathSum(int[][] grid) {
        int[][] memo = new int[grid.length][grid[0].length];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }
        return minPathSumHelper(grid, 0, 0, memo);
    }

    public int minPathSumHelper(int[][] grid, int r, int c, int[][] memo) {
        if (r == grid.length - 1 && c == grid[0].length - 1) {
            return grid[r][c];
        }

        if (c == grid[0].length || r == grid.length) {
            return Integer.MAX_VALUE;
        }
        if (memo[r][c] != -1) {
            return memo[r][c];
        }
        // if go right
        int right = minPathSumHelper(grid, r, c + 1, memo);
        // if go down
        int down = minPathSumHelper(grid, r + 1, c, memo);
        int min = grid[r][c] + Math.min(right, down);
        memo[r][c] = min;
        return min;
    }


    public int minPathSumDP(int[][] grid) {

        int[][] dp = new int[grid.length + 1][grid[0].length + 1];
        // last row
        Arrays.fill(dp[grid.length], Integer.MAX_VALUE);

        // last column
        for (int[] row : dp) {
            row[grid[0].length] = Integer.MAX_VALUE;
        }

        for (int row = grid.length-1; row >= 0; row--) {
            for (int col = grid[0].length-1; col >= 0; col--) {
                if (row == grid.length-1 && col == grid[0].length-1) {
                    dp[row][col] = grid[row][col];
                } else {
                    dp[row][col] = grid[row][col] + Math.min(dp[row + 1][col], dp[row][col + 1]);
                }
            }
        }
        return dp[0][0];
    }


    public int minPathSumDP1D(int[][] grid) {
        //Wd actually need only curent and previous row
        int[][] dp = new int[2][grid[0].length + 1];

        // last row
        Arrays.fill(dp[1], Integer.MAX_VALUE);

        // last column
        for (int[] row : dp) {
            row[grid[0].length] = Integer.MAX_VALUE;
        }

        for (int row = grid.length-1; row >= 0; row--) {
            for (int col = grid[0].length-1; col >= 0; col--) {
                if (row == grid.length-1 && col == grid[0].length-1) {
                    dp[0][col] = grid[row][col];
                } else {
                    dp[0][col] = grid[row][col] + Math.min(dp[1][col], dp[0][col + 1]);
                }
            }

            dp[1] =  dp[0];
        }
        return dp[0][0];
    }
}
