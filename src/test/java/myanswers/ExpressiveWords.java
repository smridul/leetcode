package myanswers;

import org.junit.Test;

public class ExpressiveWords {

    public int expressiveWords(String s, String[] words) {

        int count = 0;
        for (String word : words) {
            if (isSame(s, word)) {
                count++;
            }
        }
        return count;
    }


    public boolean isSame(String bigword, String smallword) {

        int i = 0;
        int j = 0;

        char[] big = bigword.toCharArray();
        char[] small = smallword.toCharArray();
        int starti = 0;
        int startj = 0;
        int count1 = 0;
        int count2 = 0;
        while (i < big.length && j < small.length && big[i] == small[j]) {
            starti = i;
            startj = j;

            while (i != big.length - 1 && big[i] == big[i + 1]) {
                i++;
            }
            count1 = i - starti + 1;
            i++;

            while (j != small.length - 1 && small[j] == small[j + 1]) {
                j++;
            }
            count2 = j - startj + 1;
            j++;
            if (count1 >= 3 && count2 > count1) {
                return false;
            }
            if (count1 < 3 && count1 != count2) {
                return false;
            }

            if (i == big.length && j == small.length) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void test(){

        String[] words = new String[]{"hello", "hi", "helo"};
        String s = "heeellooo";
        System.out.println(expressiveWords(s, words));
    }
}
