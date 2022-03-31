package myanswers;

import org.junit.Test;

public class IntToRoman {
    private static final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] symbols = {"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};

    public String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();

        int remaining = num;
        while(remaining>0){
            int base = findLargestSmallerThanValue(remaining);
            remaining = remaining - values[base];
            sb.append(symbols[base]);
        }
        return sb.toString();
    }

    private int findLargestSmallerThanValue(int k){
        for(int i=0; i<values.length; i++){
            if(values[i]<=k){
                return i;
            }
        }
        return -1;
    }

    @Test
    public void test(){
        System.out.println(intToRoman(3));
    }
}
