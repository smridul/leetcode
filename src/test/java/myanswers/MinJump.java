package myanswers;

import org.junit.Test;

import java.util.Arrays;

/**
 * Created by smridul on 3/1/19.
 */
public class MinJump {

    @Test
    public void test() {
        int arr[] = new int[]{2,3,1,1,4};
        System.out.println(jump(arr));
        System.out.println(jumpII(arr));


        arr = new int[]{3, 2, 1, 0, 4};
        System.out.println(canJump(arr));
        System.out.println(jumpII(arr));


    }

    public int jump(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        // min jump requred to reach 0 is 0;
        dp[0] = 0;

        for (int i = 0; i < nums.length; i++) {
            int x = nums[i];
            for(int k = 1; i+k <= Math.min(i+x, nums.length-1); k++){
                dp[i+k] = Math.min(dp[i+k], dp[i]+1);
            }
        }
        return dp[nums.length - 1];
    }

    public int jumpII(int[] nums) {
        int maxFarthest= 0;
        int currentEnd =0 ;
        int jump=0;


        // has the  concept of window
        // we establish the current end first
        // ==> all the elements till current end can be reachable with same jump
        // now when we finish this window and enter new window
        // ==> we have to increase the jump and set the new window end to maxFarthest mainitained

        for (int i = 0; i < nums.length; i++) {
           if(maxFarthest <i){
               return -1;
           }
            if(i > currentEnd){
                currentEnd = maxFarthest;
                jump++;
            }

            maxFarthest = Math.max(maxFarthest, i+ nums[i]);
        }
        return jump;
    }

    public boolean canJump(int[] nums) {
        int max =0 ;
        for(int i=0; i< nums.length; i++){
            if(max < i){
                return false;
            }else{
                max = Math.max(max, i+ nums[i]);
            }
        }
        return true;
    }
}
