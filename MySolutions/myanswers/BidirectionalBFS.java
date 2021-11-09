package myanswers;

import org.junit.Test;

import java.util.*;

public class BidirectionalBFS {
    LinkedList<BfsNode> path;

    void bidirectionalBfs(BfsNode begin, BfsNode end, GraphAsList graph) {
        Queue<BfsNode> beginQueue = new LinkedList<>();
        Queue<BfsNode> endQueue = new LinkedList<>();

        begin.color = 1;
        beginQueue.add(begin);
        beginQueue.add(null);

        end.color = 1;
        endQueue.add(end);
        endQueue.add(null);

        path = new LinkedList<>();
        if (begin == end) {
            path.add(begin);
            printPath();
            return;
        }


        // all visited nodes from begin
        Set<BfsNode> beginSet = new HashSet<>();
        beginSet.add(begin);
        // all visited nodes from end
        Set<BfsNode> endSet = new HashSet<>();
        endSet.add(end);
        Map<BfsNode, BfsNode> map = new HashMap<>();
        while (!beginQueue.isEmpty() && !endQueue.isEmpty()) {

            BfsNode foundNode = exploreAllNodesOfLevel(beginQueue, beginSet,
                    endSet, graph, begin, end, map, true);
            if (foundNode != null) {
                break;
            }
            foundNode = exploreAllNodesOfLevel(endQueue, endSet, beginSet,
                    graph, end, begin, map, false);
            if (foundNode != null) {
                break;
            }
        }

        // now print the path from begin to end

        printPath();
    }

    void printPath(){
        for (BfsNode node : path) {
            System.out.println(node.value + " ");
        }
    }


    void iteratePath(BfsNode node, BfsNode parent, Map<BfsNode, BfsNode> map, boolean reverse) {
        while (node != parent) {
            if (reverse) path.addFirst(node);
            else
                path.add(node);
            node = map.get(node);
        }
        if (reverse) path.addFirst(parent);
        else
            path.add(parent);
    }

    public BfsNode exploreAllNodesOfLevel(Queue<BfsNode> queue, Set<BfsNode> visited,
                                          Set<BfsNode> visitedFromOtherDirection,
                                          GraphAsList graph,
                                          BfsNode parent,
                                          BfsNode otherParent,
                                          Map<BfsNode, BfsNode> map,
                                          boolean exploringBegin) {
        BfsNode node = queue.poll();
        while (node != null) {
            for (BfsNode neighbour : graph.adjacencyList.get(node)) {
                if (visitedFromOtherDirection.contains(neighbour)) {
                    iteratePath(node, parent, map, exploringBegin);
                    iteratePath(neighbour, otherParent, map, !exploringBegin);
                    return neighbour;
                }
                if (neighbour.color == 0) {
                    neighbour.color = 1;
                    map.put(neighbour, node);
                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }
            node = queue.poll();
        }
        queue.add(null); // this will cause endless loop if node is not found. see how i handled this in
        //bfslevel printing class. Add null only if queue not empty
        return null;
    }


    @Test
    public void test() {

        BfsNode one = new BfsNode(1);
        BfsNode two = new BfsNode(2);
        BfsNode three = new BfsNode(3);
        BfsNode four = new BfsNode(4);
        BfsNode five = new BfsNode(5);
        BfsNode six = new BfsNode(6);
        BfsNode seven = new BfsNode(7);
        BfsNode eight = new BfsNode(8);

        GraphAsList graph = new GraphAsList();
        graph.adjacencyList = new HashMap<>();

        graph.adjacencyList.put(one, Arrays.asList(two, three));
        graph.adjacencyList.put(two, Arrays.asList(one, four, five));
        graph.adjacencyList.put(three, Arrays.asList(one, six, seven));
        graph.adjacencyList.put(four, Arrays.asList(two));
        graph.adjacencyList.put(five, Arrays.asList(two, eight));
        graph.adjacencyList.put(six, Arrays.asList(three, eight));
        graph.adjacencyList.put(seven, Arrays.asList(three));
        graph.adjacencyList.put(eight, Arrays.asList(five, six));


        bidirectionalBfs(one, five, graph);


    }
}

class GraphAsList {
    Map<BfsNode, List<BfsNode>> adjacencyList;
}

class BfsNode {
    int value;
    int color; // 0 not visited, 1 visited

    public BfsNode(int v) {
        value = v;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BfsNode bfsNode = (BfsNode) o;
        return value == bfsNode.value;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}