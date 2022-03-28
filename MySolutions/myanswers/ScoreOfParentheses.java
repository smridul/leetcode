package myanswers;

import org.junit.Test;

public class ScoreOfParentheses {

    public int scoreOfParentheses(String s) {

        return (int) recurse(s, 0, s.length()-1);
    }



    double recurse(String s, int start, int end){

        if(start > end){
            return 0.5;
        }

        double score = 0;

        for(int i=start; i <= end; i++){

            if(s.charAt(i) == '('){
                // find the matching )


                int count =1;
                int j = i;
                while(count!=0){

                    j++;
                    if(s.charAt(j) == '('){
                        count++;
                    }else{
                        count--;
                    }
                }

                // j is at matching )

                score += 2* recurse(s, i+1, j-1);

                i = j;

            }

        }

        return score;

    }

    @Test
    public void test(){

        System.out.println(scoreOfParentheses2("((()))()()"));
    }

    public int scoreOfParentheses2(String S) {
        int res = 0, l = 0;
        for (int i = 0; i < S.length(); ++i) {
            if (S.charAt(i) == '(') l++;

            if (S.charAt(i) == ')' && S.charAt(i - 1) == '(') res += 1 << (l-1);

            if(S.charAt(i) == ')'){
                l--;
            }
        }
        return res;
    }
}
