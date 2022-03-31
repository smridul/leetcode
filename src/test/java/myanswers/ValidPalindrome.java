package myanswers;

import org.junit.Test;

public class ValidPalindrome {

    public boolean validPalindrome(String s) {
        return validPalindromeHelper(s.toCharArray(), 0, s.length()-1, false);
    }


    public boolean validPalindromeHelper(char[] s, int i, int j, boolean alreadyFirstMismatch) {
        if (i >= j) {
            return true;
        }
        boolean answer;
        if (s[i] == s[j]) {
            answer = validPalindromeHelper(s, i + 1, j - 1, alreadyFirstMismatch);
        } else {

            if (alreadyFirstMismatch) {
                return false;
            } else {
                answer = validPalindromeHelper(s, i+1, j, true) ||
                        validPalindromeHelper(s, i, j-1, true);
            }
        }
        return answer;
    }


    public boolean validPalindrome2(String s) {
        return validPalindromeHelper2(s.toLowerCase().toCharArray(), 0, s.length()-1);
    }


    public boolean validPalindromeHelper2(char[] s, int i, int j) {
        while(i<s.length && !Character.isLetterOrDigit(s[i])){
            i++;
        }
        while (j>=0 && !Character.isLetterOrDigit(s[j])){
            j--;
        }
        if (i >= j) {
            return true;
        }
        if (s[i] == s[j]) {
            return validPalindromeHelper2(s, i + 1, j - 1);
        }
        return false;
    }

    @Test
    public void test(){
        System.out.println(validPalindrome2("A man, a plan, a canal: Panama"));
    }

}
