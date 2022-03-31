package myanswers.standards;

import org.junit.Test;

public class Kmp {

    public void computePrefix(String pattern, int prefix[]) {

        // it return length not index
        prefix[0] = 0;
        int k = 0;
        for (int i = 1; i < pattern.length(); i++) {
            //k-1 character prefix end index
            // k is next character
            /*
             while (k > 0 && pattern.charAt(i) != pattern.charAt(prefix end char  + 1)) {
                k = prefix[prefix end char];
            }
             */
            while (k > 0 && pattern.charAt(i) != pattern.charAt(k)) {
                k = prefix[k - 1];
            }

            if (pattern.charAt(i) == pattern.charAt(k)) {
                k = k + 1;
            }
            prefix[i] = k;
        }
    }

    public void search(String text, String pattern) {

        int k = 0;
        int[] prefix = new int[pattern.length()];
        computePrefix(pattern, prefix);

        for (int i = 0; i < text.length(); i++) {
            while (k > 0 && pattern.charAt(k) != text.charAt(i)) {
                k = prefix[k - 1];
            }
            if (pattern.charAt(k) == text.charAt(i)) {
                k = k + 1;
            }
            if (k == pattern.length()) {
                //pattern matched at i
                System.out.println("Matched at " + (i - pattern.length() + 1));
                k = prefix[k - 1]; // this is unusual to think but is done to get next match in run
            }
        }

    }


    @Test
    public void test() {

        search("babbababaabcbababa", "ababa");
        search("aaaaa", "aaa");

    }









}
