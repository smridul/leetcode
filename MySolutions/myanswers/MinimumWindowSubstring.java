package myanswers;

import org.junit.Test;

public class MinimumWindowSubstring {


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
            if (charCount[endChar] > 0 && currentWindowCount[endChar]< charCount[endChar]) {
                counter--; // happens only 1 for 1 character
            }
            currentWindowCount[endChar]++;
            while (counter == 0) {
                int length = end - start + 1;
                if (length < minLength) {
                    minLength=length;
                    min = s.substring(start, end+1);
                }
                char charAtStart = s.charAt(start);
                if(charCount[charAtStart] > 0){
                    currentWindowCount[charAtStart]--;
                    if(currentWindowCount[charAtStart] < charCount[charAtStart]){
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

        System.out.println(minWindow("cabwefgewcwaefgcf", "cae"));
    }

}
