package myanswers;

import org.junit.Test;

import java.util.Arrays;


public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int[] left = new int[matrix[0].length];
        int[] right = new int[matrix[0].length];
        int[] height = new int[matrix[0].length];

        Arrays.fill(left, matrix[0].length);
        Arrays.fill(right, matrix[0].length);

        int max = 0;
        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    height[j]++;
                } else {
                    height[j] = 0;
                }
            }

            int currentLeft = 0;
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    currentLeft++;
                    left[j] = Math.min(left[j], currentLeft);
                } else {
                    currentLeft = 0;
                    left[j] = matrix[0].length;
                }
            }

            int currentRight = 0;
            for (int j = matrix[0].length - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') {
                    currentRight++;
                    right[j] = Math.min(right[j], currentRight);
                } else {
                    currentRight = 0;
                    right[j] = matrix[0].length;
                }
            }

            for (int j = 0; j < matrix[0].length; j++) {
                int area = height[j] * (left[j] + right[j] - 1);
                max = Math.max(max, area);
            }
        }
        return max;
    }


    /* This dp will not work here. Why see notes
    public int maximalRectangle(char[][] matrix) {

        if(matrix==null || matrix.length==0 || matrix[0].length == 0){
            return 0;
        }
        int m = matrix.length;
        int n = matrix[0].length;
        Dimension[][] dp = new Dimension[m + 1][n + 1];

        for (int i = 0; i < dp.length; i++) {
            dp[i][0] = null;
        }

        for (int i = 0; i < dp[0].length; i++) {
            dp[0][i] = null;
        }

        int max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                if (matrix[i][j] == '1') {


                    //choice 1

                    int newr = Math.min(dp[i][j].r, dp[i][j + 1].r) + 1;
                    int newc = Math.min(dp[i + 1][j].c, dp[i][j].c) + 1;
                    dp[i + 1][j + 1] = new Dimension(newr, newc);

                    //choice 2

                    int row = dp[i][j + 1].r;
                    int col = dp[i + 1][j].c;

                    if(row>col){
                        dp[i + 1][j + 1] = new Dimension(row+1, 1);
                    }else{
                        dp[i + 1][j + 1] = new Dimension(1, col+1);
                    }



                    if (dp[i + 1][j] != null && dp[i][j + 1] != null && dp[i][j] != null) {

                        int newr = Math.min(dp[i][j].r, dp[i][j + 1].r) + 1;
                        int newc = Math.min(dp[i + 1][j].c, dp[i][j].c) + 1;
                        dp[i + 1][j + 1] = new Dimension(newr, newc);
                    } else if(dp[i + 1][j] != null && dp[i][j + 1] != null) {

                        int row = dp[i][j + 1].r;
                        int col = dp[i + 1][j].c;

                        if(row>col){
                            dp[i + 1][j + 1] = new Dimension(row+1, 1);
                        }else{
                            dp[i + 1][j + 1] = new Dimension(1, col+1);
                        }
                    } else if(dp[i + 1][j] == null && dp[i][j + 1] != null) {
                        dp[i + 1][j + 1] = new Dimension(dp[i][j + 1].r+1, 1);
                    }else if(dp[i + 1][j] != null && dp[i][j + 1] == null) {
                        dp[i + 1][j + 1] = new Dimension(1, dp[i + 1][j].c +1);
                    }
                    else {
                        dp[i + 1][j + 1] = new Dimension(1, 1);
                    }
                } else {
                    dp[i + 1][j + 1] = null;
                }

                if(dp[i + 1][j + 1]!=null) {
                    max = Math.max(max, dp[i + 1][j + 1].r * dp[i + 1][j + 1].c);
                }

            }
        }


        return max;
    }

     */

    @Test
    public void test() {
        char[][] grid = new char[][]{
                {'1'}

        };
        System.out.println(maximalRectangle(grid));
    }
}


class Dimension {
    int r;
    int c;

    public Dimension(int r, int c) {
        this.r = r;
        this.c = c;
    }
}