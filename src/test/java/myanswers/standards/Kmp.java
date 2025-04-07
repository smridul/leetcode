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

        search2("babbababaabcbababa", "ababa");
        search2("aaaaa", "aaa");

        System.out.println("=============");
        search("babbababaabcbababa", "ababa");
        search("aaaaa", "aaa");

    }






    public void computePrefix1(String pattern, int prefix[]) {

        // it return length not index
        prefix[0] = 0;
        int k = 0;

        for(int i = 1; i < pattern.length(); i++){

            // to find prefix(i)
            //to match with (prefix[i-1] +1)th character 1st index
            // prefix[i-1] +1-1 (0th index)
            // if matched then good
            // answer is prefix[i-1]+1
            //else
            // lets say prefix[i-1] is k
            // we try to match prefix[k] + 1 th character with pattern [i]

            // K will always hold the prefix [i-1] before start of the loop
            //from line A
            // Also for first case prefix[1] = 0 also k=0
            // hence k is always equal to prefix[i-1]

            k = prefix[i-1];

            while(k > 0 && pattern.charAt(i) != pattern.charAt(k)){
                k = prefix[k-1]; // 0th index
                // k = prefix[k] // 1 index
            }

            //pattern.charAt(k) is next character of matching prefix
            /*if (pattern.charAt(i) == pattern.charAt(k+1) {1 index}) {
                k = k + 1;
            }*/
            if (pattern.charAt(i) == pattern.charAt(k)) {
                k = k + 1;
            }
            prefix[i] = k; //Line A

        }

    }

    public void search2(String text, String pattern) {

        int k = 0;
        int[] prefix = new int[pattern.length()];
        computePrefix1(pattern, prefix);

        for (int i =0; i < text.length(); i++){
            while(k > 0 && text.charAt(i)!= pattern.charAt(k)){
                k = prefix[k-1];
            }

            if(text.charAt(i) == pattern.charAt(k)){
                k = k+1;
            }

            if(k == pattern.length()){
                    // matched
                // end index is i
                // start index = i-pattern.length()+1
                System.out.println("search 2 Matched at " + (i - pattern.length() + 1));
                k = prefix[k-1]; // for next match
            }
        }
    }

    }
