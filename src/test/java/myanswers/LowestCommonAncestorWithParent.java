package myanswers;

import javafx.util.Pair;
import org.junit.Test;

public class LowestCommonAncestorWithParent {

    public Node2 lowestCommonAncestor(Node2 p, Node2 q) {

        // first find root node
        Node2 root = p;
        while (root.parent != null) {
            root = root.parent;
        }

        Pair<Node2, Integer> pair = lowestCommonAncestorHelper(p, q, root);
        return pair.getKey();
    }

    public Pair<Node2, Integer> lowestCommonAncestorHelper(Node2 p, Node2 q, Node2 root) {
        if (root == null)
            return new Pair<>(null, 0);


        int totalCount = 0;
        Pair<Node2, Integer> lcount = lowestCommonAncestorHelper(p, q, root.left);
        if (lcount.getKey() != null) {
            return lcount;
        }
        totalCount = totalCount + lcount.getValue();
        if (root.val == p.val || root.val == q.val) {
            totalCount++;
        }

        if (totalCount == 2) {
            return new Pair<>(root, 2);
        }

        Pair<Node2, Integer> rcount = lowestCommonAncestorHelper(p, q, root.right);

        if (rcount.getKey() != null) {
            return rcount;
        }
        totalCount = totalCount + rcount.getValue();
        if (totalCount == 2) {
            return new Pair<>(root, 2);
        } else {
            return new Pair<>(null, totalCount);
        }
    }


    @Test
    public void test() {

        //Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
        Node2 three = new Node2(3);
        Node2 five = new Node2(5);
        Node2 one = new Node2(1);
        Node2 six = new Node2(6);
        Node2 two = new Node2(2);
        Node2 zero = new Node2(0);
        Node2 eight = new Node2(8);
        Node2 seven = new Node2(7);
        Node2 four = new Node2(4);

        two.left = seven;
        two.right = four;
        seven.parent = two;
        four.parent = two;

        five.left = six;
        five.right = two;
        six.parent = five;
        two.parent = five;

        one.left = zero;
        one.right = eight;
        zero.parent = one;
        eight.parent = one;

        three.left = five;
        three.right = one;
        five.parent = three;
        one.parent = three;

        Node2 ans = lowestCommonAncestor(five, one);

        System.out.println(ans.val);

    }
}

class Node2 {
    public int val;
    public Node2 left;
    public Node2 right;
    public Node2 parent;

    public Node2(int val) {
        this.val = val;
    }
}
