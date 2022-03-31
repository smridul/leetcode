import org.junit.Test;

import java.util.*;

public class MakeNetworkConnected {
    int extra=0;
    public int makeConnected(int n, int[][] connections) {


        Map<Integer, List<Integer>> map = new HashMap<>();

        for(int [] edge: connections){


            if(!map.containsKey(edge[0])){
                map.put(edge[0], new ArrayList<>());
            }

            map.get(edge[0]).add(edge[1]);

            if(!map.containsKey(edge[1])){
                map.put(edge[1], new ArrayList<>());
            }

            map.get(edge[1]).add(edge[0]);
        }


        boolean visited[] = new boolean[n];
        int components=0;
        for(int i=0; i < n; i++){

            if(!visited[i]){
                components++;
                dfs(i, visited, map, -1);
            }

        }

        if(components-1 <= extra){
            return components-1;
        }
        return -1;
    }



    void dfs(int node, boolean [] visited, Map<Integer, List<Integer>>map, int parent){

        visited[node] = true;
        if(map.get(node)!=null){

            for(int neighbour : map.get(node)){

                if(neighbour == parent){
                    continue;
                }

                if(visited[neighbour]){

                    extra++;
                    continue;
                }

                dfs(neighbour, visited, map, node);
            }

        }
    }

    @Test
    public void test(){
        int edges[][]= new int[][]{
                {0,1},{0,2},{0,3},{1,2}
        };
        System.out.println(makeConnected(6, edges));
    }
}
