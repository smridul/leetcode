package myanswers;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

public class SerializeDeserializeBST {


    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {

        if (root == null) {
            return "";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder stringBuilder = new StringBuilder();

        while (!queue.isEmpty()) {

            int levelSize = queue.size();

            boolean anyNonNullFound = false;
            while (levelSize-- > 0) {
                TreeNode node = queue.poll();
                if (node == null) {
                    stringBuilder.append('N');
                    stringBuilder.append(',');
                } else {
                    stringBuilder.append(node.val);
                    stringBuilder.append(',');
                }
                if (node != null) {
                    queue.add(node.left);
                    queue.add(node.right);
                    if (node.left != null || node.right != null) {
                        anyNonNullFound = true;
                    }
                }else{
                    queue.add(null);
                    queue.add(null);
                }
            }
            if (!anyNonNullFound) {
                break;
            }
        }
        return stringBuilder.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        String[] tokens = data.split(",");
        return deserializeHelper(tokens, 0);

    }

    public TreeNode deserializeHelper(String[] tokens, int index) {

        if (index >= tokens.length) {
            return null;
        }
        if (tokens[index] == null || tokens[index].isEmpty() || tokens[index].equals("N")) {
            return null;
        }

        int value = Integer.parseInt(tokens[index]);
        TreeNode node = new TreeNode(value);
        node.left = deserializeHelper(tokens, (index + 1) * 2 - 1);
        node.right = deserializeHelper(tokens, (index + 1) * 2);
        return node;
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




       /* TreeNode seven = new TreeNode(7);
        TreeNode four = new TreeNode(4);

        TreeNode two = new TreeNode(2);
        two.left = seven;
        two.right = four;

        TreeNode five = new TreeNode(5);
        five.right=two;

        TreeNode six = new TreeNode(6);
        five.left = six;

        TreeNode zero = new TreeNode(0);
        TreeNode eight = new TreeNode(8);

        TreeNode one = new TreeNode(1);
        one.left = zero;
        one.right = eight;

        TreeNode three = new TreeNode(3);
        three.left = five;
        three.right = one; */

        String s = serialize(one);
        System.out.println(s);

    }




}
