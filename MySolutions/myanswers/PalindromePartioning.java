package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PalindromePartioning {

    public List<List<String>> partition(String s) {
        List<List<String>>[] dp = new List[s.length() + 1];
        boolean[][] palindrome = new boolean[s.length()][s.length()];

        int N = s.length();
        dp[N] = new ArrayList<>();
        dp[N].add(new ArrayList<>());


        for (int i = N - 1; i >= 0; i--) {
            dp[i] = new ArrayList<>();
            for (int k = i; k <= N - 1; k++) {
                if (isPalindrome(s, i, k, palindrome)) {
                    palindrome[i][k] = true;
                    List<List<String>> lists = dp[k + 1];
                    for (List list : lists) {
                        LinkedList<String> newList = new LinkedList<>(list);
                        newList.addFirst(s.substring(i, k + 1));
                        dp[i].add(newList);
                    }
                }
            }
        }

        return dp[0];
    }

    boolean isPalindrome(String s, int i, int j, boolean[][] palindrome) {
        return (i==j || s.charAt(i) == s.charAt(j) && (j==i+1 || palindrome[i + 1][j - 1]));
    }


    @Test
    public void test(){
        List<List<String>> lists = partition("bbab");

        for(List<String> list : lists){
            for(String s: list){
                System.out.print(s + " ");
            }
            System.out.println();
        }
    }
}


