import org.junit.Test;

public class MaxAreaOfIsland {



    int max=0;
    int localarea=0;
    public int maxAreaOfIsland(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;
        for(int i=0; i<m; i++ ){
            for(int j=0; j<n; j++){
                if(grid[i][j]!=0) {
                    localarea = 0;
                    dfs(i, j, grid);
                }
            }
        }
        return max;
    }

    public void dfs(int i, int j, int[][] grid){
        int m = grid.length;
        int n = grid[0].length;
        if(grid[i][j]!=0){
            localarea = localarea+1;
            max=Math.max(max, localarea);
            grid[i][j] = 0;
            if(j!=n-1){
                dfs(i, j+1, grid);
            }
            if(j!=0){
                dfs(i, j-1, grid);
            }
            if(i!=m-1){
                dfs(i+1, j, grid);
            }
            if(i!=0){
                dfs(i-1, j, grid);
            }
        }
    }

    @Test
    public void test(){

        int [][] grid = new int[][]{
                {0, 1, 0, 1},
                {0, 1, 1, 1},
                {0, 0, 0, 1},
                {0, 0, 0, 0}
        };

        System.out.println(maxAreaOfIsland(grid));
    }
}
