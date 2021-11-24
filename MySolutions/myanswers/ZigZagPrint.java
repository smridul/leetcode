package myanswers;

import org.junit.Test;

public class ZigZagPrint {

    public String convert(String s, int numRows) {

        if(numRows == 1){
            return s;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(int i =0; (i < numRows) && i<s.length(); i++){
            stringBuilder.append(s.charAt(i));
            if(i ==0 || i==numRows-1){
                int k=i;
                int offset = 2*(numRows-1);
                while(true){
                    k = k + offset;
                    if(k<=s.length()-1){
                        stringBuilder.append(s.charAt(k));
                    }else{
                        break;
                    }
                }
            }else{
                int k=i;
                int first = 2*(numRows-1 - i);
                int second = 2* (numRows-1) - first;
                while(true){
                    k = k + first;
                    if(k<=s.length()-1){
                        stringBuilder.append(s.charAt(k));
                    }else{
                        break;
                    }

                    k = k + second;
                    if(k<=s.length()-1){
                        stringBuilder.append(s.charAt(k));
                    }else{
                        break;
                    }
                }
            }
        }
        return stringBuilder.toString();
    }


    @Test
    public void test(){
        System.out.println(convert("A",2 ));
    }
}
