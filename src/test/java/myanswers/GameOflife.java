package myanswers;

import java.util.HashSet;
import java.util.Set;

public class GameOflife {
    int[][] offset = new int[][]{
            {-1, -1}, {-1, 0}, {-1, 1}, {0, 1},
            {1, 1}, {1, 0}, {1, -1}, {0, -1}
    };

    public void gameOfLife(int[][] board) {

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {

                int totalLive = 0;
                for (int[] dir : offset) {
                    int newr = i + dir[0];
                    int newc = j + dir[1];
                    if (newr >= 0 && newr < board.length && newc >= 0 && newc < board[0].length
                            && isLive(newr, newc, board)) {
                        totalLive++;
                    }
                }
                if (isDead(i, j, board) && totalLive == 3) {
                    //rebirth
                    board[i][j] = 3;
                } else if (isLive(i, j, board) && (totalLive < 2 || totalLive > 3)) {
                    //die
                    board[i][j] = 2;
                }
            }
        }


        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == 2) {
                    board[i][j] = 0;
                }
                if (board[i][j] == 3) {
                    board[i][j] = 1;
                }
            }
        }
    }


    private boolean isDead(int r, int c, int[][] board) {
        return board[r][c] == 0 || board[r][c] == 3;
    }

    private boolean isLive(int r, int c, int[][] board) {
        return board[r][c] == 1 || board[r][c] == 2;
    }
}
