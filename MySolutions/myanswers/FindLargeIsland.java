package myanswers;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindLargeIsland {

    Map<String, CellNode> cellToNode = new HashMap<>();
    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    @Test
    public void test() {
        int[][] grid = new int[][]{{1, 0}, {0, 1}};
        System.out.println(largestIsland(grid));
    }

    public int largestIsland(int[][] grid) {

        Map<String, Integer> areaMap = new HashMap<>();
        int maxarea = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    int area = dfs(i, j, grid, visited);
                    String islandLeader = findSet("" + i + "," + j);
                    areaMap.put(islandLeader, area);
                    maxarea = Math.max(maxarea, area);
                }
            }
        }

        int a = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] == 0) {
                    int area = 0;
                    Set<String> set = new HashSet<>();
                    for (int[] dir : dirs) {

                        int row = i + dir[0];
                        int col = j + dir[1];

                        if (row < grid.length && col < grid[0].length && row >= 0 && col >= 0 &&
                                grid[row][col] == 1) {
                            String neighbour = "" + row + "," + col;
                            String cell = findSet(neighbour);
                            if(!set.contains(cell)) {
                                area += areaMap.get(cell);
                                set.add(cell);
                            }
                        }
                    }
                    maxarea = Math.max(maxarea, area + 1);
                }
            }
        }
        return maxarea;
    }

    int dfs(int i, int j, int[][] grid, boolean[][] visited) {

        visited[i][j] = true;
        int area = 1;
        for (int[] dir : dirs) {
            int row = i + dir[0];
            int col = j + dir[1];

            if (row < grid.length && col < grid[0].length && row >= 0 && col >= 0 &&
                    grid[row][col] == 1 && !visited[row][col]) {
                String neighbour = "" + row + "," + col;
                area += dfs(row, col, grid, visited);
                union("" + i + "," + j, neighbour);
            }
        }
        return area;
    }


    public void union(String node1, String node2) {

        String l1 = findSet(node1);
        String l2 = findSet(node2);
        CellNode leader1 = cellToNode.get(l1);
        CellNode leader2 = cellToNode.get(l2);

        if (leader1.rank > leader2.rank) {
            leader2.parent = leader1;
        } else {
            leader1.parent = leader2;
        }
        if (leader1.rank == leader2.rank) {
            // assume leader2 as parent in case of tie
            leader2.rank++;
        }
    }

    public String findSet(String n) {
        if (!cellToNode.containsKey(n)) {
            makeSet(n);
        }
        CellNode node = cellToNode.get(n);
        while (node.parent != node) {
            node = node.parent;
        }
        return node.cell;
    }

    public void makeSet(String cell) {
        CellNode cellNode = new CellNode(cell, 0);
        cellToNode.put(cell, cellNode);
    }

}


class CellNode {
    String cell;
    int rank;
    CellNode parent;

    public CellNode(String cell, int rank) {
        this.cell = cell;
        this.rank = rank;
        this.parent = this;
    }
}