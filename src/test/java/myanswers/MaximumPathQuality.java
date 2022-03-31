package myanswers;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.*;

public class MaximumPathQuality {
    int max=0;
    public int maximalPathQuality(int[] values, int[][] edges, int maxTime) {



        Map<Integer, List<int[]>> map = new HashMap<>();


        for(int[] edge: edges){
            if(!map.containsKey(edge[0])){
                map.put(edge[0], new ArrayList<>());
            }
            map.get(edge[0]).add(new int[]{edge[1], edge[2]});

            if(!map.containsKey(edge[1])){
                map.put(edge[1], new ArrayList<>());
            }
            map.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }



        Map<Integer, Set<Integer>> visited = new HashMap<>();
        for(int i=0; i < values.length ; i++){
            visited.put(i, new HashSet<>());
        }

        Map<Integer, Integer> path = new HashMap<>();
        path.put(0, 1);
        helper(values, map, maxTime, 0, path, 0, visited);
        return max;
    }



    void helper(int[] values,  Map<Integer, List<int[]>> map, int maxTime, int node,
                Map<Integer, Integer> path, int totalTime,  Map<Integer, Set<Integer>> visited){

        if(totalTime> maxTime){
            return;
        }

        if(node==0){
            max = Math.max(max, sum(path, values));
        }


        if(map.get(node)!=null) {
            for (int[] neighbour : map.get(node)) {
                if (visited.get(node).contains(neighbour[0])) {
                    continue;
                }
                visited.get(node).add(neighbour[0]);
                path.put(neighbour[0], path.getOrDefault(neighbour[0], 0) + 1);
                int time = totalTime + neighbour[1];
                helper(values, map, maxTime, neighbour[0], path, time, visited);
                path.put(neighbour[0], path.get(neighbour[0]) - 1);
                if (path.get(neighbour[0]) == 0) {
                    path.remove(neighbour[0]);
                }
                visited.get(node).remove(neighbour[0]);
            }
        }


    }


    int sum(Map<Integer, Integer> path, int[]values){
        int s = 0;
        for(int i: path.keySet()){
            s+=values[i];
        }
        return s;
    }

    @Test
    public void test(){


        int [] values = new int[]{1, 2, 3, 4};
        int [][] edges = new int[][]{
                {0,1,10},{1,2,11},{2,3,12},{1,3,13}
        };
        System.out.println(maximalPathQuality(values, edges, 50));
    }
}
