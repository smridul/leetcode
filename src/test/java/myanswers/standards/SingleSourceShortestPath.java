package myanswers.standards;
import org.junit.Test;

import java.util.*;
// the disjstra algorith
// m
public class SingleSourceShortestPath {
    public int networkDelayTime1(int[][] times, int n, int k) {

        Map<Integer, List<Integer>> graph = new HashMap<>();

        // problem is that we want to quickly access the distance between two given nodes
        // therefore distance matrix is the right way
        int [][] distance = new int[n+1][n+1];

        for(int i =0; i<=n; i++){
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }


        for(int i =1; i<=n; i++){
            graph.put(i, new ArrayList<>());
        }

        int i=0;
        for(int [] row :  times){
            graph.get(row[0]).add(row[1]);
            distance[row[0]][row[1]] = row[2];
        }

        Queue<int[]> q = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(int[] a, int[] b){
                // 1 st element node, 2nd element distance from source
                return Integer.compare(a[1], b[1]);
            }
        });

        distance[k][k]= 0;
        q.offer(new int[]{k, 0});
        int time=0;
        while(!q.isEmpty()){

            int[] nodeCell = q.poll();
            int node = nodeCell[0];

            for(int neighbour : graph.get(node)){

                if(distance[k][node] + distance[node][neighbour] < distance[k][neighbour] ){
                    // relax
                    distance[k][neighbour] = distance[k][node] + distance[node][neighbour];
                    q.offer(new int[] {neighbour, distance[k][neighbour]});
                    time = Math.max(time, distance[k][neighbour]);
                }
            }

        }

        for(i=1; i<=n; i++){
            if(distance[k][i] == Integer.MAX_VALUE){
                return -1;
            }
        }

        return time;
    }



    public int networkDelayTime(int[][] times, int n, int k) {

        Map<Integer, List<Integer>> graph = new HashMap<>();

        // problem is that we want to quickly access the distance between two given nodes
        // therefore distance matrix is the right way
        int [][] distance = new int[n+1][n+1];
        int [][] edge = new int[n+1][n+1];

        for(int i =0; i<=n; i++){
            Arrays.fill(distance[i], Integer.MAX_VALUE);
        }


        for(int i =1; i<=n; i++){
            graph.put(i, new ArrayList<>());
        }

        int i=0;
        for(int [] row :  times){
            graph.get(row[0]).add(row[1]);
            edge[row[0]][row[1]] = row[2];
        }

        Queue<int[]> q = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(int[] a, int[] b){
                // 1 st element node, 2nd element distance from source
                return Integer.compare(a[1], b[1]);
            }
        });

        boolean visited[]= new boolean[n+1];
        distance[k][k]= 0;
        q.offer(new int[]{k, 0});
        int time=0;
        while(!q.isEmpty()){

            int[] nodeCell = q.poll();
            int node = nodeCell[0];
            if(visited[node]){
                continue;
            }
            visited[node] = true;

            for(int neighbour : graph.get(node)){
                distance[k][neighbour] = distance[k][node] + edge[node][neighbour];
                q.offer(new int[] {neighbour, distance[k][node] + edge[node][neighbour]});
            }
        }

        for(i=1; i<=n; i++){
            if(distance[k][i] == Integer.MAX_VALUE){
                return -1;
            }else{
                time = Math.max(time, distance[k][i]);
            }
        }

        return time;
    }




    @Test
    public void test(){
        int[][] arr = new int[][]{{2,1,1},{2,3,1},{3,4,1}};
        System.out.println(networkDelayTime(arr, 4, 2));
    }

















    // cost with discounts

    public int minimumCost(int n, int[][] highways, int discounts) {


        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i=0; i < n; i++){
            map.put(i, new ArrayList<>());
        }
        int[][] edge = new int[n][n];

        for(int[] highway : highways){
            map.get(highway[0]).add(highway[1]);
            map.get(highway[1]).add(highway[0]);
            edge[highway[0]][highway[1]] = highway[2];
            edge[highway[1]][highway[0]] = highway[2];
        }


        int[][][] dist = new int[n][n][discounts+1];

        for(int i =0; i<n; i++){
            for(int j=0; j < n; j++)
                Arrays.fill(dist[i][j], Integer.MAX_VALUE);
        }

        for(int i =0; i<=discounts; i++){
            dist[0][0][i] = 0;
        }

        Queue<int[]> q = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(int [] a, int[] b){
                // node, then distance from 0, then discount used
                return Integer.compare(a[1], b[1]);
            }
        });


        Arrays.fill(dist[0][0], 0);
        q.offer(new int[]{0, 0, 0, -1});
        while(!q.isEmpty()){
            int[] nodeCell = q.poll();
            int node = nodeCell[0];
            int cost = nodeCell[1];
            int discount = nodeCell[2];
            int parent = nodeCell[3];

            // once polled algo always return the minimum for the node for that third dimension
            if(node == n-1 && discount == discounts){
                // this shoudl be min node with discounts
                return cost;
            }

            for(int neighbour : map.get(node)){
                if(neighbour == parent){
                    continue;
                }
                // dont use discount first
                int newCost = dist[0][node][discount] + edge[node][neighbour];
                if( newCost < dist[0][neighbour][discount]){
                    dist[0][neighbour][discount] = newCost;
                    q.offer(new int[]{neighbour, newCost, discount, node});
                }

                // now use the discount
                if(discount < discounts) {
                    newCost = dist[0][node][discount] + edge[node][neighbour]/2;
                    if( newCost < dist[0][neighbour][discount+1]){
                        dist[0][neighbour][discount+1] = newCost;
                        q.offer(new int[]{neighbour, newCost, discount+1, node});
                    }
                }
            }
        }
        return -1;
    }

    @Test
    public void test2(){
        int[][] arr = new int[][]{{1,3,17},{1,2,7},{3,2,5},{0,1,6},{3,0,20}};
        System.out.println(minimumCost(4, arr,20));
    }

}
