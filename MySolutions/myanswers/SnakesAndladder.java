package myanswers;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class SnakesAndladder {

    public int snakesAndLadders(int[][] board) {

        Queue<Integer> queue = new LinkedList<>();

        int N = board.length;
        boolean visited[] = new boolean[N * N + 1];

        queue.add(1);
        visited[1] = true;
        int move = 0;
        while (!queue.isEmpty()) {

            int levelSize = queue.size();

            while (levelSize-- > 0) {

                int node = queue.poll();
                if (node == N * N) {
                    return move;
                }
                for (int i = 1; i <= 6; i++) {
                    int child = Math.min(node + i, N * N);
                    int childValue = getNodeValue(child, board);

                    int toAdd = child;
                    if (childValue > 0) {
                        toAdd = childValue;
                    }

                    if (!visited[toAdd]) {
                        visited[toAdd] = true;
                        queue.add(toAdd);
                    }
                }
            }
            move++;
        }

        return -1;
    }


    int getNodeValue(int node, int board[][]) {
        int N = board.length;

        int r = (node -1)/ N + 1;
        int c = (node % N) == 0 ? N : (node % N);

        if (r % 2 == 0) {
            c = N - c + 1; // if row even, then flip col order
        }
        r = N - r + 1; // from top

        // index from 0
        r = r - 1;
        c = c - 1;
        return board[r][c];
    }


    @Test
    public void test() {

        int[][] matrix = new int[][]{
                {12, 21, 23, 26},//16 , 15 ,14, 13
                {0, 13, 16, 90},//9, 10, 11, 12,
                {1, 28, 5, 11},// 8, 7, 6, 5
                {6, 7, 2, 8}  //1, 2,3, 4
        };


        System.out.println(getNodeValue(5, matrix));

        int[][] board = new int[][] {
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,35,-1,-1,13,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,15,-1,-1,-1,-1}
        };


        System.out.println(snakesAndLadders(board));




    }
}
