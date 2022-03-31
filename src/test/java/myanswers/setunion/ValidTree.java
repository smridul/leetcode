package myanswers.setunion;

import org.junit.Test;

public class ValidTree {


    //using set union
    public boolean validTree(int n, int[][] edges) {
        int [] parent = new int[n];

        for(int i=0; i < n; i++){
            parent[i] = i;
        }

        for(int[] edge: edges){


            int rootA = find(edge[0], parent);
            int rootB = find(edge[1], parent);
            if(rootA == rootB){
                return false;
            }

            union(rootA, rootB, parent);
        }


        return edges.length == n-1;

    }


    int find(int node, int[] parent){

        if(parent[node] != node){
            parent[node] = find(parent[node], parent);
        }

        return parent[node];
    }

    void union(int node1, int node2, int[] parent){
        parent[node1] = node2;
    }


    @Test
    public void test(){
        int edges[][] = new int[][]{{0,1},{0,4},{1,4},{2,3}};
        System.out.println(validTree(5, edges));
    }
}
