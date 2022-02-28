package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DifferentWaysToCompute {
    public List<Integer> diffWaysToCompute(String expression) {


        List<Character> operators = new ArrayList<>();
        List<Integer> operands = new ArrayList<>();

        int operand=0;
        for(int i= 0; i < expression.length(); i++){

            if(Character.isDigit(expression.charAt(i))){
                operand = operand*10 + (expression.charAt(i)-'0');
            }else{
                operators.add(expression.charAt(i));
                operands.add(operand);
                operand = 0;
            }

            if(i==expression.length()-1){
                operands.add(operand);
            }
        }

        List<Integer> dp[][] = new List[operands.size()][operands.size()];

        for(int i=0; i < operands.size(); i++){
            dp[i][i] = new ArrayList<>();
            dp[i][i].add(operands.get(i));
        }

        for(int len = 2; len <= operands.size(); len++){
            for(int i=0; i <= operands.size() - len; i++){
                int j = i + len-1;
                dp[i][j] = new ArrayList<>();
                for(int k = i; k < j; k++){
                    // partition 1 dp[i][k]
                    // partition 2 dp[k+1][j]

                    List<Integer> part1 = dp[i][k];
                    List<Integer> part2 = dp[k+1][j];
                    List<Integer> evaluate  = evaluate(part1, part2, operators.get(k));
                    dp[i][j].addAll(evaluate);
                }
            }
        }

        return dp[0][operands.size()-1];
    }


    List<Integer> evaluate(List<Integer> part1, List<Integer> part2, char operator){

        List<Integer> res = new ArrayList<>();
        for(int i=0; i < part1.size(); i++){
            for (int j=0; j < part2.size(); j++){

                if(operator =='+'){
                    res.add(part1.get(i) + part2.get(j));
                } else if(operator == '-'){
                    res.add(part1.get(i) - part2.get(j));
                }else if(operator == '*'){
                    res.add(part1.get(i) * part2.get(j));
                }
            }
        }
        return res;

    }

    @Test
    public void test(){
        String s = "2*3-4*5";
        System.out.print(diffWaysToCompute(s));
    }
}
