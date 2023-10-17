package myanswers;

import org.junit.Test;

import java.util.Arrays;

public class TwoSumlessThanK {
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int i = 0;
        int j= nums.length-1;
        int sum=Integer.MIN_VALUE;
        while(i < j){
            if(nums[i] + nums[j] < k){
                sum = Math.max(sum, nums[i] + nums[j]);
                i++;
            } else if (nums[i] + nums[j] >= k){
                j--;
            }
        }
        return sum == Integer.MIN_VALUE ? -1 :sum;
    }

    @Test
    public void test4() {
        int[] arr = new int []{34,23,1,24,75,33,54,8};
        System.out.println(twoSumLessThanK(arr, 60));
    }
}
