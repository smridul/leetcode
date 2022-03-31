package myanswers;

import org.junit.Test;

import java.util.HashMap;

/**
 * Created by smridul on 6/13/18.
 */
class RomanToInt {

    private static HashMap<Character, Integer> letterToValue = new HashMap<>();;
    private static HashMap<Character, Integer> letterToRank = new HashMap<>();

    static {
        letterToValue.put('I', 1);
        letterToValue.put('V', 5);
        letterToValue.put('X', 10);
        letterToValue.put('L', 50);
        letterToValue.put('C', 100);
        letterToValue.put('D', 500);
        letterToValue.put('M', 1000);

        letterToRank.put('I', 1);
        letterToRank.put('V', 2);
        letterToRank.put('X', 3);
        letterToRank.put('L', 4);
        letterToRank.put('C', 5);
        letterToRank.put('D', 6);
        letterToRank.put('M', 7);
        letterToRank.put('\0', 10);

    }
    public int romanToInt(String s) {

        char[] charArray = s.toCharArray();

        int value = 0 ;
        char prev = '\0';

        for(char current: charArray ){
            value += letterToValue.get(current);

            if(letterToRank.get(current) > letterToRank.get(prev)){
                value = value- 2* letterToValue.get(prev);
            }
            prev = current;
        }

        return value;
    }

}
