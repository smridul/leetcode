package myanswers.calculator;

import javafx.util.Pair;
import org.junit.Test;

public class BasicCalculatorParentheses {

    // recursion o(n^2) find matching ')' and pass the substring concept
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
            if (c == '(') {
                int j = i + 1;
                int braces = 1;
                for (; j < s.length(); j++) {
                    if (s.charAt(j) == '(') ++braces;
                    if (s.charAt(j) == ')') --braces;
                    if (braces == 0) break;
                }
                currentOperand = calculate(s.substring(i + 1, j));
                i = j;
            }
            if (c == '+' || c == '-' || c == '/' || c == '*' || i == s.length() - 1) {

                if (operator == '+') {
                    eval = eval + currentOperand;
                    lastOperand = currentOperand;
                }
                if (operator == '-') {
                    eval = eval - currentOperand;
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



    int index=0;
    // recursion o(n) global index
    public int calculate2(String s) {

        int currentOperand = 0;
        char operator = '+';
        int lastOperand = 0;
        int eval = 0;

        while (index < s.length()) {

            char c = s.charAt(index);
            if (Character.isDigit(c)) {
                currentOperand = currentOperand * 10 + c - '0';
            }
            if (c == '(') {
                index++;
                currentOperand = calculate2(s);
            }


            if (c == '+' || c == '-' || c == '/' || c == '*' || c==')' || index == s.length() - 1) {

                if (operator == '+') {
                    eval = eval + currentOperand;
                    lastOperand = currentOperand;
                }
                if (operator == '-') {
                    eval = eval - currentOperand;
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
            if (c == ')') {
                return eval;
            }

            index++;
        }

        return eval ;
    }

    @Test
    public void test() {

        System.out.println(calculate22("2-(5+5-2)/3+(2+8)"));
        System.out.println(calculate21("2-(5+5-2)/3+(2+8)"));

    }






    public int calculate21(String s) {
        int num = 0;
        int sign = 1;
        int res = 0;
        while(index < s.length()) {
            char ch = s.charAt(index);
            if(Character.isDigit(ch)) {
                num = num*10 + ch - '0';
            } else if (ch == '(') {
                index++;
                num = calculate21(s);
            } else if(ch == '+') {
                res = res + sign*num;
                num = 0;
                sign = 1;
            } else if(ch == '-') {
                res = res + sign*num;
                num = 0;
                sign = -1;
            }  else if (ch == ')') {
                return res + sign*num;
            }
            index++;
        }
        return res + sign*num;
    }


    // without using global index
    public int calculate22(String s) {
        return calculate22(s, 0).getKey();
    }

    public Pair<Integer, Integer> calculate22(String s, int prevIndex) {
        int num = 0;
        int sign = 1;
        int res = 0;
        int index=0;
        while(index < s.length()) {
            char ch = s.charAt(index);
            if(Character.isDigit(ch)) {
                num = num*10 + ch - '0';
            } else if (ch == '(') {
                Pair<Integer, Integer> pair = calculate22(s.substring(index+1), index+1);
                num = pair.getKey();
                index = pair.getValue();
            } else if(ch == '+') {
                res = res + sign*num;
                num = 0;
                sign = 1;
            } else if(ch == '-') {
                res = res + sign*num;
                num = 0;
                sign = -1;
            }  else if (ch == ')') {
                return new Pair<>(res + sign*num, index + prevIndex);
            }
            index++;
        }
        return new Pair<>(res + sign*num, index);
    }
}
