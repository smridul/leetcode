package myanswers;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by smridul on 1/6/19.
 */
public class ClosestPalindrome {



    @Test
    public void test(){

        //System.out.print(nearestPalindromic("3"));
        System.out.print(findHigherPalindrome(10987l));

    }


    public String nearestPalindromic(String n) {

        int length = n.length();

        if (length == 0 || length == 1) {
            return ""+ (Integer.valueOf(n).intValue() -1);
        }
        StringBuilder newStr = new StringBuilder(n.substring(0, length / 2));

        String s;
        if (length % 2 != 0) {
            s = new String(newStr) + n.charAt(length / 2) + new String(newStr.reverse());
        } else {
            s = new String(newStr) + new String(newStr.reverse());

        }
        return s;

    }


    public Long findHigherPalindrome(Long limit) {
        String n = Long.toString(limit);
        char[] s = n.toCharArray(); // limit
        int m = s.length;
        char[] t = Arrays.copyOf(s, m); // target
        for (int i = 0; i < m / 2; i++) {
            t[m - 1 - i] = t[i];
        }
        for (int i = 0; i < m; i++) {
            if (s[i] < t[i]) {
                return Long.parseLong(String.valueOf(t));
            } else if (s[i] > t[i]) {
                for (int j = (m - 1) / 2; j >= 0; j--) {
                    if (++t[j] > '9') {
                        t[j] = '0';
                    } else {
                        break;
                    }
                }
                // make it palindrome again
                for (int k = 0; k < m / 2; k++) {
                    t[m - 1 - k] = t[k];
                }
                return Long.parseLong(String.valueOf(t));
            }
        }
        return Long.parseLong(String.valueOf(t));
    }

}
