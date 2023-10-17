package myanswers;

import org.junit.Test;

public class SubarrayProductLessthanK {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int end =0;
        int start=0;
        int ans =0 ;
        int product =nums[0];
        while (end < nums.length){
            if(product < k ){
                //desirable
                ans+= (end-start+1);
            }else {
                // not desirable
                // shrink start
                while(start <= end) {
                    product /= nums[start];
                    start++;

                    if (product < k) {
                        // we reached desirable
                        ans += (end - start + 1);
                        break;
                    }
                }
            }
            end++;
            product = product * ((end < nums.length) ? nums[end]:1);
        }
        return ans;
    }

    @Test
    public void test(){
        int[] arr = new int[]{10,5,2,6};
        System.out.println(numSubarrayProductLessThanK(arr, 100));
        String s = ""  +123;
        int a=0;
    }

    void print(int [] arr, int i, int j) {
        System.out.println();
        System.out.print("[");
        for (int start = i; start <= j; start++) {
            System.out.print(arr[start]+ ",");
        }
        System.out.print("]");
    }

    void print2(int [] arr, int i, int j){
        for (int end = i; end <= j; end++){
            print(arr, i, end);
        }
    }
}
