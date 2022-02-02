package myanswers;

import org.junit.Test;

import java.util.*;

public class MajorityElement {

    public List<Integer> majorityElement(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();

        int k = 3;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i]) + 1);
            } else if (!map.containsKey(nums[i]) && map.size() < k - 1) {
                map.put(nums[i], 1);
            } else if (!map.containsKey(nums[i]) && map.size() == k - 1) {
                decrementMapCount(map);
            }
        }

        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int candidate = entry.getKey();
            int sum=0;
            for(int n: nums){
                if (n == candidate){
                    sum++;
                }
            }

            if(sum > nums.length/k){
                res.add(candidate);
            }
        }

        return res;
    }

    public void decrementMapCount(Map<Integer, Integer> map) {
        Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, Integer> entry = iter.next();
            if (entry.getValue() == 1) {
                iter.remove();
            } else {
                entry.setValue(entry.getValue() - 1);
            }
        }

    }


    @Test
    public void test(){
        int [] arr = new int[]{1, 2, 3};
        System.out.println(majorityElement(arr));



        arr = new int[]{438885258};

        System.out.println(isMajorityElement(arr, 438885259));
    }



    public boolean isMajorityElement(int[] nums, int target) {
        int first = firstOccurenceOrHigher(nums, target);

        if(first ==-1){
            return false;
        }


        int last = firstOccurenceOrHigher(nums, target+1);

        int k = last-first;

        return (k > nums.length /2);
    }



    int firstOccurenceOrHigher(int nums[], int target){
        int low=0;
        int high= nums.length-1;

        while(low < high){
            int mid = low+ (high-low)/2;

            if(target > nums[mid] ){
                low = mid+1;
            }else{
                high = mid;
            }
        }

        return nums[low]>=target? low : low+1;

    }




















    @Test
    public void test2(){

        int [] arr = new int[]{438885258};
        System.out.println(isMajorityElement2(arr, 786460391));
    }


    public boolean isMajorityElement2(int[] nums, int target) {
        int first = firstOccurenceOrLower(nums, target);

        if(first ==-1){
            return false;
        }


        int last = firstOccurenceOrLower(nums, target+1);

        int k = last-first+1;

        return (k > nums.length /2);
    }



    int firstOccurenceOrLower(int nums[], int target){
        int low=-1;
        int high= nums.length-1;

        while(low < high){
            int mid = low+ (high-low)/2 +1;

            if(target > nums[mid] ){
                low = mid;
            }else{
                high = mid-1;
            }
        }

        if(low!=nums.length-1 && nums[low+1] == target){
            return low+1;
        }else{
            return low;
        }

    }
}
