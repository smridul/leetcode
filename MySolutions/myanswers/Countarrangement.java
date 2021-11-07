package myanswers;

import org.junit.Test;

public class Countarrangement {
    private int count = 0;

    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    private void print(int[] nums){
        for(int i=1; i<nums.length; i++){
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    public void helper(int[] nums, int start) {

        if (start == 0) {
            count++;
            return;
        }

        for (int i = start; i > 0; i--) {
            if (nums[i] % start == 0 || start % nums[i] == 0) {
                swap(nums, start, i);
                helper(nums, start - 1);
                swap(nums, start, i);
            }
        }
    }

    public int countArrangement(int N) {
        if (N == 0) return 0;
        int[] nums = new int[N + 1];
        for (int i = 0; i <= N; i++) nums[i] = i;
        helper(nums, N);
        return count;
    }


    @Test
    public void test() {
        System.out.println(countArrangement(4));
    }
}
