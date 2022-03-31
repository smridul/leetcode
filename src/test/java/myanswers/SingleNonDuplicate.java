package myanswers;

import org.junit.Test;

public class SingleNonDuplicate {
    public int singleNonDuplicate(int[] nums) {

        int low = 0;
        int high = nums.length - 1;

        while (low < high) {

            int mid = low + (high - low) / 2;
            int length = mid - low + 1; // this is length of first set which is bigger one

            if (nums[mid] != nums[mid + 1] && nums[mid] != nums[mid - 1]) {
                return nums[mid];
            } else if (nums[mid] != nums[mid + 1]) {
                // mid is end interval
                if (length % 2 == 1) {
                    high = mid;
                } else {
                    low = mid + 1;
                }
            } else {
                if (length % 2 == 1) {
                    low = mid + 2;
                } else {
                    high = mid - 1;
                }
            }
        }
        return nums[low];
    }

    @Test
    public void test() {
        int[] nums = new int[]{1};
        System.out.println(singleNonDuplicate(nums));
    }
}
