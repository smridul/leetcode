package myanswers.binarysearch;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.*;

public class TargetIndices {


    @Test
    public void test() {
        int []nums = new int[]{100, 1, 100};
        int target = 100;

        System.out.println(targetIndices(nums, target));
    }



    @Test
    public void test1() {



        byte[] arr1 = new byte[]{-60, -6, -75, -60, -77, -24, -50, -17, -75, -60, -61, -5, -41, -42, -93, -65, 3,
                -59, -42, -59, -42};
        byte[] encodedBytes =  Base64.getEncoder().encode(arr1);
        String s = new String(encodedBytes, StandardCharsets.UTF_8);
        byte[] arr2 = Base64.getDecoder().decode(s.getBytes(StandardCharsets.UTF_8));
        System.out.println(Arrays.equals(arr1, arr2));



    }





    public List<Integer> targetIndices(int[] nums, int target) {


        int low = 0;
        int high = nums.length - 1;

        Arrays.sort(nums);
        return helper(low, high, nums, target);


    }


    List<Integer> helper(int low, int high, int[] nums, int target) {
        int mid = low + (high - low) / 2;


        while (low <= high) {

            if (nums[mid] < target) {
                return helper(mid + 1, high, nums, target);
            } else if (nums[mid] > target) {
               return helper(low, mid - 1, nums, target);
            } else {

                List<Integer> left =  helper(low, mid - 1, nums, target);
                List<Integer> right = helper(mid + 1, high, nums, target);

                List <Integer> newList = new ArrayList<>();

                newList.addAll(left);
                newList.add(mid);
                newList.addAll(right);

                return newList;
            }
        }

        return new ArrayList<>();

    }
}
