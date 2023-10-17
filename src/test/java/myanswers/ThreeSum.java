package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();
        for(int i =0 ; i < nums.length; i++) {

            if(i!=0 && nums[i] == nums[i-1]){
                continue;
            }
            //find the 2 sum between i+1 and n-1
            int  k = nums.length - 1;
            int j = i+1;
            int target = -nums[i];
            while(j < k) {
                if(j!=i+1 && nums[j] == nums[j-1]){
                    j++;
                    continue;
                }
                if(k!=nums.length-1 && nums[k] == nums[k+1]){
                    k--;
                    continue;
                }
                if(nums[j] + nums[k]> target){
                    k--;
                }else if(nums[j] + nums[k] < target){
                    j++;
                }else{
                    ans.add(Arrays.asList(nums[i], nums[j],  nums[k]));
                    j++;
                    k--;
                }
            }
        }
        return ans;
    }

    @Test
    public void test() {
        int a[] = new int[]{-1,0,1,2,-1,-4};
        System.out.println(threeSum(a));
    }
}
