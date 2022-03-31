package myanswers;

import org.junit.Test;

public class DecodeString {
    int global = 0;
    public String decodeString(String s) {

        StringBuilder builder = new StringBuilder();
        int num=0;
        for(int i=global; i< s.length(); i++){
            char c = s.charAt(i);
            if(Character.isAlphabetic(c)){
                builder.append(s.charAt(i));
                num= 0;
            } else if(Character.isDigit(c)){
                num = num*10 + (c-'0');
            } else if(c == '['){

                global=i+1;
                String decoded = decodeString(s);
                for(int k=num; k>0; k--){
                    builder.append(decoded);
                }
                num = 0;
                i = global;
            }else if(c == ']'){
                num=0;
                global=i;
                return builder.toString();
            }
        }
        return builder.toString();
    }


    @Test
    public void test(){

        System.out.println(decodeString("abc3[cd]xyz"));
    }

}
