package myanswers;

import org.junit.Test;

import java.util.Map;

/**
 * Created by smridul on 1/2/19.
 */
public class LongestIncreasingPath {

    @Test
    public void test() {


        int [][] matrix = {
                {9, 9, 4},
                {6, 6, 8},
                {2, 1, 1}
        };

        int [][] matrix2 = {
                {3, 4, 5},
                {3, 2, 6},
                {2, 2, 1}
        };
        System.out.print(longestIncreasingPath(matrix2));
    }


    public int longestIncreasingPath(int[][] matrix) {
        if (matrix.length == 0) {
            return 0;
        }
        int[][] memo = new int[matrix.length][matrix[0].length];
        int[][] visiting = new int[matrix.length][matrix[0].length];

        int max=0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {

                int k = dfs(matrix, i, j, memo, visiting);
                max = Math.max(k, max);
            }
        }
        return max;
    }

    public int dfs(int[][] matrix, int i, int j, int[][] memo, int[][] visiting) {
        if(memo[i][j] !=0){
            return memo[i][j];
        }
        visiting[i][j] = 1;


        int left=0, right=0, top=0, bottom=0;
        if (i > 0 && matrix[i][j] > matrix[i - 1][j]) {
           top = dfs(matrix, i - 1, j, memo, visiting);
        }

        if (j > 0 && matrix[i][j] > matrix[i][j-1]) {

           left =  dfs(matrix, i, j - 1, memo, visiting);
        }
        if (i < matrix.length-1 && matrix[i][j] > matrix[i + 1][j]) {

            bottom = dfs(matrix, i + 1, j, memo, visiting);
        }
        if (j <matrix[0].length-1 && matrix[i][j] > matrix[i][j+1]) {
            right  = dfs(matrix, i, j + 1, memo, visiting);
        }
        int max = Math.max(Math.max(top, bottom), Math.max(left, right));

        visiting[i][j]=0;
        memo[i][j] = max+1;
        return max+1;
    }


}
