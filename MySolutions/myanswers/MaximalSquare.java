package myanswers;

import org.junit.Test;

public class MaximalSquare {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] maxArea = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            maxArea[i][0] = 0;
        }
        for (int i = 0; i <= n; i++) {
            maxArea[0][i] = 0;
        }


        int answer = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (matrix[i - 1][j - 1] == '0') {
                    maxArea[i][j] = 0;
                } else {
                    maxArea[i][j] = Math.min(Math.min(maxArea[i][j - 1], maxArea[i - 1][j]),
                            maxArea[i - 1][j - 1]) +1;
                }
                answer = Math.max(answer, maxArea[i][j]);
            }
        }
        return answer;
    }

    public int maximalSquare2(char[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] dp = new int[n + 1];

        int answer = 0;
        for (int i = 1; i <= m; i++) {
            int previous=0, current=0;
            for (int j = 1; j <= n; j++) {
                previous = current;
                if (matrix[i - 1][j - 1] == '0') {
                    current = 0;
                } else {
                    current = Math.min(Math.min(dp[j - 1], dp[j]), previous) +1;
                }
                dp[j-1] = previous;
                answer = Math.max(answer, current);
            }
            dp[n] = current;
        }
        return answer;
    }


    @Test
    public void test() {

        String a = "abc";
        System.out.println(a.substring(1, 2));
    }
}
