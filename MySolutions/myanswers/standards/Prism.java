package myanswers.standards;

import javafx.util.Pair;
import org.junit.Test;

import java.util.*;

public class Prism {
    //using prism
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {



        int [] vertex = new int[n+1];
        Arrays.fill(vertex, Integer.MAX_VALUE);

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>(){

            @Override
            public int compare(Integer a, Integer b){
                return Integer.compare(vertex[a], vertex[b]);
            }
        });


        vertex[0]=0;

        pq.offer(0);

        Map<Integer, Set<Integer>> map = new HashMap<>();
        Map<Pair<Integer, Integer>, Integer> weight = new HashMap<>();



        List<int[]> totalEdges = new ArrayList<>();

        for(int[] edge: pipes){
            totalEdges.add(edge);
        }

        for(int i=0; i<wells.length; i++){
            totalEdges.add(new int[]{0, i+1, wells[i]});
        }

        for(int[] edge: totalEdges){

            int v1 = edge[0];
            int v2 = edge[1];

            if(!map.containsKey(v1)){
                map.put(v1, new HashSet<>());
            }
            map.get(v1).add(v2);

            if(!map.containsKey(v2)){
                map.put(v2, new HashSet<>());
            }
            map.get(v2).add(v1);

            int w1 = weight.getOrDefault(new Pair<>(v1, v2), Integer.MAX_VALUE);
            int w2 = weight.getOrDefault(new Pair<>(v2, v1), Integer.MAX_VALUE);

            weight.put(new Pair<>(v1, v2), Math.min(Math.min(w1, w2) , edge[2]));
            weight.put(new Pair<>(v2, v1), Math.min(Math.min(w1, w2) , edge[2]));

        }






        int mincost = 0 ;
        boolean [] visited = new boolean[n+1];
        int count=0;
        while(count!=n+1){

            int v = getMinNode(vertex, visited);
            visited[v] = true;
            count++;
            mincost+=vertex[v];

            if(map.get(v)!=null){
                for(int neighbour :  map.get(v)){

                    if(!visited[neighbour]){
                        vertex[neighbour] = Math.min(vertex[neighbour], weight.get(
                                new Pair<Integer, Integer>(v, neighbour)));
                    }

                }
            }

        }



        return mincost;
    }

    public int getMinNode(int[] vertex, boolean[] visited){
        int min=Integer.MAX_VALUE;
        int minVertex=0;
        for(int i=0; i < vertex.length; i++){
            if(!visited[i] && vertex[i] < min){
                min = vertex[i];
                minVertex = i;
            }
        }
        return minVertex;
    }

    @Test
    public void test(){


        int[] wells = new int[]{1, 2,2};
        int[][] pipes = new int[][]{
                {1,2,1},{2,3,1}
        };
        System.out.println(minCostToSupplyWater(3, wells, pipes));


    }
}
