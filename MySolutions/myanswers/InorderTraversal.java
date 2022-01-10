package myanswers;

import javafx.util.Pair;
import org.junit.Test;

import java.util.*;
import leetcode_classes.TreeNode;

public class InorderTraversal {


    @Test
    public void test() {

    }

    // version 1 using recursion unfolding method
    public List<Integer> inorder(TreeNode root) {

        List<Integer> res = new ArrayList<>();
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        TreeNode curr = root;
        while (curr!=null || !stack.isEmpty()) {
            while (curr != null) {
                stack.push(new Pair<>(curr, 1));
                curr = curr.left;
            }
            if(stack.isEmpty()){
                return res;
            }
            Pair<TreeNode, Integer> pair = stack.pop();
            curr = pair.getKey();


            if(pair.getValue() == 1){
                res.add(curr.val);
                stack.push(new Pair<>(curr, 2));
                curr = curr.right;
            }
            if(pair.getValue() == 2){
               // do nothing, simulate pop
                curr = null;
            }
        }
        return res;
    }

    //version 2 optimized
    public List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (true) {
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }
            if(stack.isEmpty()){
                return res;
            }
            curr = stack.pop();
            res.add(curr.val);
            curr = curr.right;
        }
        // return res;
    }
}
