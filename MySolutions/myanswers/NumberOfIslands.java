package myanswers;

import org.junit.Test;

public class NumberOfIslands {

    int counter = 0;

    @Test
    public void test() {
        char[][] grid = {
                {'1', '1', '1', '1', '0'},
                {'1', '1', '0', '1', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '0', '0', '0'},
        };
        System.out.println(numIslands(grid));
    }
    public int numIslands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int[][] visited = new int[grid.length][grid[0].length];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {

                if(visited[i][j]==0 && grid[i][j]=='1') {
                    counter++;
                    dfs(i, j, grid, visited);
                }
            }
        }
        return counter;
    }

    public void dfs(int i, int j, char[][] grid, int[][] visited) {

        if(i<0 || i> grid.length-1){
            return;
        }
        if(j<0 || j> grid[0].length-1){
            return;
        }
        
        if(visited[i][j]==1 || grid[i][j]=='0'){
            return;
        }
        visited[i][j]=1;
        dfs(i-1, j, grid, visited);
        dfs(i, j+1, grid, visited);
        dfs(i+1, j, grid, visited);
        dfs(i, j-1, grid, visited);
    }
}
