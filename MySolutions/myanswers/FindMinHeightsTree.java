package myanswers;

import org.junit.Test;

import java.util.*;

public class FindMinHeightsTree {

    // topological sort concept
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {



        Map<Integer, List<Integer>> map = new HashMap<>();

        int[] degree = new int[n];

        for (int[] edge : edges) {
            List<Integer> list1 = map.getOrDefault(edge[0], new ArrayList<>());
            list1.add(edge[1]);
            map.put(edge[0], list1);
            degree[edge[0]]++;

            List<Integer> list2 = map.getOrDefault(edge[1], new ArrayList<>());
            list2.add(edge[0]);
            map.put(edge[1], list2);
            degree[edge[1]]++;
        }
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < degree.length; i++) {
            if (degree[i] == 1 || degree[i] == 0) {
                queue.add(i);
            }
        }

        while (!queue.isEmpty()) {

            if (n <= 2) {
                break;
            }
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int node = queue.poll();
                n--;
                for (int neighbour : map.get(node)) {
                    degree[neighbour]--;
                    if (degree[neighbour] == 1) {
                        queue.add(neighbour);
                    }
                }
            }
        }

        List<Integer> res = new ArrayList<>(queue);
        return res;
    }

    @Test
    public void test() {

        int[][] edges = new int[][]{{0, 1}, {1, 2}, {1,3}};
        System.out.println(findMinHeightTrees1(4, edges));
    }


    // 2 BFS solution
    public List<Integer> findMinHeightTrees1(int n, int[][] edges) {

        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int[] edge : edges) {
            List<Integer> list1 = map.getOrDefault(edge[0], new ArrayList<>());
            list1.add(edge[1]);
            map.put(edge[0], list1);

            List<Integer> list2 = map.getOrDefault(edge[1], new ArrayList<>());
            list2.add(edge[0]);
            map.put(edge[1], list2);
        }

        NodeWithPath2 firstEnd = bfs(0, map);

        NodeWithPath2 secondEnd = bfs(firstEnd.node, map);


        List<Integer> path = new ArrayList<>();
        while (secondEnd != null) {
            path.add(secondEnd.node);
            secondEnd = secondEnd.parent;
        }


        if (path.size() % 2 == 1) {
            return Arrays.asList(path.get(path.size() / 2));
        } else {
            return Arrays.asList(path.get(path.size() / 2 - 1), path.get(path.size() / 2));
        }
    }

    NodeWithPath2 bfs(int root, Map<Integer, List<Integer>> map) {
        Queue<NodeWithPath2> queue = new LinkedList<>();
        queue.add(new NodeWithPath2(root, null, 0));
        boolean[] visited = new boolean[map.size()];

        visited[root] = true;
        int level = 0;
        NodeWithPath2 farthestNode = null;
        while (!queue.isEmpty()) {

            level++;
            int size = queue.size();
            farthestNode = queue.peek();
            for (int k = 0; k < size; k++) {
                NodeWithPath2 node = queue.poll();
                for (int child : map.get(node.node)) {
                    if(!visited[child]) {
                        queue.add(new NodeWithPath2(child, node, level));
                        visited[child] = true;
                    }
                }
            }
        }

        return farthestNode;
    }
}

class NodeWithPath2 {
    int node;
    NodeWithPath2 parent;
    int len;

    public NodeWithPath2(Integer node, NodeWithPath2 parent, int len) {
        this.node = node;
        this.parent = parent;
        this.len = len;
    }
}
