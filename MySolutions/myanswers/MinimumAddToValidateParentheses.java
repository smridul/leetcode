package myanswers;

import org.junit.Test;

public class MinimumAddToValidateParentheses {
    public int minAddToMakeValid(String s) {

        int extraLeft=0;
        int extraRight=0;
        for(char c : s.toCharArray()){
            if (c == '('){
                extraLeft++;
            }else if(c== ')' && extraLeft==0){
                extraRight++;
            }else if(c== ')'){
                extraLeft--;
            }
        }
        return extraLeft + extraRight;

    }

    @Test
    public void test() {
        String s= "(((" ;
        System.out.println(minAddToMakeValid(s));

    }
}
