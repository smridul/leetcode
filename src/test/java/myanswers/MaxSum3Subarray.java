package myanswers;

import org.junit.Test;

import java.util.*;
import javafx.util.Pair;

public class MaxSum3Subarray {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {


        int [] prefix = new int[nums.length];

        for(int i=0; i < nums.length; i++){
            prefix[i] = (i==0 ? 0 : prefix[i-1]) + nums[i];
        }

        Pair<List<Integer>, Integer> dp[][] = new Pair[4][nums.length];

        int max = -1;
        int maxNumber = -1;
        for(int i=0; i < nums.length; i++){
            if(i-k+1 >= 0 && getSubArraySum(i-k+1, prefix, k) > maxNumber){
                max = i-k+1;
                maxNumber = getSubArraySum(i-k+1, prefix, k);
            }
            dp[1][i] = new Pair<>( Arrays.asList(max), maxNumber);
        }

        // dp[m][i]  doesnt mean that subarray has to end on i

        for(int m=2; m <= 3; m++){

            for(int i=0; i < nums.length; i++){

                max = -1;
                for(int j=0; j <= i-k ; j++){


                    List<Integer> list=null;
                    if(dp[m-1][j] !=null){


                        int subsum = getSubArraySum(j+1, prefix, k);
                        int lastMSum = dp[m-1][j].getValue();
                        list =  dp[m-1][j].getKey();
                        if(lastMSum + subsum > max){
                            max = lastMSum + subsum ;
                            List<Integer>list1 = new ArrayList<>(list);
                            list1.add(j+1);
                            dp[m][i] = new Pair<>(list1, lastMSum + subsum );
                        }

                    }

                }
            }


        }

        List<Integer> ans = dp[3][nums.length-1].getKey();
        int [] ansArray = new int[3];
        for(int i=0; i < ans.size(); i++){
            ansArray[i] = ans.get(i);
        }
        return ansArray;


    }

    int getSubArraySum(int i, int[] prefix, int k){

        return  (prefix[i+k-1] - (i==0? 0 :prefix[i-1]));
    }

    @Test
    public void test(){
        int[] nums = new int[] {1,2,1,2,6,7,5,1};
        int k=2;
        //int [] ans  =maxSumOfThreeSubarrays(nums, k);
        int [] ans  = maxSumOfThreeSubarrays2(nums, k);

        for(int i: ans){
           System.out.print(i +" ");
       }

    }





    public int[] maxSumOfThreeSubarrays2(int[] nums, int k) {



        int [] prefix = new int[nums.length];

        for(int i=0; i < nums.length; i++){
            prefix[i] = (i==0 ? 0 : prefix[i-1]) + nums[i];
        }

        Pair<List<Integer>, Integer> dp[][] = new Pair[4][nums.length];

        int max = -1;
        int maxNumber = -1;
        for(int i=k-1; i < nums.length; i++){
            if(i-k+1 >= 0 && getSubArraySum(i-k+1, prefix, k) > maxNumber){
                max = i-k+1;
                maxNumber = getSubArraySum(i-k+1, prefix, k);
            }
            dp[1][i] = new Pair<>( Arrays.asList(max), maxNumber);
        }

        // dp[m][i]  doesnt mean that subarray has to end on i

        for(int m=2; m <= 3; m++){


            max =-1;
            Pair<List<Integer>, Integer> maxpair = null;
            int maxSubSumJ = -1;
            for(int i=0; i < nums.length; i++){




                    if(i>=k && dp[m-1][i-k]!=null) {


                        int subsum = getSubArraySum(i - k + 1, prefix, k);
                        int lastMSum = dp[m - 1][i - k].getValue();


                        if (max == -1 || lastMSum + subsum > max) {
                            max = lastMSum + subsum;
                            maxpair = dp[m - 1][i - k];
                            maxSubSumJ = i - k + 1;
                        }

                        List<Integer> list = maxpair.getKey();
                        List<Integer> list1 = new ArrayList<>(list);
                        list1.add(maxSubSumJ);
                        dp[m][i] = new Pair<>(list1, max);
                    }


            }


        }

        List<Integer> ans = dp[3][nums.length-1].getKey();
        int [] ansArray = new int[3];
        for(int i=0; i < ans.size(); i++){
            ansArray[i] = ans.get(i);
        }
        return ansArray;

    }
}
