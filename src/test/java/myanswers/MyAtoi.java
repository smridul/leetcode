package myanswers;

import org.junit.Test;

public class MyAtoi {

    public int myAtoi(String s) {

        int number = 0;
        int sign = 1;
        boolean anyThingSeen = false;

        for(char c : s.toCharArray()){

            if(!anyThingSeen && (c=='+' || c=='-')){
                anyThingSeen = true;
                sign = c=='+' ? 1 :-1;
                continue;
            }

            if( !anyThingSeen && c == ' '){
                continue;
            }
            if(!Character.isDigit(c)){
                return number;
            }

            // real processing
            int digit = c-'0';

            int oldNumber = number;
            number = number *10 + sign * digit;
            int retriveOldNumber = number/10;
            if(oldNumber!=retriveOldNumber){ //overflow case
                return sign==1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            anyThingSeen = true;
        }
        return number;
    }

    @Test
    public void test(){
       //    2147483647
        //  -2147483648
        System.out.println(myAtoi("00-90"));
    }

}
