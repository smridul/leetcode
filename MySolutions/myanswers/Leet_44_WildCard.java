package myanswers;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by smridul on 1/3/19.
 */
public class Leet_44_WildCard {


    List<Character> characterslist = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f');

    @Test
    public void test() {
        // String s = "abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb";
        //String p = "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb";


        String p = "*a?b*";
        String s = "zacabz";
        boolean invalidmemo[][] = new boolean[s.length() + 1][p.length() + 1];
        System.out.println(isMatch(s, p, 0, 0, invalidmemo));

        System.out.println(isMatchDP(s, p));

        //s = "aa";
        //p = "a*";

        s = "ab";
        p = ".*";

        // s = "aab";
        //p = "c*a*b";

        //s = "mississippi";
        // p = "mis*is*p*.";

        //s = "mississippi";
        //p = "mis*is*ip*.";

        s= "aaa";
        p="ab*a*c*a";

        System.out.println("Regular Match ");
        System.out.println("s: " + s);
        System.out.println("p: " + p);

        //System.out.println(regularExpMatchDP(s, p));

        System.out.println(regularExpMatchDP2(s, p));


    }

    public boolean isMatch(String s, String p, int sstart, int pstart, boolean invalidmemo[][]) {

        if (invalidmemo[sstart][pstart]) {
            return false;
        }
        int pindex = pstart;
        int sindex = sstart;
        for (; pindex < p.length(); pindex++) {
            if (p.charAt(pindex) == '*') {

                while (pindex + 1 < p.length() && (p.charAt(pindex) == '*') && (p.charAt(pindex) == p.charAt(pindex + 1))) {
                    pindex++;
                }
                // * case
                // incrementally skip characters from s starting from 0 to lastindex
                for (int i = 0; sindex + i <= s.length(); i++) {
                    boolean match = isMatch(s, p, sindex + i, pindex + 1, invalidmemo);
                    if (match) {
                        return true;
                    } else {
                        invalidmemo[sindex + i][pindex + 1] = true;
                    }
                }
                return false;
            }

            if (sindex < s.length()) {
                if (Character.isLetter(p.charAt(pindex))) {
                    return p.charAt(pindex) == s.charAt(sindex) &&
                            isMatch(s, p, sindex + 1, pindex + 1, invalidmemo);
                } else if (p.charAt(pindex) == '?') {
                    return isMatch(s, p, sindex + 1, pindex + 1, invalidmemo);
                }
            } else {
                break;
            }
        }

        return (pindex >= p.length() && sindex >= s.length());
    }


    public boolean isMatchDP(String searchString, String pattern) {

        boolean dp[][] = new boolean[pattern.length() + 1][searchString.length() + 1];
        // We want to go in reverse
        //base case
        dp[pattern.length()][searchString.length()] = true;

        for (int i = pattern.length() - 1; i >= 0; i--) {
            if (pattern.charAt(i) == '*') {
                dp[i][searchString.length()] = true;
            } else {
                break;
            }
        }

        for (int i = pattern.length() - 1; i >= 0; i--) {
            for (int j = searchString.length() - 1; j >= 0; j--) {
                if (pattern.charAt(i) == '?' || pattern.charAt(i) == searchString.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else if (pattern.charAt(i) == '*') {
                    // if any of the dp[i+1][k] k varies from j to last J which is searchstring.length
                    // answer is true


                   /* int k = j;
                    while(k <= searchString.length()){
                        /*if(dp[i+1][k]){
                            dp[i][j] = true;
                            break;
                        }
                        k++;
                    }*/

                    // now with optimization
                    // using previous result
                    dp[i][j] = dp[i + 1][j] || dp[i][j + 1];
                }
            }
        }
        return dp[0][0];
    }


    public boolean regularExpMatchDP(String searchString, String pattern) {

        boolean dp[][] = new boolean[pattern.length() + 1][searchString.length() + 1];
        // We want to go in reverse
        //base case
        dp[pattern.length()][searchString.length()] = true;

        for (int i = pattern.length() - 1; i >= 0; i--) {
            if (pattern.charAt(i) == '*') {
                i--;
                dp[i][searchString.length()] = true;
            } else {
                break;
            }
        }

        boolean wildcard = false;
        for (int i = pattern.length() - 1; i >= 0; i--) {

            if (pattern.charAt(i) == '*') {
                wildcard = true;
                continue;
            }

            for (int j = searchString.length() - 1; j >= 0; j--) {
                if (!wildcard && (pattern.charAt(i) == '.' || pattern.charAt(i) == searchString.charAt(j))) {
                    dp[i][j] = dp[i + 1][j + 1];
                } else if (wildcard) {
                    char prec = pattern.charAt(i);
                    dp[i][j] = dp[i + 2][j] || prec == '.' && dp[i][j + 1] || prec == searchString.charAt(j) && dp[i][j + 1];
                }
            }
            wildcard = false;
        }
        return dp[0][0];
    }

    // left to right traversal
    public boolean regularExpMatchDP2(String searchString, String pattern) {

        if (searchString == null || pattern == null) {
            return false;
        }

        boolean[][] dp = new boolean[pattern.length() + 1][searchString.length() + 1];
        dp[0][0] = true;

        // assumption dp [i][j] we are starting from i=1, j=1 ==> for string i=0, j=0
        // dp[0][0] ==> empty s, empty p


        for (int i = 1; i < pattern.length(); i++) {
            if (pattern.charAt(i) == '*') {
                dp[i + 1][0] = true;
                i++;
            } else {
                break;
            }
        }

        for (int i = 0; i < pattern.length(); i++) {
            for (int j = 0; j < searchString.length(); j++) {

                if (pattern.charAt(i) == searchString.charAt(j) || pattern.charAt(i) == '.') {
                    // dp[i][j] = dp[i-1][j-1];
                    dp[i + 1][j + 1] = dp[i][j];
                } else if (pattern.charAt(i) == '*') {

                    if (pattern.charAt(i - 1) == searchString.charAt(j) ||
                            pattern.charAt(i - 1) == '.') {
                        //dp[i][j] = dp[i][j-1]
                      //   we are counting the string character as match

                        // but even if character match this is possible that the character ther is
                        // not the result of this char*, hence
                        // include dp[i][j] = dp[i][j-1]||
                        //  dp[i-2][j] // i,e result of NOT this wild card match


                        // THIS WAS THE TRICK, JAAN OF QUESTION: the OR condition
                        dp[i + 1][j + 1] = dp[i + 1][j] || dp[i-1][j+1];

                    } else {
                        // it does not match with prec character
                        //dp[i][j] = dp[i-2][j]
                        dp[i + 1][j + 1] = dp[i - 1][j + 1];
                    }
                }
            }
        }
        return dp[pattern.length()][searchString.length()];
    }
}
