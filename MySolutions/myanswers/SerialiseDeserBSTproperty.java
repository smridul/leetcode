package myanswers;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerialiseDeserBSTproperty {

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    public void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        } else {
            sb.append(root.val).append(",");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }
    }


    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserialize(queue, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }


    public TreeNode deserialize(Queue<String> queue, int min, int max) {
        if (queue.isEmpty()) {
            return null;
        }
        int val = Integer.parseInt(queue.peek());
        if (val < min || val > max) {
            return null;
        }

        queue.poll();

        TreeNode node = new TreeNode(val);
        node.left = deserialize(queue, min, val);
        node.right = deserialize(queue, val, max);
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

        String s = serialize(one);
        System.out.println(s);

        TreeNode node = deserialize(s);
        System.out.println(serialize(node));
    }

}
