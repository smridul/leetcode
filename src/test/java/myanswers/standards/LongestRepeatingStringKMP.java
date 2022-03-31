package myanswers.standards;

import org.junit.Test;

public class LongestRepeatingStringKMP {
    @Test
    public void test2() {

        System.out.println(longestRepeatingSubstring("aabcaabdaab"));

    }


    public int longestRepeatingSubstring(String S) {
        int ans = 0;
        for(int i = 0; i < S.length(); ++i) {
            ans = Math.max(ans, computePrefix(S, i));
        }
        return ans;
    }

    // by shifting index
    public int computePrefix1(String pattern, int start) {

        // it return length not index
        int [] prefix = new int[pattern.length()];
        prefix[start] = 0;
        int k = 0;
        int max = 0;
        for (int i = start+1; i < pattern.length(); i++) {
            while (k > 0 && pattern.charAt(i) != pattern.charAt(prefix[i] + start)) {
                k = prefix[k - 1 + start];
            }

            if (pattern.charAt(i) == pattern.charAt(k+start)) {
                k = k + 1;
            }
            prefix[i] = k;
            max=Math.max(max, k);
        }
        return max;
    }



    // by not shifting index
    public int computePrefix(String pattern, int start) {

        // it return length not index
        pattern = pattern.substring(start);
        int [] prefix = new int[pattern.length()];
        prefix[0] = 0;
        int k = 0;
        int max = 0;
        for (int i = 1; i < pattern.length(); i++) {
            while (k > 0 && pattern.charAt(i) != pattern.charAt(k)) {
                k = prefix[k - 1 ];
            }

            if (pattern.charAt(i) == pattern.charAt(k)) {
                k = k + 1;
            }
            prefix[i] = k;
            max=Math.max(max, k);
        }
        return max;
    }


}
