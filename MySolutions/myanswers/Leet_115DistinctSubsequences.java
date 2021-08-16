package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 1/18/19.
 */
public class Leet_115DistinctSubsequences {

    @Test
    public void test() {
        System.out.print(numDistinct("babgbag", ""));
    }


    public int numDistinct(String s, String t) {
        int[][] memo = new int[s.length()][t.length()];
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < t.length(); j++) {
                memo[i][j] = -1;
            }
        }
        return numDistinct2(s, t, 0, 0, memo);
    }

    public int numDistinct2(String s, String t, int i, int j, int[][] memo) {
        if (j == t.length()) {
            return 1;
        }
        if (i == s.length() || s.substring(i).length() < t.substring(j).length()) {
            return 0;
        }


        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int whenselected = 0;
        // if matches
        if (s.charAt(i) == t.charAt(j)) {
            // lets select it
            whenselected = numDistinct2(s, t, i + 1, j + 1, memo);
        }
        //lets skip this
        int whennotselected = numDistinct2(s, t, i + 1, j, memo);

        memo[i][j] = whennotselected + whenselected;
        return whennotselected + whenselected;
    }
}
