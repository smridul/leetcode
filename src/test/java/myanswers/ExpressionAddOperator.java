package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperator {

    public List<String> addOperators1(String num, int target) {

        List<String> list = new ArrayList<>();
        recurseWithPostFixOperators(num, target, 0, 0, '+', 0, "", list);
        return list;

    }

    public void recurseWithPostFixOperators(String s, int target, int start, long lastOperand, char lastOperator,
                                            long eval, String path, List<String> result) {
        if (start == s.length()) {
            if (target == eval) {
                result.add(path);
            }
            return;
        }
        for (int i = start; i < s.length(); i++) {

            if (i != start && s.charAt(start) == '0') {
                break;
            }
            String current = s.substring(start, i + 1);
            long curr = Long.parseLong(current);

            long eval1;
            long lastOperandToSend = curr;
            if (lastOperator == '+') {
                eval1 = eval + curr;

            } else if (lastOperator == '-') {
                eval1 = eval - curr;
                lastOperandToSend = -1 * curr;
            } else {
                eval1 = eval - lastOperand + lastOperand * curr;
                lastOperandToSend = curr * lastOperand;

            }

            if (i == s.length() - 1) {
                recurseWithPostFixOperators(s, target, i + 1, lastOperandToSend, '+', eval1, path + curr, result);
            } else {

                recurseWithPostFixOperators(s, target, i + 1, lastOperandToSend, '+', eval1, path + curr + "+", result);
                recurseWithPostFixOperators(s, target, i + 1, lastOperandToSend, '-', eval1, path + curr + "-", result);
                recurseWithPostFixOperators(s, target, i + 1, lastOperandToSend, '*', eval1, path + curr + "*", result);
            }
        }
    }

    @Test
    public void test() {

        List<String> res = addOperators2("105", 5);

        for (String s : res) {
            System.out.println(s);
        }
    }

    public List<String> addOperators2(String num, int target) {

        List<String> list = new ArrayList<>();
        recurseWithPrefixOperators(num, target, 0, 0, 0, "", list);
        return list;

    }


    public void recurseWithPrefixOperators(String s, int target, int start, long lastOperand, long eval, String path, List<String> result) {
        if (start == s.length()) {
            if (target == eval) {
                result.add(path);
            }
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (i != start && s.charAt(start) == '0') {
                break;
            }
            String current = s.substring(start, i + 1);
            long curr = Long.parseLong(current);

            if (start == 0) {
                recurseWithPrefixOperators(s, target, i + 1, curr, eval + curr, path + curr, result);
            } else {

                recurseWithPrefixOperators(s, target, i + 1, curr, eval + curr, path + "+" + curr, result);
                recurseWithPrefixOperators(s, target, i + 1, -1*curr, eval - curr, path + "-" + curr, result);
                recurseWithPrefixOperators(s, target, i + 1, curr * lastOperand, eval - lastOperand + lastOperand * curr, path + "*" + curr, result);
            }
        }
    }

}


