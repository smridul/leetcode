package myanswers;

import org.junit.Test;

import static myanswers.BinaryTree.inorder;
import static myanswers.BinaryTree.insert;

/**
 * Created by smridul on 1/19/19.
 */
public class InorderTraversalWithoutStack {

    @Test
    public void test() {

        BinaryTreeNode tree = createBinaryTree();
        BinaryTree.inorder(tree);
        System.out.println();
        inorder(tree);
        populateNext(tree, null, true);
        System.out.println();
        inorder(tree);
    }

    BinaryTreeNode createBinaryTree() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>();
        root.data = 100;

        insert(50, root);
        insert(20, root);
       // insert(10, root);
       // insert(25, root);
        insert(60, root);
       // insert(52, root);
      //  insert(65, root);
       // insert(62, root);
        insert(150, root);
        insert(140, root);
        insert(160, root);

        return root;
    }

    public void inorder(BinaryTreeNode root) {

        BinaryTreeNode current = root;
        BinaryTreeNode prev = null;

        while (current != null ) {

            if (prev == current.parent) {
                // GO LEFT
                while (current.left != null) {
                    current = current.left;
                }
                System.out.print(current.data + " ");


                prev = current;
                current = current.right != null ? current.right : current.parent;
            } else if( prev == current.left){
                System.out.print(current.data + " ");
                // GO RIGHT // if right null go up
                prev = current;
                current = current.right!=null ?current.right: current.parent;
            } else{
                // prev == current.right
                // GO UP only
                prev = current;
                current = current.parent;
            }
        }
    }

    public  BinaryTreeNode goRight(BinaryTreeNode current){
        if (current.right != null) {
            current = current.right;
        } else {
            while (current.parent != null && current == current.parent.right) {
                current = current.parent;
            }
            if (current.parent != null) {
                // one more up
                current = current.parent;
            }
        }
        return current;
    }


    // use parent for next field
    public void populateNext(BinaryTreeNode root, BinaryTreeNode parent, boolean isleft) {

        if(root!=null){
            if(parent!=null) {
                if (isleft) {
                    root.next = parent.right;
                } else {
                    root.next = parent.next!=null?parent.next.left:null;
                }
            }
            populateNext(root.left, root, true);
            populateNext(root.right, root, false);
        }
    }

}


