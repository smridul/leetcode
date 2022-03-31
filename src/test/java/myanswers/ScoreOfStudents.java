package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.*;
import java.util.List;
import java.util.Set;

public class ScoreOfStudents {
    public int scoreOfStudents(String s, int[] answers) {


        List<Character> operators = new ArrayList<>();
        List<Integer> operands = new ArrayList<>();

        int operand=0;
        int eval=0;
        int lastOperand =0 ;
        char lastOperator = '+';
        for(int i= 0; i < s.length(); i++){

            char ch = s.charAt(i);
            if(Character.isDigit(ch)){
                operand = operand*10 + (ch-'0');
            }
            if(ch == '+' || ch == '*' || i==s.length()-1){

                if(lastOperator == '+'){
                    eval = eval + operand;
                    lastOperand = operand;
                } else if(lastOperator == '*'){
                    eval = (eval-lastOperand) + (lastOperand * operand);
                    lastOperand = operand*lastOperand;

                }

                if(ch =='+' || ch =='*'){
                    operators.add(ch);
                }

                lastOperator = ch;
                operands.add(operand);
                operand = 0;
            }
        }

        Set<Integer> dp[][] = new Set[operands.size()][operands.size()];

        for(int i=0; i < operands.size(); i++){
            dp[i][i] = new HashSet<>();
            dp[i][i].add(operands.get(i));
        }

        for(int len = 2; len <= operands.size(); len++){
            for(int i=0; i <= operands.size() - len; i++){
                int j = i + len-1;
                dp[i][j] = new HashSet<>();
                for(int k = i; k < j; k++){
                    // partition 1 dp[i][k]
                    // partition 2 dp[k+1][j]

                    Set<Integer> part1 = dp[i][k];
                    Set<Integer> part2 = dp[k+1][j];
                    Set<Integer> evaluate  = evaluate(part1, part2, operators.get(k));
                    dp[i][j].addAll(evaluate);
                }
            }
        }

        Set<Integer> possibleAns = dp[0][operands.size()-1];

        int score = 0;
        for(int i=0; i < answers.length; i++){
            if(eval ==answers[i]){
                score+=5;
            }else if(possibleAns.contains(answers[i])){
                score+=2;
            }
        }

        System.out.println(possibleAns.size());
        return score;
    }


    Set<Integer> evaluate(Set<Integer> part1, Set<Integer> part2, char operator){

        Set<Integer> res = new HashSet<>();
        for(int i: part1){
            for (int j: part2){

                if(operator =='+' && i+j <=1000){
                    res.add(i + j);
                }else if(operator == '*' && i*j<=1000){
                    res.add(i * j);
                }
            }
        }
        return res;

    }

    @Test
    public void test(){
        String s = "8*8*4+4*4*4+4*4";
        int [] ans = new int[]{12,9,6,4,8,6};
        System.out.println(scoreOfStudents(s,ans));
        System.out.println(scoreOfStudents1(s,ans));
    }


    public int calculate(String s) {
        int i = 0;
        Stack<Integer> stack = new Stack<>();
        char operator = '+';
        int num = 0;
        while (i < s.length()) {
            char ch = s.charAt(i++);
            if (ch >= '0' && ch <= '9') num = ch - '0';
            if (i >= s.length() || ch == '+' || ch == '*') {
                if (operator == '+') stack.push(num);
                else if (operator == '*') stack.push(stack.pop() * num);
                operator = ch;
                num = 0;
            }
        }
        return stack.stream().mapToInt(Integer::intValue).sum();
    }

    public int scoreOfStudents1(String s, int[] A) {
        int n = (int)(s.length() / 2 + 1);
        Set<Integer>[][] res = new Set[n][n];
        for (int i = 0; i < n; ++i){
            res[i][i] = new HashSet<>();
            res[i][i].add(s.charAt(2 * i) - '0');
        }
        for (int dif = 1; dif < n; ++dif){
            for (int start = 0; start < n - dif; ++start){
                int end = start + dif;
                res[start][end] = new HashSet<>();
                for (int i = start * 2 + 1; i < end * 2; i += 2){
                    if (s.charAt(i) - '+' == 0){
                        for (int a : res[start][(int)(i / 2)]){
                            for (int b : res[(int)(i / 2 + 1)][end]){
                                if (a + b <= 1000) res[start][end].add(a + b);
                            }
                        }
                    } else {
                        for (int a : res[start][(int)(i / 2)]){
                            for (int b : res[(int)(i / 2 + 1)][end]){
                                if (a * b <= 1000) res[start][end].add(a * b);
                            }
                        }
                    }
                }
            }
        }

        int correct = calculate(s), ans = 0;
        for (int a : A){
            if (a == correct) ans += 5;
            else if (res[0][n - 1].contains(a)) ans += 2;
        }
        System.out.println(res[0][n-1].size());
        return ans;

    }
}
