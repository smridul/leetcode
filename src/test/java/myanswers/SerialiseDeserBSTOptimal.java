package myanswers;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerialiseDeserBSTOptimal {


    // not using bst property
    public String serializeOptimal(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serialize(root, sb);
        return sb.toString();
    }

    public void serialize(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#").append(",");
        } else {
            sb.append(root.val).append(",");
            serialize(root.left, sb);
            serialize(root.right, sb);
        }
    }


    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserialize(queue);
    }


    public TreeNode deserialize(Queue<String> queue) {
        String val = queue.poll();

        if (val.equals("#")) {
            return null;
        } else {
            TreeNode node = new TreeNode(Integer.parseInt(val));
            node.left = deserialize(queue);
            node.right = deserialize(queue);
            return node;
        }
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

        String s = serializeOptimal(one);
        System.out.println(s);

        TreeNode node = deserialize(s);
        System.out.println(serializeOptimal(node));
    }


}
