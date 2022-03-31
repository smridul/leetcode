package myanswers;

import org.junit.Test;

public class FindDuplicate {
    public int findDuplicate(int[] nums) {

        int sum = 0;
        int max = -1;
        for (int a : nums) {
            sum += a;
            max = Math.max(max, a);
        }
        int N = nums.length;

        int numberOfRepetations = N - max;
        // actual repeatation will numberOfRepetations+1

        int element = (sum - (N - numberOfRepetations) * (N - numberOfRepetations + 1) / 2) / numberOfRepetations;

        return element;
    }

    @Test
    public void test() {
        int[] arr = new int[]{2,2};
        System.out.println(findDuplicate2(arr));
        int a =0;
    }

    public int findDuplicate2(int[] nums) {
        for(int i=0; i<nums.length; i++){
            int start = i;
            if(nums[i]-1 != i){
                int elementToPlace = nums[start];
                do{
                    if(elementToPlace == nums[elementToPlace-1] ){
                        return elementToPlace;
                    }
                    int pos = elementToPlace-1;
                    int temp = nums[pos];
                    nums[pos] = elementToPlace;
                    start = pos;
                    elementToPlace =temp;
                } while (start!=i);
            }
        }
        return -1;
    }
}
