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
        System.out.println(expressiveWords1(s, words));
    }


    public int expressiveWords1(String s, String[] words) {
        int count = 0 ;
        for(String word :  words) {
            if(isStretchy(s, word)){
                count++;
            }
        }
        return count;
    }



    public boolean isStretchy(String s, String word){
        int i=0;
        int j=0;
        while(i < s.length() && j < word.length()){
            if(s.charAt(i)!= word.charAt(j)){
                return false;
            }
            //calculate the streak length of both strings
            int len1=1;
            int len2=1;
            while(i < s.length()-1 && s.charAt(i) == s.charAt(i+1)){
                i++;
                len1++;
            }
            // i is at last char of streak or at last character
            while(j < word.length()-1 && word.charAt(j) == word.charAt(j+1)){
                j++;
                len2++;
            }
            // j is at last char of streak of at last character
            if(len1 <=2 && len1!=len2){
                return false;
            }
            if(len1>2 && len2 > len1){
                return false;
            }
            i++;
            j++;
        }

        return i==s.length() && j==word.length() ;
    }
}
