package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 1/8/19.
 */
public class RedundantEdge {

    @Test
    public void test(){
       // int [][] edges = new int[][]{{1,2}, {1,3}, {2,3}};
        int [][] edges =  {{2,1},{3,1},{4,2},{1,4}};
        int [] edge = findRedundantConnection(edges);
        System.out.print(edge[0]+ " "+ edge[1]);
    }

    public int[] findRedundantConnection(int[][] edges) {
        UnionFind unionFind = new UnionFind(edges.length);

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            if (!unionFind.union(u, v)) {
                return edge;
            }
        }

        throw null;
    }
}

class UnionFind {
    private int[] parent;
    private int[] immediateParent;

    public UnionFind(int n) {
        parent = new int[n + 1];
        immediateParent = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            parent[i] = i;
            immediateParent[i] = -1;
        }
    }

    public int find(int x) {
        if (parent[x] == x)
            return x;
        return parent[x] = find(parent[x]);
    }

    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX == rootY)
            return false;

        if (immediateParent[y] != -1) {
            return false;
        }

        parent[rootY] = rootX;
        immediateParent[y] = x;
        //immediateParent[rootX] += immediateParent[rootY];
        return true;
    }
}