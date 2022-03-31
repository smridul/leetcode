package myanswers;

import org.junit.Test;

import java.util.*;

public class ReorganizeString {

    public String reorganizeString(String s) {
        int [] count = new int[26];
        int max=0;
        int maxi=0;
        for(int i=0; i<s.length(); i++){
            int cc = ++count[s.charAt(i)-'a'];

            if(cc > Math.ceil(s.length()/2)){
                return "";
            }
            if(cc > max){
                maxi = s.charAt(i)-'a';
                max = cc;
            }
        }

        char[] result = new char[s.length()];
        int currentIndex=0;
        while(count[maxi]-- > 0){
            result[currentIndex] = (char)('a' + maxi);
            currentIndex = currentIndex+2;
        }


        for(int i=0; i<26; i++){
            if(currentIndex >= s.length()){
                currentIndex = 1;
            }

            while(count[i]-- > 0){
                result[currentIndex] = (char)('a' + i);
                currentIndex=currentIndex+2;
                if(currentIndex >= s.length()){
                    currentIndex = 1;
                }
            }
        }

        return new String(result);
    }


    @Test
    public void test(){
        System.out.println(reorganizeString("aaab"));
    }
}
