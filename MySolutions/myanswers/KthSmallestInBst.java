package myanswers;

public class KthSmallestInBst {

    int i;

    public int kthSmallest(TreeNode root, int k) {
        i = 0;
        return kthSmallestHelper(root, k).val;
    }

    public TreeNode kthSmallestHelper(TreeNode root, int k) {

        if(root == null){
            return null;
        }
        TreeNode node = kthSmallestHelper(root.left, k);
        if (node != null) {
            return node;
        }

        i++;
        if (i == k) {
            return root;
        }

        node = kthSmallestHelper(root.right, k);
        if (node != null) {
            return node;
        }
        return null;
    }


    public  TreeNode insert(TreeNode root, int key) {

        if (root == null) {
            root = new TreeNode(key);
            root.treeCount = 0;
            return root;
        }

        if (key < root.val) {
            TreeNode node = insert(root.left, key);
            root.left = node;
            root.treeCount++;
        } else if (key >  root.val) {
            TreeNode node = insert(root.right, key);
            root.right = node;
            root.treeCount++;
        }
        return root;
    }

   //

    public  TreeNode delete(TreeNode root, int key) {

        if (root == null) {
            root = new TreeNode(key);
            root.treeCount = 0;
            return root;
        }

        if (key < root.val) {
            TreeNode node = insert(root.left, key);
            root.left = node;
            root.treeCount++;
        } else if (key >  root.val) {
            TreeNode node = insert(root.right, key);
            root.right = node;
            root.treeCount++;
        }
        return root;
    }
}
