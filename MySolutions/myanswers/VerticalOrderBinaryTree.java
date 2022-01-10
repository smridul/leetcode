package myanswers;

import javafx.util.Pair;

import java.util.*;

public class VerticalOrderBinaryTree {

    public List<List<Integer>> verticalOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<javafx.util.Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new javafx.util.Pair<>(root, 0));

        Map<Integer, List<Integer>> map = new HashMap<>();
        int min = 0;
        int max = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            Map<Integer, List<Integer>> innerMap = new HashMap<>();
            for (int i = 0; i < levelSize; i++) {
                javafx.util.Pair<TreeNode, Integer> pair = queue.poll();

                List<Integer> indexList = innerMap.getOrDefault(pair.getValue(), new ArrayList<>());

                min = Math.min(min, pair.getValue());
                max = Math.max(max, pair.getValue());

                indexList.add(pair.getKey().val);
                innerMap.put(pair.getValue(), indexList);

                if (pair.getKey().left != null) {
                    queue.add(new javafx.util.Pair<>(pair.getKey().left, pair.getValue() - 1));
                }
                if (pair.getKey().right != null) {
                    queue.add(new Pair<>(pair.getKey().right, pair.getValue() + 1));
                }
            }
            //sort the inner map lists
            for (Map.Entry<Integer, List<Integer>> entry : innerMap.entrySet()) {
                List<Integer> list = entry.getValue();
                Collections.sort(list);
                List<Integer> outerList = map.getOrDefault(entry.getKey(), new ArrayList<>());
                outerList.addAll(list);
                map.put(entry.getKey(), outerList);
            }
        }

        for (int i = min; i <= max; i++) {
            result.add(map.get(i));
        }
        return result;
    }

}
