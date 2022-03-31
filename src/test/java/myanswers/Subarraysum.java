package myanswers;

import org.junit.Test;

import java.util.*;

public class Subarraysum {

    public int subarraySum(int[] nums, int k) {

        Map<Integer, List<Integer>> map = new HashMap<>();

        int sum=0;

        for(int i=0; i<nums.length; i++){
            sum = sum+nums[i];
            if(!map.containsKey(sum)){
                List<Integer> set = new ArrayList<>();
                set.add(i);
                map.put(sum, set);
            }else{
                List<Integer>  set = map.get(sum);
                set.add(i);
            }
        }

        sum = 0;
        int count = 0;
        for(int i =0 ; i<nums.length; i++){
            sum +=nums[i];
            int toFind = sum + k - nums[i];

            if(map.containsKey(toFind)){
                List<Integer> set = map.get(toFind);
                if(k>=0){
                    for(int aa : set){
                        if(aa >=i){
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }




    public int subarraySum1(int[] nums, int k) {

        Map<Integer, List<Integer>> map = new HashMap<>();
        int sum=0;

        //initial update
        List<Integer> set1 = new ArrayList<>();
        set1.add(0);
        map.put(0, set1);


        for(int i=0; i<nums.length; i++){
            sum = sum+nums[i];
            if(!map.containsKey(sum)){
                List<Integer> set = new ArrayList<>();
                set.add(i+1);
                map.put(sum, set);
            }else {
                List<Integer>  set = map.get(sum);
                set.add(i+1);
            }
        }

        sum = 0;
        int count = 0;
        for(int i =0 ; i<nums.length; i++){
            sum +=nums[i];
            int toFind = sum - k ;

            if(map.containsKey(toFind)){
                List<Integer> set = map.get(toFind);

                    for(int aa : set){
                        if(aa <=i){
                            count++;
                        }
                    }

            }
        }
        return count;
    }


    @Test
    public void test(){
        int[] arr = new int[]{-1, -1, 1};
        int k=0;
        System.out.println(subarraySum1(arr, k));
    }
}
