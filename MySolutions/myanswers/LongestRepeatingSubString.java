package myanswers;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class LongestRepeatingSubString {

    public int longestRepeatingSubstring(String s) {

        int dp[][] = new int[s.length() + 1][s.length() + 1];

        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; j < s.length(); j++) {
                if(i!=j && s.charAt(i) == s.charAt(j)){
                    dp[i+1][j+1] = dp[i][j]+1;
                    max = Math.max(max, dp[i+1][j+1]);
                }
            }
        }
        return max;
    }

    @Test
    public void test(){
        String s = "abcd";
        System.out.println(longestRepeatingSubstring1(s));
    }



    public int longestRepeatingSubstring1(String S) {
        int n = S.length();
        int start = 0;
        int end = n - 1;
        int maxLen = 0;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (search(mid, S)) {
                maxLen = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return maxLen;
    }

    public boolean search(int len, String S) {
        Set<String> seen = new HashSet<>();
        for (int i = 0; i + len <= S.length(); i++) {
            String curr = S.substring(i, i+len);
            if (seen.contains(curr)) {
                return true;
            }
            seen.add(curr);
        }
        return false;
    }

}
