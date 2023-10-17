package myanswers;

import org.junit.Test;

import java.util.*;

public class PacificAtlanticWater {

    int dirs[][] = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        int m = heights.length;
        int n = heights[0].length;

        int[][] pacific = new int[m][n];
        int[][] atlantic = new int[m][n];

        List<List<Integer>> result = new ArrayList<>();
        //left pacific
        for (int i = 0; i < heights.length; i++) {

            dfs(i, 0, heights, pacific);

        }

        // top pacific
        for (int i = 0; i < heights[0].length; i++) {

            dfs(0, i, heights, pacific);

        }

        //right atlantic
        for (int i = 0; i < heights.length; i++) {
            dfs(i, n - 1, heights, atlantic);
        }

        // bottom atlantic
        for (int i = 0; i < heights[0].length; i++) {

            dfs(m - 1, i, heights, atlantic);

        }

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(pacific[i][j]==1 && atlantic[i][j]==1)
                    result.add(Arrays.asList(i, j));
            }
        }

        return result;
    }

    public void dfs(int i, int j, int[][] height, int[][] visited) {

        if (visited[i][j] == 0) {
            visited[i][j] = 1;
            for (int[] dir : dirs) {
                int row = i + dir[0];
                int col = j + dir[1];
                if (row >= 0 && row < height.length && col >= 0 && col < height[0].length
                        && height[row][col] >= height[i][j]) {
                    dfs(row, col, height, visited);
                }
            }
        }
    }


    public void dfs2(int i, int j, int[][] height, List<List<Integer>> result, int[][] visited) {

        if (visited[i][j] == 0 || visited[i][j] == 1) {

            if (visited[i][j] == 1) {
                result.add(Arrays.asList(i, j));
            }
            visited[i][j] = 2;

            for (int[] dir : dirs) {
                int row = i + dir[0];
                int col = j + dir[1];
                if (row >= 0 && row < height.length && col >= 0 && col < height[0].length
                        && height[row][col] >= height[i][j]) {
                    dfs2(row, col, height, result, visited);
                }
            }
        }
    }

    @Test
    public void test() {
        int[][] grid = new int[][]{
                {1, 2, 2, 3, 5},
                {3, 2, 3, 4, 4},
                {2, 4, 5, 3, 1},
                {6, 7, 1, 4, 5},
                {5, 1, 1, 2, 4}
        };
        List<List<Integer>> res = pacificAtlantic(grid);
        for (List<Integer> list : res) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }


        List<List<Integer>> res2 = pacificAtlantic2(grid);
        for (List<Integer> list : res2) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }



    List<List<Integer>> answer = new ArrayList<>();
    public List<List<Integer>> pacificAtlantic2(int[][] heights) {

        Queue<int[]> queue =  new LinkedList<>();
        int[][] visited = new int[heights.length][heights[0].length];

        for(int i=0; i < heights[0].length; i++){
            queue.offer(new int[]{0, i});
            visited[0][i] = -1;
        }

        for(int i=1; i < heights.length; i++){
            queue.offer(new int[]{i, 0});
            visited[i][0] = -1;
        }


        System.out.println("===========================================");
        bfs(heights, queue, 1, visited, -2);

        queue =  new LinkedList<>();

        for(int i=0; i < heights[0].length; i++){
            queue.offer(new int[]{heights.length-1, i});
            visited[heights.length-1][i] = -2;
        }

        for(int i=0; i < heights.length-1; i++){
            queue.offer(new int[]{i, heights[0].length-1});
            visited[i][heights.length-1] = -2;
        }

        bfs(heights, queue, 2, visited, -1);

        return answer;

    }






    public void bfs(int [][] grid, Queue<int[]> queue, int index, int[][] visited, int toFind){


        while(!queue.isEmpty()){
            int size = queue.size();
            while(size-- > 0 ){
                int[] cell = queue.poll();
                int row =cell[0];
                int col = cell[1];
                if(visited[row][col]== toFind){
                    answer.add(Arrays.asList(row, col));
                }
                visited[row][col] = -index;
                for(int[] dir: dirs){
                    int newRow = cell[0] + dir[0];
                    int newCol = cell[1] + dir[1];

                    if(newRow < grid.length && newCol < grid[0].length && newRow >=0 && newCol >=0 &&
                            grid[newRow][newCol] >= grid[row][col] && visited[newRow][newCol]!=-index){

                        queue.add(new int[]{newRow, newCol});
                    }
                }
            }
        }
    }
}
