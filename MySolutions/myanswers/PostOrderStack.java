package myanswers;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import CtCILibrary.TreeNode;

/**
 * Created by smridul on 3/6/19.
 */
public class PostOrderStack {

    @Test
    public void test() {
        BinaryTreeNode root = BinaryTree.createBinaryTree();


        BinaryTreeNode<Integer> root1 = new BinaryTreeNode<>();
        root1.data = 2;
        BinaryTree.insert(1, root1);
        List<Integer> list1 = postorderTraversal2(root1);



        List<Integer> list = postorderTraversal2(root);
        // 10 25 20 62 65 60 50 140 160 150 100


        for (Integer i : list) {
            System.out.print(i + " ");
        }
    }
/*
    public static List<Integer> postorderTraversal(BinaryTreeNode root) {
        List<Integer> result = new ArrayList<>();
        BinaryTreeNode node = root;
        BinaryTreeNode prev = null;
        Stack<BinaryTreeNode> stack = new Stack<>();
        while (!stack.empty() || node != null) {
            while (node != null && prev!=node.left) {
                stack.push(node);
                if (node.right != null) {
                    stack.push(node.right);
                }
                prev = node;
                node = node.left;
            }
            node = stack.pop();
            while (node==prev || prev == node.right ) {
                result.add((Integer) node.data);
                prev = node;
                node = stack.isEmpty() ? null : stack.pop();
            }
        }
        return result;
    }
    */

    public static List<Integer> postorderTraversal(BinaryTreeNode root) {

        Stack<BinaryTreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        BinaryTreeNode node = root;
        while (!stack.isEmpty() || node != null) {

            if (node != null) {
                if (node.returned == Returned.notpushed) {
                    // go to left
                    node.returned = Returned.left;
                    stack.push(node);
                    node = node.left;
                } else if (node.returned == Returned.left) {
                    node.returned = Returned.right;
                    stack.push(node);
                    node = node.right;
                } else if (node.returned == Returned.right) {
                    // 10 25 20 62 65 60 50 140 160 150 100
                    //System.out.print(node.data);
                    result.add((int) node.data);
                    node = stack.isEmpty() ? null : stack.pop();
                }
            } else {
                // node is null
                // pop
                node = stack.pop();
            }
        }
        return result;
    }

    public static List<Integer> postorderTraversal2(BinaryTreeNode root) {

        Stack<BinaryTreeNode> stack = new Stack<>();
        List<Integer> result = new ArrayList<>();
        BinaryTreeNode node = root;
        BinaryTreeNode prev = root;

        while (!stack.isEmpty() || node != null) {

            if(node == null){
                // immediate returning from right
                node = pop(stack);
                prev = node;
                System.out.print(node.data + " ");
                node = pop(stack);
            }
             else if (prev == node.left ) { //returning cases
                stack.push(node);
                prev = node;
                node = node.right;
            } else if (prev == node.right ) { //returning cases
                System.out.print(node.data + " ");
                prev = node;
                node = pop(stack);
            }else {
                while (node.left != null) {
                    stack.push(node);
                    node = node.left;
                }
                prev=node;
                stack.push(node);
                node = node.right;
            }
        }
        return result;
    }

    private static BinaryTreeNode<Integer> pop(Stack<BinaryTreeNode> stack){

        if (stack.isEmpty()){
            return null;
        }else{
            return stack.pop();
        }

    }
}

enum Returned {
    left,
    right,
    notpushed
}