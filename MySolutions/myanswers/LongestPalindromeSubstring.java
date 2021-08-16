package myanswers;

import org.junit.Test;

public class LongestPalindromeSubstring {
    int max;
    int low;
    public String longestPalindrome(String s) {


        if (s == null || s.length() <= 1) {
            return s;
        }


        int dp[][] = new int[s.length()][s.length()];
        int n = s.length();

        for (int i = 0; i <= n - 1; i++) {
            dp[i][i] = 1;
        }

        int max = 1;
        int maxi=0;
        int maxj=0;
        for (int k = 2; k <= n; k++) {
            for (int i = 0; i <= n - k; i++) {
                int j = i + k - 1;
                boolean palindrome = isPalindrome(i + 1, j - 1, dp);


                dp[i][j] = dp[i + 1][j - 1];

                if (palindrome && s.charAt(i) == s.charAt(j)) {
                    dp[i][j]+=2;
                    maxi=i;
                    maxj=j;
                }
            }
        }

        return s.substring(maxi, maxj+1);
    }

    boolean isPalindrome(int i, int j, int[][] dp) {
        if (i > j) return true;
        int len = j - i + 1;
        if (dp[i][j] == len) {
            return true;
        }
        return false;
    }


    public String longestPalindrome2(String s) {
        max = 1;
        low=0;
        if(s.length()<2){
            return s;
        }
        for(int i=0; i<=s.length()-2; i++){
            expand(i, i, s);
            expand(i, i+1, s);
        }
        return s.substring(low, low+max);
    }

    void expand(int i, int j, String s){
        if(i!=j){
            i++;
            j--;
        }
        while(i-1>=0 && j+1<=s.length()-1 && s.charAt(i-1) == s.charAt(j+1)){
            i--;
            j++;
            if(j-i+1 > max){
                max = j-i+1;
                low = i;
            }
        }
    }

    @Test
    public void test(){
        System.out.println(longestPalindrome2("cbbd"));
    }
}
