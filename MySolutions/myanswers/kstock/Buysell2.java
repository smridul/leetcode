package myanswers.kstock;

public class Buysell2 {
    public int maxProfit(int[] nums) {


        // this is like deviding the array into two parts
        //[0, i] [i+1, n-1] i varies from 0 to n-1
        // then call simple max profit on these array


        int max=0;

        for(int i=0; i< nums.length; i++){

            int profit1 = maxProfit(0, i, nums);
            int profit2 = maxProfit(i+1, nums.length-1, nums);
            max = Math.max(max, profit1+profit2);

        }

        return max;

    }


    int maxProfit(int low, int high, int [] nums){


        if(high-low < 1){
            return 0;
        }

        int min= nums[low];
        int max=0;

        for(int i=low; i <= high; i++){

            min = Math.min(min, nums[i]);
            max = Math.max(max, nums[i] -min);

        }

        return max;

    }
}
