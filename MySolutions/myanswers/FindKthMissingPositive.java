package myanswers;

import org.junit.Test;

public class FindKthMissingPositive {

    public int findKthPositive(int[] arr, int k) {

        int low = 0;
        int high = arr.length - 1;

        while (low + 1 < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] - mid - 1 >= k) {
                high = mid;
            } else {
                low = mid;
            }
        }
        int missing = 0;
        for (int i = low; i <= high; i++) {
            if (i == low) {
                missing += arr[i] - i-1;
            } else {
                missing += arr[i] - arr[i-1] - 1;
            }
            if (missing >= k) {
                return arr[i] - (missing - k) - 1;
            }
        }
        return arr[high] + k - missing;
    }

    @Test
    public void test() {

        int[] num = new int[]{2, 3, 4, 7, 11};
        int k = 5;
        System.out.println(findKthPositive(num, k));
    }
}
