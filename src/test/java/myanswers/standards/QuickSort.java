package myanswers.standards;

import org.junit.Test;

public class QuickSort {

    @Test
    public void sortColors(){
        int nums[] = new int[]{2,0,2,1,1,0};
        sortColors(nums);
        for(int a : nums){
            System.out.print(a + " ");
        }
    }

    void sortColors(int [] nums){
        int current = 0;
        int small = -1;
        int big = nums.length;

        while(current < big){

            if(nums[current] == 2){
                big--;
                //swap big and current
                swap(nums, current, big);
            } else if(nums[current] == 0){
                small++;
                //swap current and small
                //move current too
                swap(nums, small, current);
                current++;
            }else {
                //just move current
                current++;
            }
        }
    }

    void swap(int [] nums, int a, int b){
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
