package myanswers;

import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MaxCoins {
    public int maxCoins(int[] nums) {

        int n = nums.length;
        int dp[][] = new int[nums.length+2][nums.length+2];

        // we need the answer dp[1][n];


        // now all 1 length chain. coins is same as arr[i]

        //dp[0][0] = 1;

        //dp[nums.length+1][nums.length+1] = 1;




        for(int len=1; len <=n; len++){


            for(int i=0; i <= n-len; i++){

                int j = i + len-1;
                //[i, j] is window we are evaluting

                //dp[i+1][j+1]


                int maxCoins = -1;
                for(int k = i; k <= j; k++){
                    int prev = i==0 ? 1 : nums[i-1];
                    int next = j==n-1 ? 1: nums[j+1];
                    // if k selected as last
                    // dp[i][k-1]
                    // dp[k+1][j]

                    int leftdp = dp[i+1][k] ;
                    if (i+1>k){
                        leftdp = 0;
                    }
                    int rightdp = dp[k+2][j+1];
                    if(k+2>j+1){
                        rightdp =0;
                    }
                    maxCoins = Math.max(maxCoins, nums[k] * prev * next +

                            leftdp + rightdp);
                }

                dp[i+1][j+1] = maxCoins;
            }
        }


        return dp[1][n];
    }

    @Test
    public void test(){
        System.out.println(maxCoins(new int[]{3, 1, 5, 8}));

    }




    public int maxCoins2(int[] nums) {

        int n = nums.length;
        int dp[][] = new int[nums.length+2][nums.length+2];


        int[] numsArr = new int[n+2];

        for(int i=0; i< nums.length; i++){
            numsArr[i+1] = nums[i];
        }
        numsArr[0] = 1;
        numsArr[n+1] = 1;


        for(int len=1; len <=n; len++){

            for(int i=1; i <= n+1-len; i++){

                int j = i + len-1;
                //[i, j] is window we are evaluting

                //dp[i][j]


                int maxCoins = -1;
                for(int k = i; k <= j; k++){
                    int prev = numsArr[i-1];
                    int next = numsArr[j+1];


                    int leftdp = dp[i][k-1] ;
                    int rightdp = dp[k+1][j];

                    maxCoins = Math.max(maxCoins, numsArr[k] * prev * next +

                            leftdp + rightdp);
                }

                dp[i][j] = maxCoins;
            }
        }


        return dp[1][n];
    }
}
