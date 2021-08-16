package myanswers;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by smridul on 6/20/18.
 */
public class SpiralMatrix {
    @Test
    public void test() {
        int n = 5;
//        int[][] matrix = new int[][]{
//                {1,  2,   3,    4},
//                {5,  6,   7,    8},
//                {9,  10,  11,   12},
//                {13, 14,  15,  16},
//        };

        int[][] matrix = new int[][]{
                {1,  2,   3,    4, 5},
                {5,  6,   7,    8, 6},
                {9,  10,  11,   12, 7},
                {13, 14,  15,  16, 8},
                {90, 91,  91,  93, 94},
        };
//        int[][] matrix = new int[][]{
//                {1}
//        };


        ArrayList<Integer> spiral = new ArrayList<>();


        int row = 0;
        int column = 0;

        // first row
        for (column = 0; column < n; column++) {
            spiral.add(matrix[0][column]);
        }

        n--;
        row++;
        column--;

        boolean up = true;

        while (n > 0) {
            int elements = n;
            if (up) {
                for (int i = 1; i <= elements; i++) {
                    spiral.add(matrix[row++][column]);
                }
                column--;

                // because of loop
                row--;

                for (int i = 1; i <= elements; i++) {
                    spiral.add(matrix[row][column--]);
                }
                row--;

                // because of loop
                column++;
            } else {

                for (int i = 1; i <= elements; i++) {
                    spiral.add(matrix[row--][column]);
                }
                column++;

                // because of loop
                row++;

                for (int i = 1; i <= elements; i++) {
                    spiral.add(matrix[row][column++]);
                }
                row++;
                // because of loop
                column--;
            }

            n--;
            up = !up;
        }



        for(Integer i: spiral){
            System.out.print(i+" ");
        }
    }
}
