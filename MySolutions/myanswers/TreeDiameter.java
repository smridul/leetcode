package myanswers;


import org.junit.Test;

import java.util.*;

// for generic graph as well
public class TreeDiameter {

    int diameter;
    public int treeDiameter(int[][] edges) {

        Map<Integer, List<Integer>> nodeMap = new HashMap<>();

        for(int [] edge: edges){
            List<Integer> list1 = nodeMap.getOrDefault(edge[0], new ArrayList<>());
            list1.add(edge[1]);
            nodeMap.put(edge[0], list1);

            List<Integer> list2 = nodeMap.getOrDefault(edge[1], new ArrayList<>());
            list2.add(edge[0]);
            nodeMap.put(edge[1], list2);
        }


        // randomly pick node as root node, so i pick 0 node
        findDiameterRecursion(nodeMap, 0, new HashSet<>());
        return diameter;

    }

    int findDiameterRecursion( Map<Integer, List<Integer>> nodeMap, int node, Set<Integer> visited){

        visited.add(node);
        List<Integer> list = new ArrayList<>();
        for(int child: nodeMap.get(node)){
            if(!visited.contains(child)) {
                int len = findDiameterRecursion(nodeMap, child, visited);
                list.add(len);
            }
        }

        Collections.sort(list);

        int biggestChildLen = list.size()>=1? list.get(list.size()-1) +1 :0;
        int nextBiggestChildLen = list.size()>=2? list.get(list.size()-2) +1:0;
        int localdiameter = biggestChildLen + nextBiggestChildLen;
        diameter = Math.max(diameter, localdiameter);
        return biggestChildLen;
    }


    @Test
    public void test(){

        int[][] edges = new int[][]{
                {0,1},{0, 2}
        };

        System.out.println(treeDiameter(edges));
    }

}
