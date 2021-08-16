package myanswers;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

/**
 * Created by smridul on 1/31/19.
 */
public class BreadthFirstSearch {
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

        bfsWithPath(g, g8);
        System.out.println();
        bfsWithPath2(g, g8);


        System.out.println();

        g4.edges.add(g5);
        bfsCycleWithColorAndDistance(g);

    }

    public void bfsWithPath(GraphVertex source, GraphVertex destination) {

        Queue<GraphVertex> queue = new ArrayDeque<>();

        HashMap<GraphVertex, Boolean> visited = new HashMap<>();
        queue.add(source);
        HashMap<GraphVertex, Path> map = new HashMap<>();
        map.put(source, new Path(source, null));
        while (!queue.isEmpty()) {

            GraphVertex currentNode = queue.poll();
            Path currentPath = map.get(currentNode);
            if (destination == currentNode) {
                break;
            }
            for (GraphVertex node : currentNode.edges) {
                if (!visited.containsKey(node)) {
                    map.put(node, new Path(node, currentPath));
                    queue.add(node);
                    visited.put(node, true);
                }
            }
        }


        ArrayList<GraphVertex> list = new ArrayList<>();
        if(map.containsKey(destination)){
            list.add(destination);
            Path path = map.get(destination);
            while (path.parent != null){
                path = path.parent;
                list.add(path.node);
            }
        }

        for(GraphVertex v: list){
            System.out.print(v.label + " ");
        }
    }



    public void bfsWithPath2(GraphVertex source, GraphVertex destination) {

        Queue<GraphVertex> queue = new ArrayDeque<>();

        HashMap<GraphVertex, Boolean> visited = new HashMap<>();
        queue.add(source);
        HashMap<GraphVertex, GraphVertex> map = new HashMap<>();// it maps node to its parent
        map.put(source, null);
        while (!queue.isEmpty()) {

            GraphVertex currentNode = queue.poll();
            if (destination == currentNode) {
                break;
            }
            for (GraphVertex node : currentNode.edges) {
                if (!visited.containsKey(node)) {
                    map.put(node, currentNode);
                    queue.add(node);
                    visited.put(node, true);
                }
            }
        }


        ArrayList<GraphVertex> list = new ArrayList<>();
        if(map.containsKey(destination)){
            while (destination != null){
                list.add(destination);
                destination = map.get(destination);
            }
        }

        for(GraphVertex v: list){
            System.out.print(v.label + " ");
        }

    }






    // CYCLE in UNDIRECTED GRAPH
    // can be done with parent also
    //simple logic if child is already visited and not a parent ==> cycle
    public void bfsCycleWithColorAndDistance(GraphVertex source) {
        Queue<GraphVertex> queue = new ArrayDeque<>();

        HashMap<GraphVertex, Boolean> visited = new HashMap<>();
        source.color  = 1;
        source.distance = 0;
        queue.add(source);

        //HashMap<GraphVertex, GraphVertex> map = new HashMap<>();// it maps node to its parent
       // map.put(source, null);
        while (!queue.isEmpty()) {

            GraphVertex currentNode = queue.poll();

            for (GraphVertex node : currentNode.edges) {
                if (node.color == 0) {
                    queue.add(node);
                    node.color = 1;
                    node.distance = currentNode.distance+1;
                } else if(node.color == 1){
                    System.out.print("Cycle detected link to ancestor");
                    return;
                } else if(node.color == 2 && node.distance != currentNode.distance+1){
                    System.out.print("Cycle detected link to some node in other subtree");
                    return;
                }
            }
            currentNode.color = 2;
        }
        System.out.print("No Cycle detected");

    }
}

class Path {
    GraphVertex node;
    Path parent;

    public Path(GraphVertex source, Path parent) {
        this.node = source;
        this.parent = parent;
    }
}
