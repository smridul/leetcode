package myanswers;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutrepeating {

    public int lengthOfLongestSubstring(String s) {

        if(s==null || s.length() == 0){
            return 0;
        }

        int i=0, j=0;

        int max =0;
        Map<Character, Integer> map = new HashMap<>();
        for (; i < s.length() && j < s.length(); ){

            if(map.containsKey(s.charAt(j)) && map.get(s.charAt(j)) >=i){
                i=map.get(s.charAt(j)) + 1;
            }
            map.put(s.charAt(j), j);
            max = Math.max(max, j-i+1);
            j++;
        }
        return max;
    }

    @Test
    public void test(){
        System.out.println(lengthOfLongestSubstring("abba"));
    }
}
