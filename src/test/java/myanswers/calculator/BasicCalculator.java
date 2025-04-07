package myanswers.calculator;

import org.junit.Test;

import java.util.List;

public class BasicCalculator {

    public int calculate(String s) {


        int currentOperand = 0;
        char operator = '+';
        int lastOperand = 0;
        int eval = 0;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                currentOperand = currentOperand * 10 + c - '0';
            }
            if (c == '+' || c == '-' || c == '/' || c == '*' || i==s.length()-1) {

                if (operator == '+') {
                    eval = eval  + currentOperand;
                    lastOperand = currentOperand;
                }
                if (operator == '-') {
                    eval = eval  - currentOperand;
                    lastOperand = -currentOperand;
                }
                if (operator == '*') {
                    eval = eval - lastOperand + lastOperand * currentOperand;
                    lastOperand = lastOperand * currentOperand;
                }

                if (operator == '/') {
                    eval = eval - lastOperand + lastOperand / currentOperand;
                    lastOperand = lastOperand / currentOperand;
                }

                operator = c;
                currentOperand = 0;
            }

        }
        return eval;
    }


    // not using the add expression JABRAT approach
    public int calculate2(String s) {

        int currentOperand = 0;
        char operator = '+';
        int lastOperand = 0;
        int eval = 0;

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                currentOperand = currentOperand * 10 + c - '0';
            }
            if (c == '+' || c == '-' || c == '/' || c == '*' || i==s.length()-1) {

                if (operator == '+') {
                    eval = eval  + lastOperand;
                    lastOperand = currentOperand;
                }
                if (operator == '-') {
                    eval = eval  + lastOperand;
                    lastOperand = -currentOperand;
                }
                if (operator == '*') {
                    lastOperand = lastOperand * currentOperand;
                }

                if (operator == '/') {
                    lastOperand = lastOperand / currentOperand;
                }

                operator = c;
                currentOperand = 0;
            }

        }
        return eval + lastOperand;
    }

    @Test
    public void test() {
        System.out.println(calculate2("14/3*2"));
        StringBuilder str = new StringBuilder();
        String data = "a";
        String [] arr = data.split(":");
        String  sb = "ABCDE";
        String ss =  new String(sb.toCharArray(), 2, 2); //CD
        String ss2 = sb.substring(2, 2);
        int a=0;


    }
}
