package myanswers;

import java.util.ArrayList;
import java.util.List;

public class BalanceBST {

    public TreeNode balanceBST(TreeNode root) {
        List<Integer> sorted = new ArrayList<>();
        inorder(root, sorted);
        return balanceSorted(sorted, 0, sorted.size()-1);
    }

    public TreeNode balanceSorted(List<Integer> sorted, int low, int high) {
        if(low > high){
            return null;
        }
        int mid = low + (high-low)/2;
        TreeNode root = new TreeNode(sorted.get(mid));
        root.left = balanceSorted(sorted, low, mid-1);
        root.right = balanceSorted(sorted, mid+1, high);
        return root;
    }


    void inorder(TreeNode root, List<Integer> res){
        if(root == null){
            return;
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
    }
}
