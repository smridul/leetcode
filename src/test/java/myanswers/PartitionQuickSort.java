package myanswers;

import org.junit.Test;

import java.util.Collections;
import java.util.Random;

public class PartitionQuickSort {

    @Test
    public void test(){

        int [] arr = new int[]{6, 4, 4, 3, 2, 1};
        print(arr);
        int partionIndex = 1;
        System.out.println("partition element is " + arr[partionIndex]);
        System.out.println(partion(0, arr.length-1, partionIndex, arr));
        print(arr);
    }

    @Test
    public void test2(){

        int [] arr = new int[]{3,2,3,1,2,4,5,5,6};
        System.out.println(findKthLargest(arr, 4));
    }

    public int findKthLargest(int[] nums, int k) {
        k = nums.length-k;
        Random random = new Random();

        int low = 0;
        int high = nums.length-1;

        while (low <= high){
            int pIndex = random.nextInt(high-low+1) + low;
            int pos = partion(low, high, pIndex, nums);
            if(pos < k){
                low = pos + 1;
            }else if(pos == k){
                return nums[k];
            }else {
                high = pos-1;
            }
        }
        return -1;
    }

    int partion(int low, int high, int index, int[] num){
        swap(num, high, index);

        int i=low-1;
        int j=low-1;

        while (j+1  <high){
            // if(num[j+1] > num[high]) this is fine too. Why?? see notes
            if(num[j+1] >= num[high]){
                j++;
            }else{
                swap(num, i+1, j+1);
                i++; j++;
            }
        }
        swap(num, i+1, high);
        return i+1;
    }


    public void swap(int [] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    void print(int [] arr){
        System.out.print("[ ");
        for (int a: arr){
            System.out.print(a + " ");
        }
        System.out.print("]");
    }
}
