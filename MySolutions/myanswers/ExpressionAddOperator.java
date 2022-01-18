package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ExpressionAddOperator {

    public List<String> addOperators(String num, int target) {

        List<String> list = new ArrayList<>();
        recurse(num, target, 0, 0, '+', 0, "", list);
        return list;

    }

    public void recurse(String s, int target, int start, long lastOperand, char lastOperator,
                        long eval, String path, List<String> result) {
        if (start == s.length()) {
            if (target == eval) {
                result.add(path);
            }
            return;
        }
        for(int i=start; i< s.length(); i++){

            if(i!=start && s.charAt(start) == '0'){
                break;
            }
            String current = s.substring(start, i+1);
            long curr = Long.parseLong(current);

            long eval1;
            long lastOperandToSend = curr;
            if(lastOperator == '+'){
                eval1 = eval + curr;

            }else if(lastOperator == '-'){
                eval1 = eval - curr;
                lastOperandToSend = -1*curr;
            }else{
                eval1 = eval - lastOperand + lastOperand * curr;
                lastOperandToSend = curr * lastOperand;

            }

            if(i == s.length()-1){
                recurse(s, target, i+1, lastOperandToSend, '+',eval1, path  + curr , result);
            }else {

                recurse(s, target, i + 1, lastOperandToSend, '+', eval1, path + curr + "+", result);
                recurse(s, target, i + 1, lastOperandToSend, '-', eval1, path + curr + "-", result);
                recurse(s, target, i + 1, lastOperandToSend, '*', eval1, path + curr + "*", result);
            }
        }
    }
    @Test
    public void test(){

        List<String > res = addOperators("105", 5);

        for (String s: res){
            System.out.println(s);
        }

    }

}


