package myanswers;

import org.junit.Test;

import java.util.*;

public class MinimumKnightMoves {

    int[][] offsets = {{1, 2}, {2, 1}, {2, -1}, {1, -2},
            {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2}};

    // my version
    public int minKnightMoves(int x, int y) {
        if (x == 0 && y == 0) {
            return 0;
        }

        x = Math.abs(x);
        y = Math.abs(y);
        Queue<int[]> beginQueue = new LinkedList<>();
        Queue<int[]> endQueue = new LinkedList<>();

        beginQueue.add(new int[]{0, 0});
        beginQueue.add(null);

        endQueue.add(new int[]{x, y});
        endQueue.add(null);

        // all visited nodes from begin
        Map<String, Boolean> beginSet = new HashMap<>();
        beginSet.put("0.0", true);
        // all visited nodes from end
        Map<String, Boolean> endSet = new HashMap<>();
        endSet.put(x + "." + y, true);

        int steps = 0;
        while (!beginQueue.isEmpty() && !endQueue.isEmpty()) {

            steps++;
            if (exploreAllNodesOfLevel(beginQueue, beginSet, endSet)) {
                break;
            }
            steps++;
            if (exploreAllNodesOfLevel(endQueue, endSet, beginSet)) {
                break;
            }
        }

        return steps;
    }

    public boolean exploreAllNodesOfLevel(Queue<int[]> queue, Map<String, Boolean> visited,
                                          Map<String, Boolean> visitedFromOtherDirection) {
        int[] node = queue.poll();
        while (node != null) {
            for (int[] offset : offsets) {
                int[] newNode = new int[]{node[0] + offset[0], node[1] + offset[1]};
                if (visitedFromOtherDirection.containsKey(newNode[0] + "." + newNode[1])) {
                    return true;
                }
                if (!visited.containsKey(newNode[0] + "." + newNode[1]) && newNode[0] > -2 && newNode[1] > -2) {
                    visited.put(newNode[0] + "." + newNode[1], true);
                    queue.add(newNode);
                }
            }
            node = queue.poll();
        }
        queue.add(null);// this will cause endless loop if node is not found. see how i handled this in
        //bfslevel printing class.  Add null only if queue not empty
        return false;
    }

    @Test
    public void test() {
        System.out.println(minKnightMoves(0, -300));
    }

}
