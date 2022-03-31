package myanswers;

import java.util.LinkedList;
import java.util.Queue;

public class WallsAndGate {

    int dirs[][] = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public void wallsAndGates(int[][] rooms) {

        int m = rooms.length;
        int n = rooms[0].length;

        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    queue.add(new int[]{i, j});
                }
            }
        }

        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int[] cell = queue.poll();

                for (int[] dir : dirs) {
                    int row = cell[0] + dir[0];
                    int col = cell[1] + dir[1];
                    if (row >= 0 && row < rooms.length && col >= 0 && col < rooms[0].length
                            && rooms[row][col] == Integer.MAX_VALUE) {
                        rooms[row][col] = Math.min(level, rooms[row][col]);
                        queue.add(new int[]{row, col});
                    }
                }
            }
        }

    }

}
