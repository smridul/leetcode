package myanswers;

import java.util.*;

import javafx.util.Pair;
import org.junit.Test;

public class VerticalOrder {

    public List<List<Integer>> verticalOrder(TreeNode root) {

        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<Pair<TreeNode, Integer>> queue = new LinkedList<>();
        queue.add(new Pair<>(root, 0));

        Map<Integer, List<Integer>> map = new HashMap<>();

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                Pair<TreeNode, Integer> pair = queue.poll();

                List<Integer> indexList = map.getOrDefault(pair.getValue(), new ArrayList<>());
                indexList.add(pair.getKey().val);
                map.put(pair.getValue(), indexList);

                if (pair.getKey().left != null) {
                    queue.add(new Pair<>(pair.getKey().left, pair.getValue() - 1));
                }
                if (pair.getKey().right != null) {
                    queue.add(new Pair<>(pair.getKey().right, pair.getValue() + 1));
                }
            }
        }
        List<Map.Entry<Integer, List<Integer>>> maplist = new ArrayList<>();
        for(Map.Entry<Integer, List<Integer>> entry : map.entrySet()){
            maplist.add(entry);
        }

        Collections.sort(maplist, new Comparator<Map.Entry<Integer, List<Integer>>>() {
            @Override
            public int compare(Map.Entry<Integer, List<Integer>> o1, Map.Entry<Integer, List<Integer>> o2) {
                return o1.getKey() - o2.getKey();
            }
        });


        for(Map.Entry<Integer, List<Integer>> element: maplist){
            result.add(element.getValue());
        }
        return result;
    }

    @Test
    public void test() {
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode thre = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);
        TreeNode eight = new TreeNode(8);
        TreeNode nine = new TreeNode(9);
        TreeNode ten = new TreeNode(10);

        one.left = two;
        one.right = thre;

        two.left = four;
        two.right = null;

        four.left = seven;
        four.right = eight;

        thre.left = five;
        thre.right = six;

        five.left = nine;
        five.right = ten;

        verticalOrder(one);
    }
}
