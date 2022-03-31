package myanswers;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class DiameterOfNAryTree {

    int diameter;


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
