package myanswers;

public class PlusOne {

    public int[] plusOne(int[] digits) {
        int[] ans;
        boolean init=false;
        for(int i : digits){
            if(i!=9){
                //ans = new int[digits.length];
                init=true;
                break;
            }
        }

        if(!init){
            ans = new int[digits.length+1];
        }else{
            ans = new int[digits.length];
        }

        int carry = 1;
        for(int i=digits.length-1; i >= 0; i--){
            ans[i] = (carry + digits[i])%10;
            carry = (carry + digits[i])/10;
        }
        return ans;

    }
}
