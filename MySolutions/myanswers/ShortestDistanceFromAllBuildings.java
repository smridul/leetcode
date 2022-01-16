package myanswers;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class ShortestDistanceFromAllBuildings {
    int leastDistance = Integer.MAX_VALUE;
    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int shortestDistance(int[][] grid) {
        int index = 1;
        int[][] distance = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    leastDistance = Integer.MAX_VALUE;
                    if (!bfs(grid, i, j, index, distance)) {
                        return -1;
                    }
                    index++;
                }

            }
        }
        return leastDistance;
    }

    boolean bfs(int[][] grid, int i, int j, int index, int[][] distance) {
        boolean noAnswer = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{i, j});
        int toFind = -(index - 1);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            level++;
            for (int k = 0; k < size; k++) {
                int[] cell = queue.poll();
                for (int[] dir : dirs) {
                    int row = cell[0] + dir[0];
                    int col = cell[1] + dir[1];
                    if (row >= 0 && row < grid.length && col >= 0 && col < grid[0].length && grid[row][col] == toFind) {
                        grid[row][col] = -index;
                        noAnswer = false;
                        queue.offer(new int[]{row, col});
                        distance[row][col] += level;
                        leastDistance = Math.min(leastDistance, distance[row][col]);
                    }
                }
            }
        }
        return !noAnswer;
    }

    @Test
    public void test() {
        int [][] grid = new int[][]{
                {1,0,2,0,1},
                {0,0,0,0,0},
                {0,0,1,0,0}
        };
        System.out.println(shortestDistance(grid));
    }
}
