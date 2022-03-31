package myanswers.easy;

import leetcode_classes.TreeNode;
import org.junit.Test;

public class ClosestValueBST {

    @Test
    public void test(){


        System.out.println(Math.abs(3.714286 - 4));
        System.out.println(Math.abs(3.714286 - 2));
        System.out.println(Math.abs(3.714286 - 3));

    }
    public int closestValue(TreeNode root, double target) {

        int close = root.val;
        double min = Double.MAX_VALUE;
        while(root!=null){

            if(Math.abs(target - root.val) < min){
                close=root.val;
            }

            if(root.val > target){
                root = root.left;
            }else if(root.val > target){
                root = root.right;
            }else{
                return root.val;
            }
        }

        return close;


    }
}
