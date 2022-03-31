package myanswers;

import org.junit.Test;

import java.util.List;

/**
 * Created by smridul on 3/9/19.
 */
public class PalindromePartitionII {

    @Test
    public void test() {

        String s = "abcb";
        System.out.println(minCut(s));
    }

    public int minCut(String s) {
        if (s == null || s.isEmpty()) return 0;
        char[] charArray = s.toCharArray();
        int[] dp = new int[charArray.length];
        for (int i = 0; i < charArray.length; i++) {

            if (isPalindrome(charArray, 0, i)) {
                dp[i] = 0;
            } else {

                int k = 0;
                int min = Integer.MAX_VALUE;

                while (k < i) {
                    if (isPalindrome(charArray, k + 1, i)) {
                        min = Math.min(min, 1 + dp[k]);
                    }
                    k++;
                }
                dp[i] = min;
            }
        }

        return dp[charArray.length - 1];

    }

    public boolean isPalindrome(char[] array, int i, int j) {
        while (i < j) {
            if (array[i] != array[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
}
