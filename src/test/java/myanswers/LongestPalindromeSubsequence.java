package myanswers;

import org.junit.Test;

public class LongestPalindromeSubsequence {

    public int longestPalindromeSubseq(String s) {

        int[][] dp = new int[s.length()][s.length()];

        int N = s.length();
        for (int i = 0; i < N; i++) {
            dp[i][i] = 1;
        }

        for (int length = 2; length <= N; length++) {
            for (int i = 0; i <= N - length; i++) {
                int j = i + length - 1;
                if (s.charAt(i) == s.charAt(j)) {
                    if (length == 2) {
                        dp[i][j] = 2;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    }
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]);
                }
            }
        }
        return dp[0][N-1];
    }


    @Test
    public void test() {
        String s = "abbcdacgbib";
        System.out.println(longestPalindromeSubseq(s));
    }
}
