package myanswers.addNumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AddArrayWithK {
    public List<Integer> addToArrayForm(int[] nums, int k) {

        List<Integer> ans = new ArrayList<>();


        int i= nums.length-1;
        String kstr = String.valueOf(k);
        int j = kstr.length()-1;

        int carry =0;
        while(i >=0 && j>=0){

            int d1 = i>=0 ?nums[i] :0;
            int d2 = j>=0 ? kstr.charAt(j)-'0':0;
            int sum = d1+d2+carry;
            ans.add(sum%10);
            carry = sum/10;
        }

        if(carry>0){
            ans.add(carry);
        }
        Collections.reverse(ans);
        return ans;
    }
}
