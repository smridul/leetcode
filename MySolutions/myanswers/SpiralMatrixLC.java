package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SpiralMatrixLC {

    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> list = new ArrayList();
        if (matrix.length == 1) {
            for (int i = 0; i < matrix[0].length; i++) {
                list.add(matrix[0][i]);
            }
            return list;
        }
        if (matrix[0].length == 1) {
            for (int i = 0; i < matrix.length; i++) {
                list.add(matrix[i][0]);
            }
            return list;
        }

        int H = matrix[0].length - 2;
        int V = matrix.length - 2;
        int i = 0;
        int j = 0;


        while (H >= 0 && V >= 0) {


            // handle special case where we are at center for odd matrix

           if(i== matrix.length/2 && j==matrix[0].length/2 && matrix.length%2==1&& i==j){
               list.add(matrix[i][j]);
           }


            for (; j <= H; j++) {
                list.add(matrix[i][j]);
            }
            for (; i <= V; i++) {
                list.add(matrix[i][j]);
            }
            for (; j >= matrix[0].length - (H+1); j--) {
                list.add(matrix[i][j]);
            }
            for (; i >= matrix.length - (V+1); i--) {
                list.add(matrix[i][j]);
            }

            H = H - 1;
            V = V - 1;
            j++; i++;
        }

        return list;
    }

    @Test
    public void test() {

//        int[][] matrix = new int[][]{
//                {1, 2, 3},
//                {4, 5, 6},
//                {7, 8, 9},
//        };
        int[][] matrix = new int[][]{
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };

//        int[][] matrix = new int[][]{
//                {1, 2},
//                {5, 6}
//        };

//        int[][] matrix = new int[][]{
//                {1, 2, 3},
//                {4, 5, 6},
//                {7, 8, 9},
//                {10, 11, 12}
//        };

//        int[][] matrix = new int[][]{
//                {1},
//                {4}
//        };
        List<Integer> list = spiralOrder(matrix);

        for (Integer i: list){
            System.out.print(i + " ");
        }
    }

}
