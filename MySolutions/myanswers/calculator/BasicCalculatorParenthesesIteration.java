package myanswers.calculator;

import org.junit.Test;

import java.util.Stack;

public class BasicCalculatorParenthesesIteration {

    public int calculate(String s) {
        int currentOperand = 0;
        char operator = '+';
        int lastOperand = 0;
        int eval = 0;

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {

            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                currentOperand = currentOperand * 10 + c - '0';
            }
            if (c == '(') {
                // order of push
                /*
                    operator; 0, 1, 2, 3 for +, -, *, /
                    lastOperand;
                    int eval;

                 */
                stack.push(getIntFromOperator(operator));
                stack.push(lastOperand);
                stack.push(eval);

                currentOperand = 0;
                operator = '+';
                lastOperand = 0;
                eval = 0;
            }

            if (c == '+' || c == '-' || c == '/' || c == '*' || c == ')' || i == s.length() - 1) {

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

                //order of push was
                 /*
                    operator; 0, 1, 2, 3 for +, -, *, /
                    lastOperand;
                    int eval;

                 */

                currentOperand = eval;
                eval = stack.pop();
                lastOperand = stack.pop();
                operator = getOperator(stack.pop());


            }
        }

        return eval;
    }


    char getOperator(int i) {
        switch (i) {
            case 0:
                return '+';
            case 1:
                return '-';
            case 2:
                return '*';
            case 3:
                return '/';
        }
        return '+';
    }

    int getIntFromOperator(char c) {
        switch (c) {
            case '+':
                return 0;
            case '-':
                return 1;
            case '*':
                return 2;
            case '/':
                return 3;
        }
        return 0;
    }

    @Test
    public void test() {

        System.out.println(calculate("(2*(5+5*2)/3+(6/2+8)) "));

    }
}
