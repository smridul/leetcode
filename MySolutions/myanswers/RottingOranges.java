package myanswers;

import org.junit.Test;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class RottingOranges {
    int[][] dir = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int orangesRotting(int[][] grid) {

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int i = 0; i < size; i++) {
                int[] cell = queue.poll();

                for (int[] move : dir) {
                    int newRow = cell[0] + move[0];
                    int newCol = cell[1] + move[1];
                    if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length
                            && grid[newRow][newCol] == 1) {
                        grid[newRow][newCol] = 3; // revert back to 1
                        queue.add(new int[]{newRow, newCol});
                    }
                }
            }
        }
        // in the last pass, we are not adding any node to queue, only removing
        if(level>0)level--;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    level = -1;
                    break;
                }
            }
        }
        revertBack(grid);
        return level;
    }

    void revertBack(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 3) {
                    grid[i][j] = 1;
                }
            }
        }
    }

    @Test
    public void test() {

        int[][] grid = new int[][]{
                {0}


        };

        System.out.println(orangesRotting(grid));
    }


}
