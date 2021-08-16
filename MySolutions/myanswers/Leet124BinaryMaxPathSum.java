package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 1/15/19.
 */
public class Leet124BinaryMaxPathSum {




    @Test
    public void testInorder() {
        BinaryTreeNode root = createBinaryTree2();
        inorder(root);
        System.out.println();

        preorder(root);

        maxValue = Integer.MIN_VALUE;
        maxPathDown2(root);
        System.out.println();

        System.out.print( maxValue);

    }

    int maxValue;


    private int maxPathDown(BinaryTreeNode node) {
        if (node == null) return 0;
        int left = Math.max(0, maxPathDown(node.left));
        int right = Math.max(0, maxPathDown(node.right));
        maxValue = Math.max(maxValue, left + right + (int)node.data);
        return Math.max(left, right) + (int)node.data;
    }

    private int maxPathDown2(BinaryTreeNode node) {
        if (node == null) return 0;
        int left =  maxPathDown(node.left);
        int right = maxPathDown(node.right);
        int thisdata = (int)node.data;
        maxValue = Math.max(maxValue, Math.max(thisdata, Math.max(thisdata+left, Math.max(thisdata+ right, thisdata+left+right))));
        return Math.max(thisdata, Math.max(thisdata + right, thisdata+left));
    }

    public void inorder(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }

    BinaryTreeNode createBinaryTree() {
        BinaryTreeNode<Integer> node1 = new BinaryTreeNode<>();
        node1.data = 9;
        node1.left = null;
        node1.right = null;


        BinaryTreeNode<Integer> node2 = new BinaryTreeNode<>();
        node2.data = 15;
        node2.left = null;
        node2.right = null;

        BinaryTreeNode<Integer> node3 = new BinaryTreeNode<>();
        node3.data = 7;
        node3.left = null;
        node3.right = null;

        BinaryTreeNode<Integer> node4 = new BinaryTreeNode<>();
        node4.data = 20;
        node4.left = node2;
        node4.right = node3;

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>();
        root.data = 0;
        root.left = node1;
        root.right = node4;
        return root;
    }

    BinaryTreeNode createBinaryTree2() {
        BinaryTreeNode<Integer> node1 = new BinaryTreeNode<>();
        node1.data = 2;
        node1.left = null;
        node1.right = null;


        BinaryTreeNode<Integer> node2 = new BinaryTreeNode<>();
        node2.data = 3;
        node2.left = null;
        node2.right = null;


        BinaryTreeNode<Integer> root = new BinaryTreeNode<>();
        root.data = 1;
        root.left = node1;
        root.right = node2;
        return root;
    }

    public void preorder(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.data + " ");
        preorder(root.left);
        preorder(root.right);

    }

}
