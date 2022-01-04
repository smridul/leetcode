package myanswers;

import org.junit.Test;

public class MinimumWindowSubsequence {
    public String minWindow(String s, String t) {
        int end1 = 0;
        int end2 = 0;
        int minLength = Integer.MAX_VALUE;
        String min = "";

        while (end1 < s.length()) {
            if (s.charAt(end1) == t.charAt(end2)) {
                end2++;
            }

            while (end2 == t.length()) {
                // see we hae reached till end that means definitely we have found the string
                end2--;
                int posOfLastMatch = end1;
                // end1 is already pointing to last char in S
                while (end2 >= 0) {
                    if (t.charAt(end2) == s.charAt(end1)) {
                        end2--;
                    }
                    end1--;
                }
                // end1 is now pointing to a char 1 less than matching first char
                // and end2 as -1. so fix them
                end1++;
                end2++;
                int length = posOfLastMatch - end1+1;
                if (length < minLength) {
                    minLength = length;
                    min = s.substring(end1, posOfLastMatch + 1);
                }
            }
            end1++;
        }
        return min;
    }

    @Test
    public void test() {
        String s = "fgrqsqsnodwmxzkzxwqegkndaa";
        String t = "kzed";
        System.out.println(minWindow(s, t));
    }
}
