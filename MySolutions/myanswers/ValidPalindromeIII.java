package myanswers;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ValidPalindromeIII {

    int max;
    public boolean isValidPalindrome(String s, int k) {

        max=k;

        Map<String, Boolean> map = new HashMap<>();
        return helper(s, 0, 0, map);

    }



    boolean helper(String s, int k, int start, Map<String, Boolean> map){

        if(start >= s.length()){
            return false;
        }

        if(k > max ){
            return false;
        }


        if(isPalindrome(s, map)){
            return true;
        }

        // keep start character
        boolean keep = helper(s, k, start+1, map);

        if(keep){
            return true;
        }

        //remove current char
        boolean dontkeep = helper(s.substring(0, start) + s.substring(start+1), k+1, start, map);

        return dontkeep;

    }


    boolean isPalindrome(String s, Map<String, Boolean>map){

        if(map.containsKey(s)){
            return map.get(s);
        }
        int i=0;
        int j = s.length()-1;

        while(i<j){
            if(s.charAt(i)!=s.charAt(j)){
                map.put(s, false);
                return false;
            }
            i++;
            j--;
        }
        map.put(s, true);
        return true;
    }
    @Test
    public  void test(){
        System.out.println(isValidPalindrome("daabbddbdaadcabbccdddabaabadadadacaababdabdbaaccadadbbadaacbbbdd4", 1));
    }
}
