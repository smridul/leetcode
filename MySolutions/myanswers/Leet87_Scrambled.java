package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 1/15/19.
 */
public class Leet87_Scrambled {



    @Test
    public void test(){

        System.out.print(isScramble("great", "rgtae"));
    }
    public boolean isScramble(String s1, String s2) {


        if (s1.equals(s2)){
            return true;
        }
        if(s1.length()!=s2.length()){
            return false;
        }


        for(int i = 0; i < s2.length()-1; i++){

            String first = s2.substring(0, i+1);
            String second = s2.substring(i+1);

            String first2 = s2.substring(s2.length()-(i+1));
            String second2 = s2.substring(0, s2.length()-(i+1));

            String originalfirst = s1.substring(0, i+1);
            String originalsecond = s1.substring(i+1);

            if(checkIfValidPartition(first, second, originalfirst, originalsecond)){
                if( isScramble( originalfirst, first) && isScramble(originalsecond, second)){
                    return true;
                }
            } else if(checkIfValidPartition(first2, second2, originalfirst, originalsecond)){
                if( isScramble( originalfirst, first2) && isScramble(originalsecond, second2)){
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkIfValidPartition(String first, String second, String ofirst, String osecond){

        if(first.length()!=ofirst.length() || second.length()!=osecond.length()){
            return false;
        }
        int count[]  = new int[26];
        for(char c: first.toCharArray()){
            count[c-'a']++;
        }
        for(char c: ofirst.toCharArray()){
            count[c-'a']--;
        }
        for(int i: count){
            if (i!=0){
                return false;
            }
        }

        for(char c: second.toCharArray()){
            count[c-'a']++;
        }
        for(char c: osecond.toCharArray()){
            count[c-'a']--;
        }
        for(int i: count){
            if (i!=0){
                return false;
            }
        }
        return true;

    }

}
