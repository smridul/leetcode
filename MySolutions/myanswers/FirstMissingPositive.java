package myanswers;

import org.junit.Test;

public class FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {



        for(int i=0; i < nums.length; i++){

            while(nums[i] > 0 && nums[i] <=nums.length){

                // already at correct pos
                if(nums[i]==i+1){
                    break;
                }

                // place this num[i] on nums[i]-1 posiition
                // swap between i and nums[i]-1
                // pos1 = i, pos2 = nums[i]-1
                // swap
                int pos1 = i;
                int pos2 = nums[i]-1;

                int temp = nums[pos1];
                nums[pos1] = nums[pos2];
                nums[pos2] = temp;
            }
        }
        int i=0;
        for( i=0; i < nums.length; i++){
            if(nums[i]!=i+1){
                return i+1;
            }
        }

        return i+1;

    }

    @Test
    public void test(){
        int arr[] = new int[]{3,4,-1,1};
        System.out.println(firstMissingPositive(arr));
    }
}
