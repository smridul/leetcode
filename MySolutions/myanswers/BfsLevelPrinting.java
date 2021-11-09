package myanswers;

import org.junit.Test;

import java.util.*;

public class BfsLevelPrinting {

    void bfsLevelPrinting(BfsNode begin, GraphAsList graph) {
        Queue<BfsNode> queue = new LinkedList<>();

        begin.color = 1;
        queue.add(begin);
        queue.add(null);

        int level = 0;
        while (!queue.isEmpty()) {
            BfsNode node = queue.poll();
            if (node == null) {
                level++;
                if (!queue.isEmpty()) {
                    // to terminate loop. No need to add null now if no element remaining
                    queue.add(null);
                }
                continue;
            }

            System.out.println("level:" + level + " value:" + node.value);
            for (BfsNode neighbor : graph.adjacencyList.get(node)) {
                if (neighbor.color == 0) {
                    neighbor.color = 1;
                    queue.add(neighbor);
                }
            }
        }
    }

    void bfsLevelPrinting2(BfsNode begin, GraphAsList graph) {
        Queue<BfsNode> queue = new LinkedList<>();

        begin.color = 1;
        queue.add(begin);

        int level = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                BfsNode node = queue.poll();
                System.out.println("level:" + level + " value:" + node.value);
                for (BfsNode neighbor : graph.adjacencyList.get(node)) {
                    if (neighbor.color == 0) {
                        neighbor.color = 1;
                        queue.add(neighbor);
                    }
                }
            }
            level++;
        }
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
        bfsLevelPrinting2(one, graph);
    }

}
