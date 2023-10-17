package myanswers;

import org.junit.Test;

import java.util.Arrays;

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {

        Arrays.sort(nums);

        int min = Integer.MAX_VALUE;
        int answer = 0;


        for (int i = 0; i <= nums.length - 3; i++) {
            int j = i + 1;
            int k = nums.length - 1;

            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                if (sum > target) {
                    k--;
                } else if (sum == target) {
                    return target;
                } else {
                    j++;
                }
                if (Math.abs(sum - target) < min) {
                    min = Math.abs(sum - target);
                    answer = sum;
                }
            }
        }
        return answer;
    }
    @Test
    public void test(){
        int[] nums = new int[]{-1,2,1,-4};

        System.out.println(threeSumClosest2(nums, 1));

    }


    public int threeSumClosest2(int[] nums, int target) {
        Arrays.sort(nums);

        int min = Integer.MAX_VALUE;
        int sum = 0;
        for(int i =0 ; i < nums.length; i++) {

            // if(i!=0 && nums[i] == nums[i-1]){
            //     continue;
            // }
            int k = nums.length - 1;
            int j = i+1;
            int toFind = target - nums[i];
            while(j < k) {
                // if(j!=i+1 && nums[j] == nums[j-1]){
                //     j++;
                //     continue;
                // }
                // if(k!=nums.length-1 && nums[k] == nums[k+1]){
                //     k--;
                //     continue;
                // }
                if(Math.abs(nums[i] + nums[j] + nums[k] - target) < min){
                    min = Math.abs(nums[i] + nums[j] + nums[k] - target);
                    sum = nums[i] + nums[j] + nums[k] ;
                }
                if(nums[j] + nums[k] > toFind) {
                    k--;
                } else if (nums[j] + nums[k] < toFind){
                    j++;
                } else {
                    return target;
                }
            }
        }
        return sum;
    }
}
