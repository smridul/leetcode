package myanswers;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeIterator {

    Stack<TreeNode> stack = new Stack<>();
    List<Integer> list = new ArrayList<>();
    int pointer = -1;

    public BinaryTreeIterator(TreeNode root) {

        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    public boolean hasNext() {
        return pointer < list.size() - 1 || !stack.isEmpty();
    }

    public int next() {
        if (pointer < list.size() - 1) {
            pointer++;
            return list.get(pointer);
        } else {
            TreeNode node = stack.pop();
            TreeNode current = node.right;
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            list.add(node.val);
            pointer++;
            return node.val;
        }
    }

    public boolean hasPrev() {
        return pointer > 0;
    }

    public int prev() {
        pointer--;
        return list.get(pointer);
    }
}
