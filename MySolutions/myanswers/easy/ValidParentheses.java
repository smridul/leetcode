package myanswers.easy;

import org.junit.Test;

import java.util.*;

public class ValidParentheses {
    public boolean isValid(String s) {
        Map<Character, Character> closingPair = new HashMap<>();
        closingPair.put(')', '(');
        closingPair.put('}', '{');
        closingPair.put(']', '[');

        Set<Character> opening = new HashSet<>(Arrays.asList('(', '{', '['));
        int i = 0;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (closingPair.containsValue(c) || stack.isEmpty()) {
                stack.push(c);
            } else if(closingPair.get(c) != stack.pop()) {
                return false;
            }
        }
        return stack.isEmpty();
    }


    @Test
    public void test() {
        System.out.println(isValid(")("));
    }
}
