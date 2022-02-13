package myanswers.dijkstra;

import javafx.util.Pair;
import leetcode_classes.Node;
import org.junit.Test;

import java.util.*;

public class CheapestPrice {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {


        //int [] ;
        // node, distance, hop
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){

            public int compare(int [] a, int[] b){
                return Integer.compare(a[1], b[1]);
            }

        });

        Map<Integer, List<int[]>> map = new HashMap<>();
        for(int [] edge: flights){

            if(!map.containsKey(edge[0])){
                map.put(edge[0], new ArrayList<>());
            }
            map.get(edge[0]).add(new int[]{edge[1], edge[2]});
        }


        pq.offer(new int[]{src, 0, -1, -1});

        Set<Pair<Integer, Integer>> visited = new HashSet<>();
        while(!pq.isEmpty()){

            int [] node = pq.poll();

            visited.add(new Pair<>(node[0], node[2]));
            if(node[0] == dst){
                return node[1];
            }

            if(node[2]==k){
                continue;
            }

            if(map.get(node[0])!=null){

                for(int[] nei : map.get(node[0])){

                    if(node[2] +1 > k){
                        continue;
                    }

                    if(visited.contains(new Pair<>(nei[0], node[2]+1))){
                        continue;
                    }
                    pq.offer(new int[]{ nei[0], node[1] + nei[1], node[2]+1});
                }

            }

        }

        return -1;

    }

    @Test
    public void test(){


        int n=11;
        int src=0;
        int dst = 2;
        int k=4;
        int [][] flights = new int[][]{{0,3,3},{3,4,3},{4,1,3},{0,5,1},{5,1,100},{0,6,2},{6,1,100},{0,7,1},{7,8,1},{8,9,1},{9,1,1},{1,10,1},{10,2,1},{1,2,100}};


        System.out.println(findCheapestPrice(n, flights, src, dst, k));

    }
}
