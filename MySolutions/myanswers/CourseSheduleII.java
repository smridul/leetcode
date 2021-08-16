package myanswers;

import org.junit.Test;

import java.util.*;

public class CourseSheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {

        int outdegree[] = new int[numCourses];
        Map<Integer, List> map = new HashMap<>();
        for (int[] edge : prerequisites) {
            outdegree[edge[0]]++;
            if (map.containsKey(edge[1])) {
                map.get(edge[1]).add(edge[0]);
            } else {
                List<Integer> list= new ArrayList<>();
                list.add(edge[0]);
                map.put(edge[1], list);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int i =0; i< outdegree.length; i++) {
            if (outdegree[i] == 0) {
                queue.offer(i);
            }
        }

        int[] result = new int[numCourses];
        int index = 0;
        while (!queue.isEmpty()) {
            int node = queue.poll();
            result[index] = node;
            index++;

            List<Integer> neighbours = map.get(node);
            for (int i = 0; neighbours != null && i < neighbours.size(); i++) {
                int neighbourNode = neighbours.get(i);
                outdegree[neighbourNode]--;
                if (outdegree[neighbourNode] == 0) {
                    queue.offer(neighbourNode);
                }
            }
        }

        if (index < numCourses) {
            return new int[0];
        } else {
            return result;
        }
    }

    @Test
    public void test(){
        int[][] edges= new int[][]{ {1, 0}};
        findOrder(2, edges);

    }
}
