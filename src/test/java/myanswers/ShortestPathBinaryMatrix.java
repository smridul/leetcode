package myanswers;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestPathBinaryMatrix {

    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0},
            {1, -1}, {1, 1}, {-1, -1}, {-1, 1}};

    public int shortestPathBinaryMatrix(int[][] grid) {

        if(grid[0][0]!=0){
            return -1;
        }

        if(grid.length ==1 && grid[0].length==1){
            return 1;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0});
        int level=0;

        while (!queue.isEmpty()){
            int size = queue.size();
            level++;
            for (int k = 0; k < size; k++) {
                int[] cell = queue.poll();
                for (int[] dir : dirs) {
                    int row = cell[0] + dir[0];
                    int col = cell[1] + dir[1];
                    if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == 0) {
                        grid[row][col] = 1;
                        if(row == grid.length-1 && col == grid[0].length-1){
                            return level+1;
                        }
                        queue.offer(new int[]{row, col});
                    }
                }
            }
        }
        return -1;
    }

    @Test
    public void test() {
        int [][] grid = new int[][]{
                {0, 1},
                {1, 0}
        };
        System.out.println(shortestPathBinaryMatrix(grid));
    }


}
