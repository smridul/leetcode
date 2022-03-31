package myanswers;

import org.junit.Test;

public class Multiply {
    public String multiply(String num1, String num2) {

        int i=0;
        StringBuilder prevRes = new StringBuilder();
        while(i < num2.length()){

            StringBuilder partialRes = multihelper(num1, num2.charAt(i));

            prevRes.append('0');

            prevRes = add(prevRes, partialRes);
            i++;

        }

        return prevRes.toString();

    }



    public StringBuilder multihelper(String num1, char c){

        if(c=='0'){
            return new StringBuilder("0");
        }
        StringBuilder sb = new StringBuilder();
        int i= num1.length()-1;
        int carry=0;
        while(i>=0){
            int res = (c-'0') * (num1.charAt(i)-'0') + carry;
            carry = res/10;
            sb.append(res%10);
            i--;
        }

        if(carry>0){
            sb.append(carry);
        }

        return sb.reverse();
    }

    public StringBuilder add(StringBuilder num1, StringBuilder num2){
        StringBuilder sb = new StringBuilder();
        int i= num1.length()-1;
        int j = num2.length()-1;
        int carry=0;
        while(i>=0 || j>=0){
            char d1 = i>=0 ? num1.charAt(i) : '0';
            char d2 = j>=0 ? num2.charAt(j) : '0';
            int res = d1-'0'+ d2-'0'+carry;
            carry = res/10;
            sb.append(res%10);
            i--;
            j--;
        }

        if(carry>0){
            sb.append(carry);
        }

        return sb.reverse();
    }


    @Test
    public void test(){
        System.out.println(multiply("123", "456"));
    }
}
