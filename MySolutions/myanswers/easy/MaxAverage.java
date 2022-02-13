package myanswers.easy;

import javafx.util.Pair;
import org.junit.Test;

public class MaxAverage {
    public double findMaxAverage(int[] nums, int k) {


        int sum=0;
        double max= -Double.MAX_VALUE;
        for(int i=0; i < nums.length; i++){

            sum += nums[i];
            if(i > k-1){
                sum = sum - nums[i-k];
            }

            if(i>=k-1){
                double d = (double)(sum)/k;
                max = Math.max (max, (double)(sum)/k);
            }

        }

        return max;
    }

    @Test
    public void test(){
        int [] arr = new int[]{-1};
        System.out.println(findMaxAverage(arr, 1));



    }
}
