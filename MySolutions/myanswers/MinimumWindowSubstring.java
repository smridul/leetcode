package myanswers;

import org.junit.Test;

import java.util.Arrays;

public class MinimumWindowSubstring {


    // green book approach
    // didn't work because not ditinct chars
    public String minWindow2(String s, String t) {

        int[] maxIndex = new int[s.length()];
        Arrays.fill(maxIndex, s.length());

        int[] charCount = new int[128]; // 33 to 126
        int[] currentWindowCount = new int[128]; // 33 to 126

        int lastIndex=-1;
        for (int j = 0; j < t.length(); j++) {
            char textChar = t.charAt(j);
            charCount[textChar]++;
            currentWindowCount = new int[128];
            for (int i = 0; i < s.length(); i++) {
                currentWindowCount[s.charAt(i)]++;
                int index = -1;
                if (textChar == s.charAt(i) && currentWindowCount[s.charAt(i)] >= charCount[textChar]) {
                    index = i; // this is  not correct with repeated chars in text string
                    // insteead this should be index of last occurence of "aa" if 2nd a
                    //  last occurence of "aaa" if 3rd "a". that way.
                    // ==> what the current text char count is
                } else if (i != 0) {
                    index = lastIndex;
                }
                lastIndex = index;
                maxIndex[i] = Math.min(index, maxIndex[i]);
            }
        }


        int minLength = s.length()+1;
        String min = "";
        for (int i = 0; i < maxIndex.length; i++) {
            if (maxIndex[i] != -1 && i-maxIndex[i]+1 < minLength) {
                min = s.substring(maxIndex[i], i+1);
                minLength = i-maxIndex[i]+1;
            }
        }
        return min;
    }

    public String minWindow(String s, String t) {
        int[] charCount = new int[128]; // 33 to 126
        int[] currentWindowCount = new int[128]; // 33 to 126
        for (char c : t.toCharArray()) {
            charCount[c]++;// it will be max 1 because distinct t
        }
        int end = 0;
        int start = 0;
        int minLength = Integer.MAX_VALUE;
        String min = "";
        int counter = t.length();

        while (end < s.length()) {
            char endChar = s.charAt(end);
            if (charCount[endChar] > 0 && currentWindowCount[endChar] < charCount[endChar]) {
                counter--; // happens only 1 for 1 character
            }
            currentWindowCount[endChar]++;
            while (counter == 0) {
                int length = end - start + 1;
                if (length < minLength) {
                    minLength = length;
                    min = s.substring(start, end + 1);
                }
                char charAtStart = s.charAt(start);
                if (charCount[charAtStart] > 0) {
                    currentWindowCount[charAtStart]--;
                    if (currentWindowCount[charAtStart] < charCount[charAtStart]) {
                        counter++;
                    }
                }
                start++;
            }
            end++;
        }
        return min;
    }


    @Test
    public void test() {

        String s= "aa" ;
        String t = "aa";
        System.out.println(minWindow(s, t));
        System.out.println(minWindow2(s, t));

    }

}
