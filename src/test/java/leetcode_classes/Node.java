package leetcode_classes;

public class Node {
    public int val;
    public int key;
    public Node left;
    public Node right;
    public int freqCount;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
