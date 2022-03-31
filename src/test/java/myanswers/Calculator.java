package myanswers;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * Created by smridul on 3/1/19.
 */
public class Calculator {

    @Test
    public void test() {


        String s = "(2+6* 3+5- (3*14/7+2)*5)+3";
        System.out.println(calculate(s));

        s = "1 + 1";
        System.out.println(calculate(s));

        s = " 6-4 / 2 ";
        System.out.println(calculate(s));

        s = "2*(5+5*2)/3+(6/2+8)";
        System.out.println(calculate(s));

        s="0-2147483648";
        System.out.println(calculate(s));

        s =" 2-1 + 2 ";
        System.out.println(calculate(s));


    }

    public int calculate(String s) {
        Stack<String> stack = new Stack<>();


        char[] array = s.toCharArray();


        for (int i = 0; i < array.length; i++) {
            char current = array[i];
            if (current == ' ') continue;

            else if (current == '(') {
                stack.push("(");
            }
           else if (current == '+' || current == '-') {
                stack.push("" + current);
            }

           else if (current == '/' || current == '*') {

                // get the next token
                for (int k = i + 1; k < array.length; k++) {
                    if (array[k] != ' ') {
                        if (array[k] == '(') {
                            stack.push("" +current);
                            stack.push("(");
                            i = k;
                        } else {
                            // it is a number
                            String number = getNumber(array, k);
                            i = k + number.length() - 1;
                            String prev = stack.pop();
                            stack.push(eval(prev, number, "" + current));
                        }
                        break;
                    }
                }
            }

            else if (current == ')') {
                String result = stack.pop();
                Deque<String> evalExpression = new ArrayDeque<>();
                evalExpression.addFirst(result);
                while (!"(".equals(stack.peek())) {
                    String operand = stack.pop();
                    evalExpression.addFirst(operand);

                    String number = stack.pop();
                    evalExpression.addFirst(number);

                }

                result = evaluateExpression(evalExpression);

                // got "("
                stack.pop();


                //at this point we have evaluated the parentheses.
                // if last operand was / or * operate it now
                if (!stack.isEmpty() &&
                        (stack.peek().equals("*") || stack.peek().equals("/"))) {

                    String operand = stack.pop();
                    String number = stack.pop();
                    result = eval(number, result, operand);
                }

                stack.push(result);
            }
            else {
                // token is number
                String number = getNumber(array, i);
                i = i + number.length() - 1;
                stack.push(number);
            }
        }

        // At this point, we should have only + and - in stack. No * or /
        String result = "";
        if (!stack.isEmpty()) {
            Deque<String> evalExpression = new ArrayDeque<>();

            result = stack.pop();
            evalExpression.addFirst(result);
            while (!stack.isEmpty()) {
                String operand = stack.pop();
                evalExpression.addFirst(operand);
                String number = stack.pop();
                evalExpression.addFirst(number);
            }
            result = evaluateExpression(evalExpression);
            return Integer.parseInt(result);
        }
        return 0;
    }


    private String getNumber(char[] array, int k) {
        StringBuilder s = new StringBuilder();
        while (k < array.length && Character.isDigit(array[k])) {
            s.append(array[k]);
            k++;
        }
        return s.toString();
    }

    private String eval(String s1, String s2, String operand) {
        double first = Double.parseDouble(s1);
        double second = Double.parseDouble(s2);

        double result = 0;

        if (operand.equals("+")) {
            result = first + second;
        } else if (operand.equals("-")) {
            result = first - second;
        } else if (operand.equals("*")) {
            result = first * second;
        } else if (operand.equals("/")) {
            result = (int ) (first / second);
        }
        return String.valueOf((int)result);
    }

    private String evaluateExpression(Deque<String> deque){
        String result = deque.removeFirst();
        while (!deque.isEmpty()){
            String operand  = deque.removeFirst();
            String second = deque.removeFirst();

            result = eval(result, second, operand);
        }
        return result;

    }
}
