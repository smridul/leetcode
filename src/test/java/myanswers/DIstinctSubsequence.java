package myanswers;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by smridul on 1/19/19.
 */
public class DIstinctSubsequence {

    @Test
    public void test() {
        String s = "abc";
        System.out.print(numDistinctIIHelper(s, 0, "", new HashSet<>()));
        System.out.println();
        System.out.print(distinctSubseqII(s));

        System.out.println();
        System.out.print(distinctSubseqIIMy(s));

    }



    public int distinctSubseqIIMy(String S) {
        int n = S.length(),  result = 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = i-1; j >=0; j--) {
                if (S.charAt(j) != S.charAt(i)) {
                    dp[i] += dp[j];
                }else{
                    dp[i] += dp[j]-1;
                    break;
                }
            }
            result += dp[i];
        }
        return result;
    }

    public int distinctSubseqII(String S) {
        int n = S.length(),  result = 0;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i ; j++) {
                if (S.charAt(j) != S.charAt(i)) {
                    dp[i] += dp[j];
                }
            }
            result += dp[i];
        }
        return result;
    }

    public int numDistinctIIHelper(String s, int i, String prefix, Set<String> hashset) {
        if(i==s.length() ){
            if(prefix.isEmpty() || hashset.contains(prefix)){
                return 0;
            }
            hashset.add(prefix);
            return 1;
        }
        // if selected
        int selected = numDistinctIIHelper(s, i+1, prefix + s.charAt(i), hashset);
        // if not selected
        int notselected = numDistinctIIHelper(s, i+1, prefix, hashset);
        return selected + notselected;
    }

    }
