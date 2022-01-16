package myanswers;

import org.junit.Test;

public class MaxProductSubArray {

    public int maxProduct(int[] nums) {
        int product = 1;
        Integer firstNegativeProduct = null;
        int max = Integer.MIN_VALUE;

        for (int i : nums) {
            product = product * i;
            int choice;

            if (product >= 0) {
                choice = product;
            } else {
                if (firstNegativeProduct == null) {
                    firstNegativeProduct = product;
                    choice = product;
                } else {
                    choice = product / firstNegativeProduct;
                }
            }

            max = Math.max(max, choice);
            if (i == 0) {
                product = 1;
                firstNegativeProduct = null;
            }
        }
        return max;
    }

    @Test
    public void test() {
        int[] nums = new int[]{-2,0,-1};
        System.out.println(maxProduct(nums));
    }
}
