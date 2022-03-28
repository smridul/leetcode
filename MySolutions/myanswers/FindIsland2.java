package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindIsland2 {


    Map<String, String> parent = new HashMap<>();

    int[][] dirs = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {


        int grid[][] = new int[m][n];

        int count = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < positions.length; i++) {

            int row = positions[i][0];
            int col = positions[i][1];

            if(parent.containsKey(row + ":" + col)){
                ans.add(count);
                continue;
            }
            grid[row][col] = 1;
            String currentLeader = findSet(row + ":" + col);
            count++;

            for (int[] dir : dirs) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];


                if (newRow >= 0 && newCol >= 0 && newRow < m && newCol < n && grid[newRow][newCol] == 1) {

                    String leader = findSet(newRow + ":" + newCol);

                    if (!leader.equals(currentLeader)) {

                        union(leader, currentLeader);
                        count--;
                        currentLeader = findSet(leader);
                    }
                }

            }
            ans.add(count);
        }
        return ans;
    }



    @Test
    public void test(){


        int [][] arr =  new int[][]{{0,1},{1,2},{2,1},{1,0},{0,2},{0,0},{1,1}};
        List<Integer> ans = numIslands2(3, 3, arr);
        System.out.println(ans);
    }

    public void union(String node1, String node2) {

        String l1 = findSet(node1);
        String l2 = findSet(node2);


        // making parent L1 always
        parent.put(l2, l1);
    }

    public String findSet(String n) {
        if (!parent.containsKey(n)) {
            makeSet(n);
            return n;
        }

        if (!(parent.get(n).equals(n))) {
            parent.put(n, findSet(parent.get(n)));
        }
        return parent.get(n);
    }

    public void makeSet(String cell) {
        parent.put(cell, cell);
    }
}
