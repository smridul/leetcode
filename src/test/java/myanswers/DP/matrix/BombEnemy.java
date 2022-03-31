package myanswers.DP.matrix;

import javafx.util.Pair;
import org.junit.Test;

public class BombEnemy {
    public int maxKilledEnemies(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        // pair(i, j) i gives total enemy in that row
        // j gives total enemy in that col
        Pair<Integer, Integer> dp1[][] = new Pair[m][n];
        Pair<Integer, Integer> dp2[][] = new Pair[m][n];


        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {


                //top cell
                int colEnemy = i == 0 ? 0 : dp1[i - 1][j].getValue();

                // left cell
                int rowEnemy = j == 0 ? 0 : dp1[i][j - 1].getKey();


                if (grid[i][j] == 'E') {
                    rowEnemy++;
                    colEnemy++;
                }

                if (grid[i][j] == 'W') {
                    rowEnemy = 0;
                    colEnemy = 0;
                }

                dp1[i][j] = new Pair<>(rowEnemy, colEnemy);
            }
        }


        int ans=0;
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {


                //top cell
                int colEnemy = i == m - 1 ? 0 : dp2[i + 1][j].getValue();

                // left cell
                int rowEnemy = j == n - 1 ? 0 : dp2[i][j + 1].getKey();

                if (grid[i][j] == 'E') {
                    rowEnemy++;
                    colEnemy++;
                }

                if (grid[i][j] == 'W') {
                    rowEnemy = 0;
                    colEnemy = 0;
                }

                dp2[i][j] = new Pair<>(rowEnemy, colEnemy);

                if(grid[i][j] == '0'){

                    int totalKilled = dp2[i][j].getKey() + dp2[i][j].getValue() +
                            dp1[i][j].getKey() + dp1[i][j].getValue();

                    ans = Math.max(ans, totalKilled);
                }

            }
        }

        return ans;

    }


    @Test
    public void test(){

       char[][] grid = new char[][]{{'W','W','W'},{'0','0','0'},{'E','E','E'}};
       System.out.println(maxKilledEnemies(grid));
       String a = "abcd";
       System.out.println(a.startsWith("bcd", 1));
    }
}
