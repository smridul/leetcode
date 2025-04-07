package myanswers;

import org.junit.Test;

public class RedundantEdgeII {


    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] can1 = {-1, -1};
        int[] can2 = {-1, -1};
        int[] parent = new int[edges.length + 1];
        for (int i = 0; i < edges.length; i++) {
            if (parent[edges[i][1]] == 0) {
                parent[edges[i][1]] = edges[i][0];
            } else {
                can2 = new int[]{edges[i][0], edges[i][1]};
                can1 = new int[]{parent[edges[i][1]], edges[i][1]};
                edges[i][1] = 0;
            }
        }
        for (int i = 1; i <= edges.length; i++) {
            parent[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][1] == 0) {
                continue;
            }
            int child = edges[i][1], father = edges[i][0];
            if (root(parent, father) == child) {
                if (can1[0] == -1) {
                    return edges[i];
                }
                return can1;
            }
            parent[child] = father;
        }
        return can2;
    }

    int root(int[] parent, int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

    @Test
   public void test(){
        int [][] edges = new int[][]{{1,2},{2,3},{3,4}, {4,5}, {5,2}};
        int [] ans  = findRedundantDirectedConnection(edges);
        System.out.println(ans[0]+ "::" + ans[1]);



        String ss = ":abc::";
        String [] ssar = ss.split(":", -1);
        String [] ssar2 = ss.split(":");
        String [] ssar3 = ss.split(":",0);

        int a=0;
    }

}
