package myanswers;

import org.junit.Test;

import java.util.Arrays;

public class BuildTreeFromPreInorder {
    public TreeNode buildTree(int[] preorder, int[] inorder) {

        return buildTree(preorder, inorder, 0,
                0, inorder.length - 1);

    }


    public TreeNode buildTree(int[] preorder, int[] inorder, int preLow,
                              int inorderLow, int inorderHigh) {
        int index = preLow;
        if (preLow > preorder.length-1) {
            return null;
        }
        if (inorderLow > inorderHigh) {
            return null;
        }
        int positionInInorder = search(inorder, preorder[index], inorderLow, inorderHigh);

        TreeNode root = new TreeNode(preorder[index]);

        int countOfLeftSubTree = positionInInorder - inorderLow;

        root.left = buildTree(preorder, inorder, preLow + 1,
                inorderLow, positionInInorder - 1);

        root.right = buildTree(preorder, inorder, preLow + countOfLeftSubTree + 1,
                positionInInorder + 1, inorderHigh);
        return root;
    }

    public int search(int num[], int target, int low, int high) {

        while (low <= high) {
           if(num[low] == target){
               return low;
           }
           low++;
        }
        return -1;
    }


    @Test
    public void test(){

        //preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
        int [] preorder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};

        TreeNode node = buildTree(preorder, inorder);
    }

}
