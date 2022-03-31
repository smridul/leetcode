package myanswers;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CountComponents {
    Map<Integer, VertexNode> map = new HashMap<>();

    public int countComponents(int n, int[][] edges) {

        for(int i=0; i<n; i++){
            makeSet(i);
        }

        for(int[] edge: edges){
            union(edge[0], edge[1]);
        }

        Set<Integer> set =  new HashSet<>();
        for(int i=0; i<n; i++){
            VertexNode node = find(i);
            set.add(node.val);
        }

        return set.size();
    }


    void makeSet(int i) {
        VertexNode node = new VertexNode();
        node.val = i;
        node.parent = node;
        map.put(i, node);
    }

    VertexNode find(int i) {
        VertexNode node = map.get(i);

        if (node.parent != node) {
            node.parent = find(node.parent.val);
        }
        return node.parent;
    }

    void union(int i, int j){
        VertexNode l1 = find(i);
        VertexNode l2 = find(j);

        if(l1.rank > l2.rank){
            l2.parent = l1;
        }else {
            l1.parent = l2;
        }
        if(l1.rank == l2.rank){
            l2.rank++;
        }

    }

    @Test
    public void test(){

        int[][] edges = new int[][]{{0,1},{1,2},{3,4}};
        countComponents(5, edges);
    }

}

class VertexNode {
    int val;
    int rank;
    VertexNode parent;
}