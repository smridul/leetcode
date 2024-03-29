package myanswers;

import org.junit.Test;

import java.util.TreeSet;

public class FindKthMissingPositive {



    // modified bin search 1st approach
    public int findKthPositive1(int[] arr, int k) {

        int low = 0;
        int high = arr.length;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] - mid - 1 >= k) {
                high = mid;
            } else {
                low = mid+1;
            }
        }
        return low+k;
    }


    // modified bin search 2st approach
    public int findKthPositive2(int[] arr, int k) {

        int low = -1;
        int high = arr.length-1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (arr[mid] - mid - 1 >= k) {
                high = mid-1;
            } else {
                low = mid;
            }
        }
        return low+k+1;
    }

    @Test
    public void test() {

        int[] num = new int[]{2, 3, 4, 7, 11};
        int k = 5;
        System.out.println(findKthPositive1(num, k));

    }



}
