package myanswers;

import org.junit.Test;

public class NextPermutation {
    public void nextPermutation(int[] nums) {

        int i= nums.length-1;
        while(i>=0){
            if(i!=0 && nums[i-1] < nums[i]){
                // find j

                int j=i;
                while(j < nums.length && nums[i-1] < nums[j]){
                    j++;
                }
                swap(nums, j-1, i-1);
                reverse(nums, i);
                return;
            }
            i--;
        }
        reverse(nums, 0);
    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    @Test
    public void test(){
        int [] num =new int[]{1, 2};
        nextPermutation(num);

        for(int a: num){
            System.out.print(a + " ");
        }
    }
}
