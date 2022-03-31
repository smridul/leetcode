package myanswers;

public class DiameterBinaryTree {

    int diameter;

    public int diameterOfBinaryTree(TreeNode root) {

        diameter = 0;
        if (root == null) {
            return 0;
        }

        recurse(root);
        return diameter;
    }

    public int recurse(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int l = recurse(root.left) + 1;
        int r = recurse(root.right) + 1;
        diameter = Math.max(diameter, l + r);
        return Math.max(l, r);
    }
}
