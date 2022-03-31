package myanswers;


import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// old class
public class Leet_315_Smaller_Count {


    @Test
    public void test(){

        int a[] = new int []{99,51,9,21,84,66,65,36,100,41};
       // int a[] = new int []{5,2,6,1};
        List<Integer> b = countSmaller(a);
        for (Integer c :b){
            System.out.print(c + " " );
        }
    }

    public List<Integer> countSmaller(int[] nums) {

        if(nums.length == 1) return Arrays.asList(0);

        LinkedList count = new LinkedList();

        BinaryNode root = null;

        for(int i =nums.length-1 ; i >=0; i--) {
            IntWrap smallCount = new IntWrap(0) ;
            root = insert(root,nums[i], smallCount);
            count.addFirst(smallCount.data);
        }
        return count;
    }



    BinaryNode insert(BinaryNode root, int key, IntWrap smallCount) {

        /* If the tree is empty, return a new node */
        if (root == null) {
            root = new BinaryNode();
            root.data = key;
            root.leftCount = 0;
            return root;
        }
        /* Otherwise, recur down the tree */
        if (key <  root.data) {
            BinaryNode node = insert(root.left, key, smallCount);
            root.leftCount++;
            root.left = node;
        } else if (key >  root.data) {
            smallCount.data = smallCount.data  + root.leftCount +1;
            BinaryNode node = insert(root.right, key, smallCount);
            root.right = node;
        } else if (key ==  root.data) {
            // always add equal element to the right
            smallCount.data = smallCount.data +  root.leftCount;
            BinaryNode node = insert(root.right, key, smallCount);
            root.right = node;
        }

        /* return the (unchanged) node pointer */
        return root;
    }
}

class BinaryNode {
    BinaryNode left, right;
    int  data;
    int leftCount;
}
class IntWrap {
    public IntWrap(int m) {
        data = m;
    }
    public int data;
}
