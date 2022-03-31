package myanswers.kstock;

import java.util.Arrays;

public class KBuySellWithSimplicationLogic {
    /*
    here there is a major change which is that we can select some j from [0, i]
    and not i-1

    hence the dp chanegs from dp[0, j-1] + price[i] - price[j]
    and j varies from 0 to i
    THATS A BIG CHANGE
     */


    // basic
    public int maxProfit1(int k, int[] prices) {
        int dp[][] = new int[k + 1][prices.length + 1];

        for (int t = 1; t <= k; t++) {
            for (int i = 0; i < prices.length; i++) {
                // To find dp[t][i] => shifted dp[t][i+1]

                // if not done anything on day i
                //  dp[t][i-1]; ==> shifed is dp[t][i]
                int max = dp[t][i];
                ;
                //if sold dp[k-1][j-1] => shifted dp[k-1][j]
                for (int j = 0; j <= i; j++) {
                    max = Math.max(max, dp[t - 1][j] + prices[i] - prices[j]);
                }
                dp[t][i + 1] = max;
            }
        }
        return dp[k][prices.length];
    }


    //loop saver
    public int maxProfit(int k, int[] prices) {

        int dp[][] = new int[k + 1][prices.length + 1];
        int max = 0;
        for (int t = 1; t <= k; t++) {
            max = Integer.MIN_VALUE;
            for (int i = 0; i < prices.length; i++) {
                int toTake = 0;

                max = Math.max(max, dp[t - 1][i] - prices[i]);
                toTake = prices[i] + max;
                dp[t][i + 1] = Math.max(dp[t][i], toTake);
            }
        }
        return dp[k][prices.length];
    }


    // reverse loop
    public int maxProfit3(int k, int[] prices) {

        int dp[][] = new int[prices.length + 1][k + 1];

        for (int i = 0; i < prices.length; i++) {
            for (int t = 1; t <= k; t++) {

                int max = 0;
                for (int j = 0; j <= i; j++) {
                    max = Math.max(max, dp[j][t - 1] + prices[i] - prices[j]);
                }
                dp[i + 1][t] = Math.max(dp[i][t], max);
            }
        }
        return dp[prices.length][k];
    }


    // reverse loop with inner loop gone
    public int maxProfit4(int k, int[] prices) {

        int dp[][] = new int[prices.length + 1][k + 1];


        int[] max = new int[k + 1];
        Arrays.fill(max, Integer.MIN_VALUE);
        for (int i = 0; i < prices.length; i++) {
            for (int t = 1; t <= k; t++) {
                int toTake = 0;

                max[t] = Math.max(max[t], dp[i][t - 1] - prices[i]);
                toTake = max[t] + prices[i];

                dp[i + 1][t] = Math.max(dp[i][t], toTake);
            }
        }
        return dp[prices.length][k];
    }


    // reverse loop with inner loop gone with space optimzation
    public int maxProfit5(int k, int[] prices) {

        int dp[][] = new int[2][k + 1];
        int[] max = new int[k + 1];
        Arrays.fill(max, Integer.MIN_VALUE);
        for (int i = 0; i < prices.length; i++) {
            for (int t = 1; t <= k; t++) {
                int toTake = 0;
                max[t] = Math.max(max[t], dp[0][t - 1] - prices[i]);
                toTake = max[t] + prices[i];
                dp[1][t] = Math.max(dp[0][t], toTake);
            }
            dp[0] = dp[1];
            dp[1] = new int[k+1];
        }
        return dp[0][k];
    }
}
