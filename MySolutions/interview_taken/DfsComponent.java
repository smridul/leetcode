package interview_taken;

import org.junit.Test;

import java.util.ArrayList;
import java.util.*;

public class DfsComponent {


    List<List<Integer>> groupComponents(int[][] matrix) {

        List<List<Integer>> res = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            if(!visited.contains(i)){
                List<Integer> component = new ArrayList<>();
                dfs(i, matrix, component, visited);
                res.add(component);
            }
        }
        return res;
    }

    void dfs(int i, int[][] matrix, List<Integer> component, Set<Integer> visited){
        visited.add(i);
        component.add(i);

        for(int j = 0; j < matrix[0].length; j++){
            if(matrix[i][j]==1 && !visited.contains(j)){
                dfs(j, matrix, component, visited);
            }
        }
    }

    List<Integer> cumulativeWeight(int[][] matrix, int[] weight) {

        List<Integer> res = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            if(!visited.contains(i)){
                int cumulativeWeight = dfs1(i, matrix, visited, weight);
                res.add(cumulativeWeight);
            }
        }
        return res;
    }

    int dfs1(int i, int[][] matrix, Set<Integer> visited, int[] weight){
        visited.add(i);
        int sum = weight[i] ;
        for(int j = 0; j < matrix[0].length; j++){
            if(matrix[i][j]==1 && !visited.contains(j)){
                sum+=dfs1(j, matrix, visited, weight);
            }
        }
        return sum;
    }

    @Test
    public void test(){
        int graph[][] = new int[][]{
                {1, 0, 1, 0, 0},
                {0, 1, 0, 1, 0},
                {1, 0, 1, 0, 1},
                {0, 1, 0, 1, 0},
                {0, 0, 1, 0, 0}
        };

        int weight[] = new int[]{1, 2, 5, 1, 3};

        List<Integer> res = cumulativeWeight(graph, weight);


            for(int i: res){
                System.out.print(i + " ");
            }


    }

}
