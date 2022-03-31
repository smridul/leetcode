package myanswers;

import org.junit.Test;

public class DiagonalTraversal {

    public int[] findDiagonalOrder(int[][] mat) {

        int m = mat.length;
        int n = mat[0].length;

        int[] result = new int[m * n];
        int index = 0;
        int lastLineCount = 0;
        boolean flipped = false;

        for (int row = 0; row <= m - 1; row++) {

            int i = row;
            int j = 0;
            lastLineCount = 0;


            while (i >= 0) {
                result[index] = mat[i][j];
                lastLineCount++;
                i--;
                j++;
                if (flipped) {
                    index--;
                } else {
                    index++;
                }
            }
            flipped = !flipped;

            if (flipped) {
                if (row == m - 1) {
                    index = index + lastLineCount - 2;
                } else {
                    index = index + lastLineCount;
                }
            }else{
                index = index+ lastLineCount + 1;
            }
        }


        for(int col = 1; col <=n-1; col++){
            int i= m-1;
            int j=col;
            lastLineCount = 0;

            while (j <= m-1){
                result[index] = mat[i][j];
                j++;
                i--;
                lastLineCount++;
                if(flipped){
                    index--;
                }else{
                    index++;
                }
                flipped = !flipped;

                if(flipped){
                    index = index + lastLineCount-2;
                }
            }
        }


        return result;
    }

    @Test
    public void test() {
        int[][] arr = new int[][]{
                {1,2,3},
                {4, 5, 6},
                {7, 8,9}
        };
        int[] n = findDiagonalOrder(arr);
        for(int i : n) {
            System.out.println(i);
        }
    }


}
