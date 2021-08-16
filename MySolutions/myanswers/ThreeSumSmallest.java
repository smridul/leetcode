package myanswers;

import org.junit.Test;

import java.util.Arrays;

public class ThreeSumSmallest {
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i <= nums.length - 3; i++) {
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum < target) {
                    count = count + k-j;
                    j++;
                } else {
                    k--;
                }
            }
        }
        return count;
    }

    @Test
    public void test(){
        int[] nums = new int[]{-2,0,1,3};
        System.out.println(threeSumSmaller(nums, 2));
    }
}
