package myanswers;

import org.junit.Test;

public class WildCardMatching {
    public boolean isMatch(String s, String p) {

        boolean dp[][] = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;

        // special handling
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') {
                dp[0][i + 1] = true;
            } else {
                break;
            }
        }


        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < p.length(); j++) {

                // to calculate dp[i+1][j+1]
                if (s.charAt(i) == p.charAt(j) && dp[i][j]) {
                    dp[i + 1][j + 1] = true;
                } else if ('?' == p.charAt(j) && dp[i][j]) {
                    dp[i + 1][j + 1] = true;
                } else if (p.charAt(j) == '*' && (dp[i + 1][j] || dp[i][j + 1])) {
                    dp[i + 1][j + 1] = true;
                }
            }
        }

        return dp[s.length()][p.length()];
    }


    @Test
    public void test() {
        System.out.print(isMatch("aa", "a"));
    }
}
