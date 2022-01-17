package myanswers;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class DiameterOfNAryTree {

    int diameter;

    // I will use 2 bfs solution here to get the longest path.
    // THis will work only for graphs i,e if we can go upwards too in the tree
    public int diameter(Node root) {

        if(root ==null){
            return -1;
        }
        NodeWithPath firstend = bfs(root);
        NodeWithPath secondEnd = bfs(firstend.node);

        return secondEnd.len;
    }

    NodeWithPath bfs(Node root) {
        Queue<NodeWithPath> queue = new LinkedList<>();
        queue.add(new NodeWithPath(root, null, 0));

        int level = 0;
        NodeWithPath farthestNode = null;
        while (!queue.isEmpty()) {

            level++;
            int size = queue.size();
            farthestNode = queue.peek();
            for (int k = 0; k < size; k++) {
                NodeWithPath node = queue.poll();
                for (Node child : node.node.children) {
                    queue.add(new NodeWithPath(child, node, level));
                }
            }
        }

        return farthestNode;
    }


    public int diameterOfNAryTree(Node root) {

        diameter = 0;
        if (root == null) {
            return 0;
        }

        recurse(root);
        return diameter;
    }

    public int recurse(Node root) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for (Node node : root.children) {
            int height = recurse(node);
            pq.add(height);
        }

        int longLeg = !pq.isEmpty() ? pq.poll() + 1 : 0;
        int nextLongLeg = !pq.isEmpty() ? pq.poll() + 1 : 0;

        diameter = Math.max(diameter, longLeg + nextLongLeg);
        return longLeg;
    }
}

class NodeWithPath {
    Node node;
    NodeWithPath parent;
    int len;

    public NodeWithPath(Node node, NodeWithPath parent, int len) {
        this.node = node;
        this.parent = parent;
        this.len = len;
    }
}
