package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by smridul on 1/21/19.
 */
public class Permutation {

    @Test
    public void test(){
        int [] array = new int[]{2, 2, 3, 0};
      //  int [] array = new int[]{2, 2, 1};

        //  int [] array = new int[]{1, 2, 3, 4};


        List<List<Integer>> result = new ArrayList<>();

        permuteWithDup(array, new ArrayList<>(),result, 0 );
        for(List<Integer> perm: result){
            for (Integer i: perm) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
        System.out.println(result.size());

    }


    public List<Integer> toList(int[] ints){
        List<Integer> intList = new ArrayList<Integer>();
        for (int i : ints)
        {
            intList.add(i);
        }
        return intList;
    }

    public void permuteWithDup(int[] array, List<Integer> permutation, List<List<Integer>> result, int start){

        if(start == array.length){
            result.add(toList(array));
            return;
        }

        for(int i=start; i< array.length; i++){

            if(start!=i && array[start] == array[i]){
                // skip the swapping and continue ahead
                continue;
            }

            //swap
            int temp = array[start];
            array[start] = array[i];
            array[i] = temp;

            // add
          //  permutation.add(array[start]);

            // recurse
            permuteWithDup(array, permutation, result, start+1);

            // upswap
            temp = array[start];
            array[start] = array[i];
            array[i] = temp;

            // remove added
          //  permutation.remove(permutation.size()-1);
        }
    }




    @Test
    public void test1() {
        int[] array = new int[]{1, 2, 3, 4};
        List<List<Integer>> result = permute(array);

        for(List<Integer> list: result){
            for(int i: list){
                System.out.print(i+" ");
            }
            System.out.println();
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        List<Integer> numList = new ArrayList<Integer>();
        for (int i : nums)
        {
            numList.add(i);
        }
        permuteHelper(numList, 0, result);
        return result;
    }
    public void permuteHelper( List<Integer>  nums, int index,  List<List<Integer>> result) {
        if(index == nums.size()-1){
            //last element
            List<Integer> perm = new ArrayList<>(nums);
            result.add(perm);
            return;
        }

        for(int i=index; i<nums.size(); i++){
            Collections.swap(nums, i, index);
            permuteHelper(nums, index+1, result);
            Collections.swap(nums, i, index);
        }
    }


}
