package myanswers;

import org.junit.Test;

import java.util.Stack;

public class CalculatorII {

    public int calculate(String s) {

        char[] array = s.toCharArray();

        int lastSeenInteger = 0;
        Integer firstOperand = null;
        char operation = ' ';
        for (int i = 0; i < s.length(); i++) {


            if (Character.isDigit(s.charAt(i))) {

                lastSeenInteger = lastSeenInteger * 10 + (array[i] - '0');

                if (firstOperand != null) {
                    int result;
                    if (operation == '*') {
                        result = firstOperand * lastSeenInteger;
                    } else {
                        result = firstOperand / lastSeenInteger;
                    }

                    //s = s.substring();


                }


            }
            if (s.charAt(i) == '*') {
                firstOperand = lastSeenInteger;
                operation = '*';

            }
            if (s.charAt(i) == '/') {
                firstOperand = lastSeenInteger;
                operation = '/';
            }
            lastSeenInteger = 0;

        }

        return 0;

    }


    public int calculate1(String s) {
        int len;
        if (s == null || (len = s.length()) == 0) return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0;
        char operation = '+';
        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            if (s.charAt(i) == '*' || s.charAt(i) == '/' || s.charAt(i) == '+' || s.charAt(i) == '-' ||
            i==s.length()-1) {

                if (stack.empty()) {
                    stack.push(num);
                } else {
                    if (operation == '*') {
                        stack.push(stack.pop() * num);
                    } else if (operation == '/') {
                        stack.push(stack.pop() / num);
                    } else if (operation == '-') {
                        stack.push(-1 * num);
                    } else if (operation == '+') {
                        stack.push(num);
                    }
                }
                operation = s.charAt(i);
                num = 0;
            }

        }

        int re = 0;
        for (int i : stack) {
            re += i;
        }
        return re;
    }



    public int calculate2(String s) {
        int len;
        if (s == null || (len = s.length()) == 0) return 0;
        int currentNumber = 0;
        char operation = '+';
        Integer lastNumber = null;
        int result = 0;

        for (int i = 0; i < len; i++) {
            if (Character.isDigit(s.charAt(i))) {
                currentNumber = currentNumber * 10 + s.charAt(i) - '0';
            }
            if (s.charAt(i) == '*' || s.charAt(i) == '/' || s.charAt(i) == '+' || s.charAt(i) == '-' ||
                    i==s.length()-1) {

                if (lastNumber == null) {
                    lastNumber = currentNumber;
                } else {
                    if (operation == '*') {
                        lastNumber = lastNumber * currentNumber;
                    } else if (operation == '/') {
                        lastNumber = lastNumber / currentNumber;
                    } else if (operation == '-') {
                        lastNumber = -1* currentNumber;
                    } else if (operation == '+') {
                        lastNumber = currentNumber;
                    }

                }
                if(s.charAt(i) == '+' || s.charAt(i) == '-'|| i==s.length()-1 ){
                    result+=lastNumber;
                }
                operation = s.charAt(i);
                currentNumber = 0;
            }
        }

        return result;
    }


    public int calculate3(String s) {
        if (s == null || s.isEmpty()) return 0;
        int length = s.length();
        int currentNumber = 0, lastNumber = 0, result = 0;
        char operation = '+';
        for (int i = 0; i < length; i++) {
            char currentChar = s.charAt(i);
            if (Character.isDigit(currentChar)) {
                currentNumber = (currentNumber * 10) + (currentChar - '0');
            }
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar) || i == length - 1) {
                if (operation == '+' || operation == '-') {
                    result += lastNumber;
                    lastNumber = (operation == '+') ? currentNumber : -currentNumber;
                } else if (operation == '*') {
                    lastNumber = lastNumber * currentNumber;
                } else if (operation == '/') {
                    lastNumber = lastNumber / currentNumber;
                }
                operation = currentChar;
                currentNumber = 0;
            }
        }
        result += lastNumber;
        return result;
    }

    @Test
    public void test() {
        System.out.println(calculate3("3+2*2"));
    }
}


