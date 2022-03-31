package myanswers;

import org.junit.Test;

public class DecodeWays91 {

    public int numDecodings(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }
        int dp[] = new int[s.length() + 1];
        dp[0] = 1;
        for (int i = 0; i < s.length(); i++) {
            int ways = 0;

            int singleCharSelected = Integer.parseInt("" + s.toCharArray()[i]);
            if (singleCharSelected < 10 && singleCharSelected > 0) {
                ways = dp[i];
            }

            if (i - 1 >= 0) {
                int prevCharSelected = Integer.parseInt("" + s.toCharArray()[i - 1]);
                int doubleDigit = prevCharSelected * 10 + singleCharSelected;

                if (doubleDigit < 27 && doubleDigit > 9) {
                    ways += dp[i - 1];
                }
            }
            dp[i + 1] = ways;
        }
        return dp[s.length()];
    }

    public int numDecodingsOptimzied(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        int prevValue = 0;
        int newValue =1;
        for (int i = 0; i < s.length(); i++) {
            int ways = 0;

            int singleCharSelected = Integer.parseInt("" + s.toCharArray()[i]);
            if (singleCharSelected < 10 && singleCharSelected > 0) {
                ways = newValue;
            }

            if (i - 1 >= 0) {
                int prevCharSelected = Integer.parseInt("" + s.toCharArray()[i - 1]);
                int doubleDigit = prevCharSelected * 10 + singleCharSelected;

                if (doubleDigit < 27 && doubleDigit > 9) {
                    ways += prevValue;
                }
            }
            prevValue = newValue;
            newValue = ways;
        }
        return newValue;
    }

    @Test
    public void test() {
        System.out.println(numDecodingsOptimzied("226"));
    }
}
