package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 3/8/19.
 */
public class SerializeTree {
    @Test
    public void test() {


        BinaryTreeNode<Integer> root = new BinaryTreeNode<>();
        root.data = 5;
        BinaryTree.insert(2, root);
        BinaryTree.insert(4, root);
        BinaryTree.insert(3, root);
        System.out.println(serialize(root));
        BinaryTreeNode<Integer> deserializedRoot = deserialize(serialize(root));
        System.out.println(serialize(deserializedRoot));

    }


    public String serialize(BinaryTreeNode root) {

        String res = null;
        if (root != null) {
            res = String.valueOf(root.data);

        } else {
            res = "null";
            return res;
        }

        String left = serialize(root.left);
        String right = serialize(root.right);
        return res + "," + left + "," + right;
    }

    // Decodes your encoded data to tree.
    public BinaryTreeNode deserialize(String data) {

        IntWrap intWrap = new IntWrap(0);
        return deserializeHelper(data.split(","), intWrap);
    }

    public BinaryTreeNode deserializeHelper(String[] data,  IntWrap intWrap) {

        BinaryTreeNode<Integer> root = new BinaryTreeNode<>();
        if(intWrap.data < data.length) {
            if (data[intWrap.data].equals("null")) {
                intWrap.data++;
                return null;
            } else {
                root.data = Integer.parseInt(data[intWrap.data]);
                intWrap.data++;
            }
            root.left = deserializeHelper(data, intWrap);
            root.right = deserializeHelper(data, intWrap);
        }
        return root;
    }


}
