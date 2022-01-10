package myanswers;

public class RangeSumBST {

    public int rangeSumBST(TreeNode root, int low, int high) {

        if (root == null) {
            return 0;
        }

        int sum = 0;
        if (root.val >= low && root.val <= high) {
            sum += root.val;
        }

        if (root.val > high || root.val >= low) {
            sum += rangeSumBST(root.left, low, high);
        }
        if (root.val < low || root.val <= high) {
            sum += rangeSumBST(root.left, low, high);
        }
        return sum;


    }
}
