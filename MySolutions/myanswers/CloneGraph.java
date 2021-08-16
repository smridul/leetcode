package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by smridul on 1/25/19.
 */
public class CloneGraph {


    @Test
    public void test() {

        //  1-> 2, 5,
        // 2-> 3, 4
        // 5-> 6,7,8


        GraphVertex g3 = new GraphVertex(3);
        GraphVertex g4 = new GraphVertex(4);


        GraphVertex g2 = new GraphVertex(2);
        g2.edges.add(g3);
        g2.edges.add(g4);


        GraphVertex g6 = new GraphVertex(6);
        GraphVertex g7 = new GraphVertex(7);
        GraphVertex g8 = new GraphVertex(8);

        GraphVertex g5 = new GraphVertex(5);
        g5.edges.add(g6);
        g5.edges.add(g7);
        g5.edges.add(g8);


        GraphVertex g = new GraphVertex(1);
        g.edges.add(g2);
        g.edges.add(g5);


        HashMap visited = new HashMap<GraphVertex, GraphVertex>();
        visited.put(g, new GraphVertex(g.label));
        GraphVertex gg = clone(g, visited);

        System.out.print("");


        GraphVertex example = new GraphVertex(0);
        example.edges.add(example);
        example.edges.add(example);

        visited = new HashMap<GraphVertex, GraphVertex>();
        visited.put(example, new GraphVertex(example.label));
        GraphVertex exampleClone = clone(example, visited);

        System.out.print("");
    }


    public GraphVertex clone(GraphVertex g, HashMap<GraphVertex, GraphVertex> visited) {

        if (g == null) {
            return null;
        }

        visited.put(g, new GraphVertex(g.label));

        for (GraphVertex vertex : g.edges) {

            if (!visited.containsKey(vertex)) {

                GraphVertex cloned = clone(vertex, visited);
                visited.get(g).edges.add(cloned);

            } else {
                visited.get(g).edges.add(visited.get(vertex));
            }
        }
        return visited.get(g);
    }
}


class GraphVertex {

    public int label;
    public List<GraphVertex> edges;
    public int min = Integer.MAX_VALUE;
    public int color; // 0 unvisited, 1 grey, 2 finished
    public int distance;  //distnce with source node// used for bfs or distance calcuation algos

    public GraphVertex(int label) {
        this.label = label;
        edges = new ArrayList<>();
    }
}

