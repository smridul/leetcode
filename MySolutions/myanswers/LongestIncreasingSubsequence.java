package myanswers;

import org.junit.Test;
import javafx.util.Pair;
public class LongestIncreasingSubsequence {
    public int findNumberOfLIS(int[] nums) {

        Pair<Integer, Integer> dp[] = new Pair[nums.length];
        //length, count pair
        int maxLen = 0;
        for(int i=0; i < nums.length; i++){

            int max=0;
            //find j such that a[j] < a[i]

            Pair<Integer, Integer> maxpair = new Pair<>(0, 0);
            for(int j=0; j <i; j++){
                if(nums[j] < nums[i]){

                    Pair<Integer, Integer> pair = dp[j];
                    if(pair.getKey() > maxpair.getKey()){
                        maxpair = pair;
                    }else if(pair.getKey() == maxpair.getKey()){
                        maxpair = new Pair<>(pair.getKey(), pair.getValue()+ maxpair.getValue());
                    }else{

                    }

                }
            }

            dp[i] = new Pair<>(maxpair.getKey()+1, maxpair.getValue()==0 ? 1:
                    maxpair.getValue());
            maxLen = Math.max(maxLen, dp[i].getKey());

        }


        int count=0;
        for(int i=0;  i< dp.length ; i++){
            if(dp[i].getKey()==maxLen){
                count+=dp[i].getValue();
            }
        }

        return count;
    }

    @Test
    public void test(){
        int [] arr = new int[]{1,2,4,3,5,4,7,2};
        System.out.println(findNumberOfLIS(arr));
    }
}
