package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 6/8/18.
 */
public class MaxBlackSqaure {

    @Test
    public void test() {

        char[][] matrix = new char[][]{
                {'B', 'W', 'W', 'B'},
                {'B', 'B', 'B', 'B'},
                {'B', 'B', 'B', 'W'},
                {'B', 'B', 'B', 'B'},
        };

        Subsquare subsquare = getMaxSubSquare(matrix);
        System.out.println("MAX black square is " + subsquare.length);
        System.out.println(" " + subsquare.row + "," + subsquare.column);


    }

    public Subsquare getMaxSubSquare(char[][] matrix) {

        int max = 0;
        Subsquare subsquare = new Subsquare();

        for (int i = 0; i < matrix.length; i++) {

            for (int j = 0; j < matrix[0].length; j++) {

                int maxPossibleSquareLenth = Math.min(matrix.length - i, matrix.length - j);

                for (int length = 1; length <= maxPossibleSquareLenth; length++) {
                    if (matrix[i][j + length - 1] != 'B') {
                        break;
                    }


                    if (squarePossible(i, j, matrix, length)) {

                        if (length > max) {
                            max = length;
                            subsquare.length = max;
                            subsquare.row = i;
                            subsquare.column = j;

                        }
                    }

                }


            }

        }
        return subsquare;

    }


    private boolean squarePossible(int i, int j, char[][] matrix, int length) {


        if (length == 0) {
            return false;
        }

        if (length == 1) {
            return matrix[i][j] == 'B';
        }

        for (int row = i; row <= i + length - 1; row++) {

            for (int column = j; column <= j + length - 1; column++) {


                if (row == i || row == i + length - 1) {
                    // check each column
                    if (!(matrix[row][column] == 'B')) {
                        return false;
                    }
                } else {
                    // check only first and last column for other rows
                    if (matrix[row][j] == 'B' && matrix[row][j + length - 1] == 'B') {
                        break;
                    } else {
                        return false;
                    }

                }

            }

        }
        return true;

    }
}

class Subsquare {

    int row;
    int column;
    int length;
    // assumption  we follow this direction
    //cell ----->
    //|         |
    //|         |
    //|         |
    // <---------
}
