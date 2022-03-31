package myanswers;

import org.junit.Test;

public class LongestOne {
    public int longestOnes(int[] nums, int k) {

        int start = 0;
        int end = 0;

        int max=0;
        while(end < nums.length){
            if(nums[end]==1){
                max = Math.max(max, end-start+1);
                end++;
            } else if(nums[end] == 0 && k>0){
                k--;
                max = Math.max(max, end-start+1);
                end++;
            }else{
                while (k <=0){

                    if(nums[start]==0){
                        k++;
                        start++;
                    }else {
                        start++;
                    }
                }
            }

        }
        return max;
    }

    @Test
    public void tt(){
        int[] num = new int[] {1,1,1,0,0,0,1,1,1,1,0};
        int k=2;
        System.out.println(longestOnes(num, 2));

    }
}
