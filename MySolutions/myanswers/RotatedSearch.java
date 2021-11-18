package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 1/24/19.
 */
public class RotatedSearch {


    @Test
    public void test() {

        //int []a = new int[]{16, 17, 18, 19, 1, 2, 3, 4, 5};
        int[] a = new int[]{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1, 1, 1};
        System.out.print(distinct(a, 2));


         a = new int[]{4,5,6,7,8,1,2,3};
        System.out.print(distinctVersion2(a, 8));
    }

    public static int binarySearch(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int mid;

        while (low <= high) {
            mid = (low + high) / 2;

            if (nums[mid] == target) {
                return mid;
            } else {
                if (nums[mid] > target) {
                    // left subset
                    if ((nums[low] <= target && target <= nums[mid] || nums[low] > nums[mid])) {
                        high = mid - 1;
                    } else {
                        low = mid + 1;
                    }
                } else {
                    // right subset
                    if ((nums[mid] <= target && target <= nums[high] || nums[mid] > nums[high])) {
                        low = mid + 1;
                    } else {
                        high = mid - 1;
                    }
                }
            }

        }
        return -1;
    }


    public int distinct(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int mid;

        while (low <= high) {
            mid = low + (high - low) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (target > nums[mid]) {
                if (nums[low] > nums[mid] && target >= nums[low]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                if (nums[high] < nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }


    public int distinctVersion2(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        int mid;

        while (low <= high) {
            mid = low + (high - low) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (target > nums[mid]) {
                // go right ideally BUT
                // do it only if last element is greater than target
                if (nums[high] >= target || nums[high] < nums[mid]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            } else {
                // go left ideally BUT
                // do it only if first element is smaller than target
                if (nums[low] <= target || nums[low] > nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }
}
