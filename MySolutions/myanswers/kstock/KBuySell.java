package myanswers.kstock;

import org.junit.Test;

import java.util.Arrays;

public class KBuySell {

    public int maxProfit(int k, int[] prices) {

        int dp[][] = new int[k + 1][prices.length + 1];
        int max = 0;
        for (int t = 1; t <= k; t++) {
             max = Integer.MIN_VALUE;
            for (int i = 0; i < prices.length; i++) {
                int toTake = 0;
                if (i > 0) {
                    max = Math.max(max, dp[t - 1][i - 1] - prices[i - 1]);
                    toTake = prices[i] + max;
                }

                dp[t][i + 1] = Math.max(dp[t][i], toTake);
            }
        }
        return dp[k][prices.length];
    }


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
                for (int j = 0; j < i; j++) {
                    max = Math.max(max, dp[t - 1][j] + prices[i] - prices[j]);
                }
                dp[t][i + 1] = max;
            }
        }
        return dp[k][prices.length];
    }


    //space optimization
    public int maxProfit2(int k, int[] prices) {
        int dp[][] = new int[2][prices.length + 1];
        int max = 0;
        for (int t = 1; t <= k; t++) {
            max = Integer.MIN_VALUE;
            for (int i = 0; i < prices.length; i++) {
                int toTake = 0;
                if (i > 0) {
                    max = Math.max(max, dp[0][i - 1] - prices[i - 1]);
                    toTake = prices[i] + max;
                }

                dp[1][i + 1] = Math.max(dp[1][i], toTake);
            }
            // make dp[1] as previous dp[0]
            // now we calculate dp[1]
            dp[0] = dp[1];
            dp[1] = new int[prices.length + 1];

        }
        return dp[0][prices.length];
    }


    @Test
    public void test() {
        int arr[] = new int[]{2, 4, 1};
        System.out.println(maxProfit2(2, arr));
    }


    // reverse loop
    public int maxProfit3(int k, int[] prices) {

        int dp[][] = new int[prices.length + 1][k + 1];

        for (int i = 0; i < prices.length; i++) {
            for (int t = 1; t <= k; t++) {

                int max = 0;
                for (int j = 0; j < i; j++) {
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



        int[] max = new int[k+1];
        Arrays.fill(max, Integer.MIN_VALUE);
        for (int i = 0; i < prices.length; i++) {
            for (int t = 1; t <= k; t++) {
                int toTake = 0;
                if(i > 0){
                    max[t] = Math.max(max[t], dp[i-1][t-1] - prices[i-1]);
                    toTake = max[t] + prices[i];
                }
                dp[i + 1][t] = Math.max(dp[i][t], toTake);
            }
        }
        return dp[prices.length][k];
    }


    // reverse loop with inner loop gone with space optimzation
    public int maxProfit5(int k, int[] prices) {

        int dp[][] = new int[prices.length + 1][k + 1];
        int[] max = new int[k+1];
        Arrays.fill(max, Integer.MIN_VALUE);
        for (int i = 0; i < prices.length; i++) {
            for (int t = 1; t <= k; t++) {
                int toTake = 0;
                if(i > 0){
                    max[t] = Math.max(max[t], dp[i-1][t-1] - prices[i-1]);
                    toTake = max[t] + prices[i];
                }
                dp[i + 1][t] = Math.max(dp[i][t], toTake);
            }
        }
        return dp[prices.length][k];
    }
}
