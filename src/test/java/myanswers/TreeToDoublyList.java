package myanswers;

import leetcode_classes.Node;
import org.junit.Test;


public class TreeToDoublyList {
    Node lastNode;

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Node first = recursion(root);
        first.left = lastNode;
        lastNode.right = first;
        return first;
    }

    public Node recursion(Node root) {
        if(root == null){
            return null;
        }

        Node first = recursion(root.left);
        root.left = lastNode;
        if(lastNode!=null){
            lastNode.right = root;
        }
        lastNode = root;
        recursion(root.right);
        return first==null ? root:first;
    }

    @Test
    public void tes(){

        Node two = new Node(2);
        Node one = new Node(1);
        Node three = new Node(3);
        Node four = new Node(4);
        Node five = new Node(5);



        two.left = one;
        two.right = three;

        four.right =five;
        four.left = two;

        Node node = treeToDoublyList(four);
        System.out.println(node.val);
    }
}
