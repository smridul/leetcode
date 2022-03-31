package myanswers.easy;

import org.junit.Test;

import java.util.*;

public class ValidParentheses {
    public boolean isValid(String s) {
        Map<Character, Character> closingPair = new HashMap<>();
        closingPair.put(')', '(');
        closingPair.put('}', '{');
        closingPair.put(']', '[');

        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (closingPair.containsValue(c)) {
                stack.push(c);
            }  else if(stack.isEmpty()) {
                return false;
            } else if(closingPair.get(c) != stack.pop()) {
                return false;
            }
        }
        return stack.isEmpty();

    }

    @Test
    public void test() {
        System.out.println(isValid("(){}"));
    }
}
