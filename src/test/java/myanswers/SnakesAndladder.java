package myanswers;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

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

        int[] cellTorc = cellToRowCol(5, 4);
        System.out.println(" " +cellTorc[0] +" "+  cellTorc[1]);

        int[][] board = new int[][] {
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,35,-1,-1,13,-1},
                {-1,-1,-1,-1,-1,-1},
                {-1,15,-1,-1,-1,-1}
        };


        System.out.println(snakesAndLadders(board));

        System.out.println(snakesAndLadders2(board));

    }


    public int snakesAndLadders2(int[][] board) {

        int n = board.length;
        int N = n*n;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        Set<Integer> set = new HashSet<>();
        set.add(1);
        int level = 0;
        while(!queue.isEmpty()){
            int levelSize = queue.size();
            level++;
            while(levelSize-- > 0){
                int cell = queue.poll();
                for(int i=1; cell+i <=N && i<=6; i++ ){
                    int newCell = cell+i;

                    // get the corresponding final value of new cell
                    // ie if this it is not -1 then we need to get the resultant cell
                    // toget that resultant cell we first need to find the row and col of the
                    // newCell, so we need something to convert cell to its row,col representation

                    int [] rowcol = cellToRowCol(newCell, n);
                    int processedCell = board[rowcol[0]][rowcol[1]] == -1 ? newCell : board[rowcol[0]][rowcol[1]];
                    if(processedCell == N){
                        return level;
                    }
                    if(!set.contains(processedCell)){
                        set.add(processedCell);
                        queue.add(processedCell);
                    }
                }
            }

        }

        return -1;

    }


    int[] cellToRowCol(int cell, int n){

        int actualrow = (cell-1)/n;

        // this is in reverse order so flip it
        int matrixrow = n-1-actualrow;

        // 1st row => 0th index row is from left to right
        // 2nd row ==> 1st index row is from right to left
        // even is left to right
        // odd is right to left
        int col=0;
        if(actualrow %2 == 0){
            // normal order
            col = (cell-1)%n ;

        }else{
            //reverse order
            col = (cell-1)%n ;
            col = n-1-col;
        }
        return new int[]{matrixrow, col};
    }
}
