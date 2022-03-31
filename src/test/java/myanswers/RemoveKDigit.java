package myanswers;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class RemoveKDigit {

    public String removeKdigits(String num, int k) {

        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < num.length(); i++) {
            while (k != 0 && !stack.isEmpty() && stack.getLast() > num.charAt(i)) {
                stack.removeLast();
                k--;
            }
            stack.addLast(num.charAt(i));
        }

        while (!stack.isEmpty() && k-- > 0) {
            stack.removeLast();
        }

        StringBuilder result = new StringBuilder();

        while (!stack.isEmpty()) {
            char element = stack.removeFirst();
            if (element != '0' || result.length() != 0) {
                result.append(element);
            }
        }

        return result.length() == 0 ? "0" : result.toString();

    }

    @Test
    public void test() {
        System.out.println(removeKdigits("10", 2));
    }
}
