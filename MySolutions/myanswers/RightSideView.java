package myanswers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class RightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            dfs(root, 0, result);
        }
        return result;
    }

    private void dfs(TreeNode node, int level, List<Integer> result) {
        if (level == result.size()) {
            result.add(node.val);
        }

        if (node.right != null) {
            dfs(node.right, level + 1, result);
        }
        if (node.left != null) {
            dfs(node.left, level + 1, result);
        }
    }

    public List<Integer> rightSideViewUsingBfs(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.add(root);
        }

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (i == 0) {
                    result.add(node.val);
                }

                if (node.right != null) {
                    queue.add(node.right);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
            }
        }
        return result;
    }

}
