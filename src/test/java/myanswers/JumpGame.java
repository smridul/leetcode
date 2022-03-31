package myanswers;

import org.junit.Test;

import java.util.Arrays;

public class JumpGame {
    public int jump(int[] nums) {

        int n = nums.length;
        int dp[] = new int[n];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[n-1] =0 ;

        for(int i=n-2; i>=0; i--){
            int maxStepsFromi = Math.min(nums[i], n-(i+1));

            for(int j=1; j<=maxStepsFromi; j++){
                dp[i] = Math.min(dp[i], 1+dp[i+j]);
            }
        }
        return dp[0];
    }

    @Test
    public void test(){
        int [] num = new int[]{2,3,0,1,4};

        System.out.println(jump(num));
    }
}
