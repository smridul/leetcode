package myanswers;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckSubarraySum {

    public boolean checkSubarraySum1(int[] nums, int k) {


        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int toFind = (sum) % k;
            if (map.get(toFind) != null && map.get(toFind) <i-1 ) {
                return true;
            }
            int mod = sum % k;
            if (map.get(toFind) == null )map.put(mod, i);
        }
        return false;
    }


    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int runningSum = 0;
        for (int i=0;i<nums.length;i++) {
            runningSum += nums[i];
            if (k != 0) runningSum %= k;
            Integer prev = map.get(runningSum);
            if (prev != null) {
                if (i - prev > 1) return true;
            }
            else map.put(runningSum, i);
        }
        return false;
    }

    @Test
    public void test() {
        int[] arr = new int[]{5, 0, 0, 0};
        checkSubarraySum1(arr, 3);
    }


}
