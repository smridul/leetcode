package myanswers.standards;

import java.util.*;


// leetcode 1168
public class Kruskal {
    //using kruskal with pq
    public int minCostToSupplyWater1(int n, int[] wells, int[][] pipes) {


        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){

            @Override
            public int compare(int [] a, int[] b){
                return Integer.compare(a[2], b[2]);

            }
        });



        for(int[] edge: pipes){

            pq.offer(edge);
        }


        for(int i=0; i<wells.length; i++){
            pq.offer(new int[]{0, i+1, wells[i]});
        }


        int [] parents = new int[n+1];
        for(int i=0; i < parents.length; i++){
            parents[i] = i;
        }

        int mincost =0 ;
        while(!pq.isEmpty()){

            int [] edge = pq.poll();

            if(union(edge[0], edge[1], parents)){
                mincost+= edge[2];
            }

        }

        return mincost;

    }


    int find(int node, int [] parents){
        if(parents[node]!=node){
            parents[node] = find(parents[node], parents);
        }
        return parents[node];
    }

    boolean union(int a, int b, int [] parents ){

        int root1 = find(a, parents);
        int root2 = find(b, parents);

        boolean done=false;
        if(root1!=root2){
            parents[root1] = root2;
            done = true;

        }
        return done;
    }










    //using kruskal with sorting
    public int minCostToSupplyWater2(int n, int[] wells, int[][] pipes) {


        Comparator<int[]> cmp = new Comparator<int[]>(){

            @Override
            public int compare(int [] a, int[] b){
                return Integer.compare(a[2], b[2]);

            }
        };


        List<int[]> list  = new ArrayList<>();

        for(int[] edge: pipes){

            list.add(edge);
        }


        for(int i=0; i<wells.length; i++){
            list.add(new int[]{0, i+1, wells[i]});
        }


        Collections.sort(list, cmp);

        int [] parents = new int[n+1];
        for(int i=0; i < parents.length; i++){
            parents[i] = i;
        }

        int mincost =0 ;
        for(int i=0; i < list.size(); i++){

            int [] edge = list.get(i);

            if(union(edge[0], edge[1], parents)){
                mincost+= edge[2];
            }

        }

        return mincost;

    }
}
