package myanswers;

import java.util.*;

public class ValidNumber {

    private static final List<Map<Character, Integer>> dfa = new ArrayList<>();

    public boolean isNumber(String s) {
        for(int i=0; i<8; i++){
            dfa.add(new HashMap<>());
        }
        dfa.get(0).put('d', 2);
        dfa.get(0).put('+', 1);
        dfa.get(0).put('-', 1);
        dfa.get(0).put('.', 4);

        dfa.get(1).put('d', 2);
        dfa.get(1).put('.', 4);

        dfa.get(2).put('d', 2);
        dfa.get(2).put('.', 3);
        dfa.get(2).put('e', 5);
        dfa.get(2).put('E', 5);

        dfa.get(3).put('d', 3);
        dfa.get(3).put('e', 5);
        dfa.get(3).put('E', 5);

        dfa.get(4).put('d', 3);

        dfa.get(5).put('d', 7);
        dfa.get(5).put('+', 6);
        dfa.get(5).put('-', 6);

        dfa.get(6).put('d', 7);

        dfa.get(7).put('d', 7);

        int currentState = 0;

        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                c = 'd';
            }
            if (!dfa.get(currentState).containsKey(c)) {
                return false;
            }
            currentState = dfa.get(currentState).get(c);
        }
        return currentState == 2 || currentState == 3 || currentState == 7;
    }
}
