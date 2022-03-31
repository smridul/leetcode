package myanswers;

import org.junit.Test;

import java.util.*;

/**
 * Created by smridul on 1/10/19.
 */
public class RearrangeK {

    @Test
    public void test(){
        System.out.print(rearrangeString("aa", 0));
    }


    public String rearrangeString(String s, int k) {
        HashMap<Character, LetterCount> map = new HashMap<>();

        for(char c: s.toCharArray()){
            if(map.containsKey(c)){
                 map.get(c).count++;
            }else{
                map.put(c, new LetterCount(c, 1));
            }
        }

        ArrayList<LetterCount> frequencyTable = new ArrayList<>();
        for (LetterCount lc: map.values()){
            frequencyTable.add(lc);
        }

        StringBuilder sb = new StringBuilder();
        Set<Character> kset = new HashSet<>();
        for(int i =0 ; i < s.length(); i++ ){

            char c = findMax(frequencyTable, kset);
            if(c=='_'){
                return "";
            }
            kset.add(c);
            if(kset.size() >= k){
                kset.clear();
            }
            sb.append(c);

        }
        return sb.toString();

    }

    char findMax(ArrayList<LetterCount> frequencyTable, Set<Character> kset){

        int max= Integer.MIN_VALUE;
        LetterCount maxlc = new LetterCount('_', Integer.MIN_VALUE);
        for(LetterCount lc : frequencyTable){
            if(!kset.contains(lc.c) && lc.count!=0 && lc.count > maxlc.count){
                maxlc = lc;

            }
        }
        maxlc.count--;
        return maxlc.c;
    }



}

class LetterCount{
    char c;
    int count;
    public LetterCount(char c, int count){
        this.c = c;
        this.count = count;
    }
}