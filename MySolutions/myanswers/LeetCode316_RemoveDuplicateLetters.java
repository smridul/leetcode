package myanswers;

import org.junit.Test;

import java.util.HashMap;

/**
 * Created by smridul on 1/9/19.
 */
public class LeetCode316_RemoveDuplicateLetters {

    @Test
    public void test() {

        removeDuplicateLetters("bacdcbc");
    }

    public String removeDuplicateLetters(String s) {

        HashMap<Character, Integer> map = new HashMap<>();

        StringBuilder ss = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c)) {
                ss.append(c);
                map.put(c, i);
            } else {

                int index = map.get(c);

                // if we include this the new string is
                String newString = ss.substring(0, index) + ss.substring(index + 1, ss.length()) + c;
                // if not include,

                if (newString.compareTo(ss.toString())<0) {
                    map.put(c, index);
                    ss.replace(index, index+1, "");
                    ss.append(c);
                }
            }
        }
        return ss.toString();
    }

}
