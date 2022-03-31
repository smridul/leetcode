package myanswers;

import org.junit.Test;

import java.util.Arrays;

public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        int dp[][] = new int[coins.length + 1][amount+1];

        for (int i = 0; i <= coins.length; i++) {
            Arrays.fill(dp[i], -1);
        }

        for (int i = 0; i <= coins.length; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= coins.length; i++) {
            for (int k = 1; k <= amount; k++) {
                int j=0;
                int min = Integer.MAX_VALUE;
                while(k - j* coins[i-1] >=0){
                    if(dp[i-1][ k - j* coins[i-1]] !=-1){

                        int noofcoins = j + dp[i-1][ k - j* coins[i-1]];
                        min = Math.min(min, noofcoins );
                        dp[i][k] = min;
                    }
                    j++;
                }
            }
        }
        return dp[coins.length][amount];
    }


    @Test
    public void test(){
        int [] coins = new int[]{1,2,5};
        System.out.println(coinChange(coins, 11));
    }
}

