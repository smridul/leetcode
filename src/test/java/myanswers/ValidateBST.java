package myanswers;

public class ValidateBST {

    public boolean isValidBST(TreeNode root) {

        long min = (long)Integer.MIN_VALUE -1;
        long max = (long)Integer.MAX_VALUE +1;

        return isValidBST(root, min, max);
    }

    public boolean isValidBST(TreeNode root, long min, long max) {

        if(root == null){
            return true;
        }

        if(root.val < min || root.val > max){
            return false;
        }

        if(!isValidBST(root.left, min, root.val)){
            return false;
        }

        if(!isValidBST(root.right, root.val, max)){
            return false;
        };

        return true;
    }
}
