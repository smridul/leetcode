package myanswers;

import org.junit.Test;
import org.junit.experimental.max.MaxHistory;


/**
 * Created by smridul on 1/17/19.
 */
public class MaxRectangle {


    @Test
    public void test() {


        char[][] matrix = new char[][]{
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}
              };
        maximalRectangle(matrix);
    }

    public void maximalRectangle(char[][] matrix) {

        int m = matrix.length;
        int n = matrix[0].length;

        CellRectangle maxRectangle[][] = new CellRectangle[m + 1][n + 1];

        //fill last low last column
        for (int col = 0; col <= n; col++) {
            maxRectangle[m][col] = new CellRectangle(0, 0);

        }
        for (int row = 0; row <= m; row++) {
            maxRectangle[row][n] = new CellRectangle(0, 0);
        }


        for (int row = m - 1; row >= 0; row--) {

            for (int col = n - 1; col >= 0; col--) {

                if(matrix[row][col] == '0'){
                    maxRectangle[row][col] = new CellRectangle(0, 0);
                    continue;
                }

                // down cell row+1, col
                CellRectangle downCell = maxRectangle[row + 1][col];

                // right cell row, cell+1
                CellRectangle rightCell = maxRectangle[row][col + 1];
                CellRectangle diagCell = maxRectangle[row+1][col + 1];


                char right = (col == n - 1) ? '0' : matrix[row][col + 1];
                char down = (row == m - 1) ? '0' : matrix[row + 1][col];
                char diagonal = (row == m - 1 || col == n - 1) ? '0' : matrix[row + 1][col + 1];

                if(right=='1' && down=='1' && diagonal=='1'){
                    int width = Math.min(rightCell.width, Math.min(downCell.width, diagCell.width)) + 1;
                    int height = Math.min(rightCell.height, Math.min(downCell.height, diagCell.height)) + 1;
                    maxRectangle[row][col] = new CellRectangle(width, height);
                } else if(right=='1' && down=='0'){
                    int width = rightCell.width +  1;
                    maxRectangle[row][col] = new CellRectangle(width, 1);
                } else if(right=='0' && down=='1'){
                    int height = downCell.height +  1;
                    maxRectangle[row][col] = new CellRectangle(1, height);
                } else if(right == '1' && down == '1'){
                   if(rightCell.width > downCell.height){
                       maxRectangle[row][col] = new CellRectangle(rightCell.width+1, 1);
                   }else{
                       maxRectangle[row][col] = new CellRectangle(1, downCell.height+1);
                   }
                } else{
                    maxRectangle[row][col] = new CellRectangle(1, 1);

                }
            }
        }

        int max = 0 ;

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                max = Math.max(maxRectangle[row][col].height *maxRectangle[row][col].width, max );

            }
        }


        System.out.print("Max is "+ max);
    }
}

class CellRectangle {
    int width;
    int height;

    CellRectangle(int width, int height) {
        this.height = height;
        this.width = width;
    }
}
