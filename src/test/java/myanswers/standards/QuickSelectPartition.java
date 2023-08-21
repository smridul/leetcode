package myanswers.standards;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class QuickSelectPartition {


    @Test
    public void test2(){

        int [] arr = new int[]{3,2,3,1,2,4,5,5,6};
        System.out.println(findKthLargest(arr, 1));
        Arrays.sort(arr);
        for(int a: arr) {
            System.out.print(a + " ");
        }
    }



    public int findKthLargest(int[] nums, int k) {

        Random random = new Random();

        int left=0;
        int right = nums.length-1;

        while(left <= right) {
            //choose partition element
            int randomPivot = random.nextInt(right - left + 1) + left;

            int pivotIndexAfterPartition = partition(nums, left, right, randomPivot);


            if (pivotIndexAfterPartition > nums.length - k) {
                right = pivotIndexAfterPartition - 1;
            } else if (pivotIndexAfterPartition < nums.length - k) {
                left = pivotIndexAfterPartition + 1;
            } else {
                // we found the perfect pivot point
                return nums[pivotIndexAfterPartition];
            }
        }

        return -1;

    }


    public int partition(int[] arr, int left, int right, int pivot) {
        swap(arr, pivot, right);
        int small = left-1;
        int current = left;

        while (current < right) {

            if(arr[current] <= arr[right]){
                small++;
                swap(arr, small, current);
                current++;
            } else {
                current++;
            }
        }

        //swap small+1 and pivot
        swap(arr, small+1, right);
        return small+1;
    }


    void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
