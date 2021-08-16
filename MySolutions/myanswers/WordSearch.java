package myanswers;

import org.junit.Test;

public class WordSearch {
    public boolean exist(char[][] board, String word) {

        if (word == null || word.length() == 0) {
            return false;
        }
        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dfs(i, j, board, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    boolean dfs(int i, int j, char[][] board, String search, int searchIndex) {

        if (searchIndex == search.length()) {
            return true;
        }
        if (i >= board.length || j >= board[0].length || i < 0 || j < 0) {
            return false;
        }

        if (board[i][j] == search.charAt(searchIndex)) {

            char oldChar = board[i][j];
            board[i][j] = '\0';

            if (dfs(i + 1, j, board, search, searchIndex + 1) ||
                    dfs(i - 1, j, board, search, searchIndex + 1) ||
                    dfs(i, j + 1, board, search, searchIndex + 1) ||
                    dfs(i, j - 1, board, search, searchIndex + 1)){
                return true;
            }

            board[i][j]=oldChar;
        }
        return false;
    }

    @Test
    public void test() {

        char [][] grid = new char[][]{
                {'A','B','C','E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        System.out.println(exist(grid, "ABE"));
    }
}
