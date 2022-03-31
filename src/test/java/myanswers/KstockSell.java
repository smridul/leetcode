package myanswers;


import org.junit.Test;

public class KstockSell {

    @Test
    public void KStockSell() {

        int stocks[] = new int[]{3, 2, 6, 5, 0, 3};

        int[][] dp = new int[][]{};


    }

    public void kstock(int[] stocks, int[][] dp, int k) {


        for (int i = 0; i < stocks.length; i++) {
            for(int j=1; j<=k; j++){

                int noTransactionDone = dp[j][ i-1];

            }
        }


        for(int j=1; j<=k; j++){

            for (int i = 0; i < stocks.length; i++) {


                int noTransactionDone = dp[j][i-1];

                


            }
        }

    }
}
