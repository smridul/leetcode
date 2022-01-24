package myanswers;

import java.util.*;
import javafx.util.Pair;
import org.junit.Test;

public class PostOrderTraversal {


    //verison 1
    public List<Integer> postorderTraversal1(TreeNode root) {
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
                curr = pair.getKey();
                stack.push(new Pair<>(curr, 2));
                curr = curr.right;
            }
            if(pair.getValue() == 2){
                res.add(curr.val);
                curr = null;

            }
        }
        return res;
    }


    public List<Integer> postorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            while (curr != null) {
                res.addFirst(curr.val);
                stack.push(curr);
                curr = curr.right;
            }
            curr = stack.pop();

            curr = curr.left;
        }
        return res;
    }



    //verison 3
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        boolean simulatePop = false;
        while (curr!=null || !stack.isEmpty()) {
            while (!simulatePop && !isLeaf(curr)) {
                stack.push(curr);
                curr = curr.left;
            }
            if(curr!=null && isLeaf(curr)){
                res.add(curr.val);
            }
            simulatePop = false;
            if(stack.isEmpty()){
                return res;
            }

            if(stack.peek().left == curr){
                curr = stack.pop();
                stack.push(curr);
                // actually above operations means noop
                curr = curr.right;
            } else{
                curr = stack.pop();
                res.add(curr.val);
                simulatePop=true;
            }
        }
        return res;
    }

    private boolean isLeaf(TreeNode r) {
        if (r == null) return true;
        return r.left == null && r.right == null;
    }


    @Test
    public  void test(){
        TreeNode one = new TreeNode(1);
        TreeNode two = new TreeNode(2);
        TreeNode three = new TreeNode(3);

        one.left = two;
        two.left = three;
        List<Integer> list = postorderTraversal2(one);
        int a =0;
    }

}
