package myanswers;

import org.junit.Test;

import java.util.HashMap;

/**
 * Created by smridul on 1/9/19.
 */
public class LeetCode316_RemoveDuplicateLetters {

    @Test
    public void test() {

        System.out.println(removeDuplicateLetters("abacb"));
    }

    public String removeDuplicateLetters(String s) {

        if(s.isEmpty()){
            return "";
        }
        int freq[] = new int[26];

        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }

        int pos = 0;
        int i = 0;
        for (i = 0; i < s.length(); i++) {

            if (s.charAt(i) < s.charAt(pos)) {
                pos = i;
            }
            if (--freq[s.charAt(i) - 'a'] == 0) {
                break;
            }
        }

        if (s.charAt(pos) == s.charAt(i)) {
            return s.charAt(pos) + removeDuplicateLetters(s.substring(i + 1).replaceAll("" + s.charAt(pos), ""));
        } else {
            return s.charAt(pos) + removeDuplicateLetters(s.substring(i).replaceAll("" + s.charAt(pos), ""));
        }

    }

}
