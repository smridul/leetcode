package myanswers;

import org.junit.Test;

public class FirstAndLast {
    public int[] searchRange(int[] nums, int target) {
        int[] ans = new int[]{firstMatchImproved(nums, target), lastMatchImproved(nums, target)};
        return ans;
    }

    public int firstMatch(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                // equal element case
                high = mid;
            }
        }

        if (low == high) {
            return nums[low] == target ? low : -1;
        } else {
            return -1;
        }

    }


    public int firstMatchImproved(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] >= target) {
                high = mid;
            } else if (nums[mid] < target) {
                low = mid + 1;
            }
        }
        return nums.length > 0 && nums[low] == target ? low : -1;
    }

    public int lastMatch(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int low = 0;
        int high = nums.length - 1;

        while (low + 1 < high) {
            int mid = low + (high - low) / 2;

            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] < target) {
                low = mid + 1;
            } else {
                // equal element case
                low = mid;
            }
        }

        if (nums[high] == target) {
            return high;
        } else if (nums[low] == target) {
            return low;
        } else {
            return -1;
        }
    }

    public int lastMatchImproved(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low + 1) / 2;

            if (nums[mid] > target) {
                high = mid - 1;
            } else if (nums[mid] <= target) {
                low = mid;
            }
        }
        return nums.length > 0 && nums[low] == target ? low : -1;
    }

    @Test
    public void test() {
        int[] num = new int[]{5,7,7,8,8,10};
        int[] ans = searchRange(num, 8);
        System.out.println("[" + ans[0] + ", " + ans[1] + "]");
    }
}
