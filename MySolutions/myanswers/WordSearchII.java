package myanswers;

import org.junit.Test;

import java.util.*;

public class WordSearchII {

    int[][] offsets = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public List<String> findWords(char[][] board, String[] words) {

        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                List<String> matches = new ArrayList<>();
                if(trie.root.containsKey(board[i][j])) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(board[i][j]);
                    dfs(board, i, j, sb, trie.root.get(board[i][j]), matches);
                }
                for (String s : matches)
                    result.add(s);
            }
        }

        return result;
    }


    void dfs(char[][] board, int row, int col, StringBuilder current, TrieNode trieNode, List<String> result) {

        if (trieNode.isEnd) {
            result.add(current.toString());
            trieNode.isEnd = false;
        }


        char temp = board[row][col];
        board[row][col] = '#';

        for (int[] dir : offsets) {

            int newRow = row + dir[0];
            int newCol = col + dir[1];

            if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length &&

                    board[newRow][newCol] != '#') {

                char ch =  board[newRow][newCol];
                if (trieNode.containsKey(ch)) {
                    current.append(ch);
                    dfs(board, newRow, newCol, current, trieNode.get(ch), result);
                    current.deleteCharAt(current.length()-1);
                }
            }
        }
        board[row][col] = temp;
    }


    @Test
    public void test() {

        char[][] grid = new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };

        String[] words = new String[]{"oath","pea","eat","rain"};

        List<String> matches = findWords(grid, words);
        int a =0;
        for (String l : matches) {
            System.out.println(l);
        }

    }

}
