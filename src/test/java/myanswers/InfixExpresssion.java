package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 2/28/19.
 */
import static myanswers.ExpressionState.*;

public class InfixExpresssion {

    @Test
    public void test() {

        String s = "4 + &3";
        System.out.println(s + " " + isValid(s));

        s = "4 + 3&";
        System.out.println(s + " " + isValid(s));

        s = "4 + 3";
        System.out.println(s + " " + isValid(s));

        s = "4 +";
        System.out.println(s + " " + isValid(s));

        s = "+1";
        System.out.println(s + " " + isValid(s));

        s = "4 + &3 + 6";
        System.out.println(s + " " + isValid(s));

        s = "4&";
        System.out.println(s + " " + isValid(s));

        s = "&4";
        System.out.println(s + " " + isValid(s));

        s = "    ";
        System.out.println(s + " " + isValid(s));

        s = "&123";
        System.out.println(s + " " + isValid(s));

    }

    public boolean isValid(String expressionString) {

        // edge case
        if (expressionString == null) return false;
        if (expressionString.trim().isEmpty()) return true;

        ExpressionState state = start;
        char[] expressionArray = expressionString.toCharArray();

        for (int i = 0; i < expressionArray.length; i++) {
            if (expressionArray[i] == ' ') {
                continue;
            }
            if (state == start) {
                if (expressionArray[i] == '&' || Character.isDigit(expressionArray[i])) {
                    state = expressionArray[i] == '&' ? unary : digit;
                } else {
                    return false;
                }
            } else if (state == unary) {
                if (Character.isDigit(expressionArray[i])) {
                    state = digit;
                } else {
                    return false;
                }
            } else if (state == binary) {
                if (Character.isDigit(expressionArray[i]) || expressionArray[i] == '&') {
                    state = expressionArray[i] == '&' ? unary : digit;
                } else {
                    return false;
                }
            } else if (state == digit) {
                if(Character.isDigit(expressionArray[i])){
                    continue; // remain in same state
                }
                else if (expressionArray[i] == '+') {
                    state = binary;
                }
                else {
                    return false;
                }
            }
        }
        return state == digit;
    }
}

enum ExpressionState {
    start,
    binary,
    unary,
    digit
}
