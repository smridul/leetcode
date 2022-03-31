package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 6/11/18.
 */
public class MedianSortedArray {

    @Test
    public void test() {

        int arr1[] = new int[]{1, 4, 7, 8, 9, 10, 15, 20};
        int arr2[] = new int[]{11, 12};

        int partitionIndex = getPartitionIndex(90, arr1, 0, 7);

        int index = findMedian(arr1, arr2, 0, arr1.length, 0, arr2.length);
        System.out.println(index);
    }

    public int findMedian(int arr1[], int arr2[], int start1, int end1, int start2, int end2) {

        return 0;
    }


    public int getPartitionIndex(int element, int[] array, int start, int end) {

        int partitionIndex = -1;
        int right = end;
        int left = start;
        while (left <= right) {

            int mid = (left + right) / 2;
            if (element == array[mid]) {
                partitionIndex = mid;
                break;
            } else if (element > array[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (right < 0) {
            // special case handling because round off of -0.5 is 0 not -1
            return -1;
        }
        //Not found cases
        if (partitionIndex == -1) {
            partitionIndex = (left + right) / 2;
        }
        if (partitionIndex > end) {
            partitionIndex = end + 1;
        }
        if (partitionIndex < start) {
            partitionIndex = start - 1;
        }

        // output is that start to partitionindex all element are less than or equal to element
        return partitionIndex;
    }
}
