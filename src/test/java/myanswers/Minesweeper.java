package myanswers;

import org.junit.Test;

public class Minesweeper {
    int[][] offset = new int[][]{
            {-1, -1},
            {-1, 0},
            {-1, 1},
            {0, 1},
            {1, 1},
            {1, 0},
            {1, -1},
            {0, -1}
    };

    public char[][] updateBoard(char[][] board, int[] click) {
        int r = click[0];
        int c = click[1];
        if (board[r][c] == 'M') {
            board[r][c] = 'X';
            return board;
        }
        dfs(r, c, board);
        return board;
    }

    public void dfs(int r, int c, char[][] board) {
        if (board[r][c] == 'M') {
            return;
        }
        int mines = totalMines(r, c, board);
        if (mines > 0) {
            board[r][c] = (char) ('0' + (mines));
        } else {
            board[r][c] = 'B';
            for (int[] dir : offset) {
                int newr = r + dir[0];
                int newc = c + dir[1];

                if (newr >= 0 && newr < board.length && newc >= 0 && newc < board[0].length &&
                        board[newr][newc] == 'E') {
                    dfs(newr, newc, board);
                }
            }
        }

    }

    public int totalMines(int r, int c, char[][] board) {
        int totalMine = 0;
        for (int[] dir : offset) {
            int newr = r + dir[0];
            int newc = c + dir[1];
            if (newr >= 0 && newr < board.length && newc >= 0 && newc < board[0].length &&
                    board[newr][newc] == 'M') {
                totalMine++;
            }
        }
        return totalMine;
    }


    @Test
    public void test() {
        char[][] board = new char[][]{
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'M', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'},
                {'E', 'E', 'E', 'E', 'E'}
        };

        int[] click = new int[]{3, 0};
        board = updateBoard(board, click);

        for(int i=0; i<board.length; i++){
            for(int j=0; j< board[0].length; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
