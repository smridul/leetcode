package myanswers;

import java.util.*;
import javafx.util.Pair;


public class PreorderTraversal {


    // version 1 recursion unfolding
    public List<Integer> preorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        TreeNode curr = root;
        while (curr!=null || !stack.isEmpty()) {
            while (curr != null) {
                res.add(curr.val);
                stack.push(new Pair<>(curr, 1));
                curr = curr.left;
            }
            if(stack.isEmpty()){
                return res;
            }
            Pair<TreeNode, Integer> pair = stack.pop();
            curr = pair.getKey();

            if(pair.getValue() == 1){
                stack.push(new Pair<>(curr, 2));
                curr = curr.right;
            }
            if(pair.getValue() == 2){
                // do nothing
                curr = null;
            }
        }
        return res;
    }



    // optimized
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                res.add(curr.val);
                stack.push(curr);
                curr = curr.left;
            }
            curr = stack.pop();

            curr = curr.right;
        }
        return res;
    }
}
