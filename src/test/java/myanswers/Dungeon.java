package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 2/25/19.
 */
public class Dungeon {

    @Test
    public void test() {

        int grid[][] = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5},
        };

        System.out.println(calculateMinimumHP(grid));
    }


    public int calculateMinimumHP(int[][] dungeon){

        int dp[][] = new int[dungeon.length][dungeon[0].length];
        return calculateMinimumHP(dungeon, dp, 0, 0);
    }


    public int calculateMinimumHP(int[][] dungeon, int dp[][], int row, int col){

        // dp is minimum entry value of knight before entering the cell
        if(row == dungeon.length-1 && col == dungeon[0].length-1){
            dp[row][col] =  dungeon[row][col] < 0 ?  -dungeon[row][col]+1  : 1;
            return dp[row][col];
        }

        if(row == dungeon.length || col == dungeon[0].length){
            return Integer.MAX_VALUE;
        }

        if(dp[row][col]!=0){
            return dp[row][col];
        }

        //right
        int minRequiredWhenGoingThruRight = calculateMinimumHP(dungeon, dp, row, col+1);

        //down
        int minRequiredWhenGoingThruDown = calculateMinimumHP(dungeon, dp, row+1, col);


        // we want tht knightvalue + cell value should be eqaul to entry value for next cell
        // which is stored in dp

        int ans = Math.max(0, Math.min(minRequiredWhenGoingThruRight, minRequiredWhenGoingThruDown) - dungeon[row][col]);

        dp[row][col] = ans;
        return ans;
    }

}
