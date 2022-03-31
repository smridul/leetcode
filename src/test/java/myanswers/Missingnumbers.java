package myanswers;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by smridul on 7/22/18.
 */
public class Missingnumbers {

    @Test
    public void findmissinglist() {

        List<Integer> list = Arrays.asList(3,4,-1,1);



        for (int i = 0; i < list.size(); i++) {

            while(list.get(i) > 0 && list.get(i) <=list.size() && (list.get(list.get(i)-1) != list.get(i))){

                Collections.swap(list, i, list.get(i)-1);
            }
        }

        int a=0;

        for(int i =0; i < list.size(); i++){
            if(list.get(i) != i+1){

                System.out.print(i+1);
                break;
            }

        }
    }


    @Test
    public void findmissingArray() {
        int [] nums = new int[]{1, 2, 3, 4};
        System.out.print(firstMissingPositive(nums));
    }


    public int firstMissingPositive(int[] nums) {

        for (int i = 0; i < nums.length; i++) {
            while(nums[i] > 0 && nums[i] <= nums.length && (nums[nums[i]-1] != nums[i])){

                swap(nums, i, nums[i]-1);
            }
        }


        int i =0;
        for(i =0; i < nums.length; i++){
            if(nums[i] != i+1){
                return (i+1);
            }
        }

        return i+1;
    }

    public void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

}
