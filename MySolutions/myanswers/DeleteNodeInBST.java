package myanswers;

public class DeleteNodeInBST {

    //iterative version
    public TreeNode deleteNode(TreeNode root, int key) {

        if (root == null) {
            return null;
        }

        // step 1 find the node, save the parent
        TreeNode current = root;
        TreeNode parent = null;

        while (current != null) {
            if (current.val > key) {
                parent = current;
                current = current.left;
            } else if (current.val < key) {
                parent = current;
                current = current.right;
            } else {
                break;
            }
        }

        // we are at current

        if (parent == null) {
            return deleteRoot(current);
        } else if (parent.left == current) {
            parent.left = deleteRoot(current);
        } else {
            parent.right = deleteRoot(current);
        }

        return root;
    }

    TreeNode deleteRoot(TreeNode root) {
        if (root == null) { // no key found case
            return null;
        }

        if (root.left == null) {
            return root.right;
        }
        if (root.right == null) {
            return root.left;
        }

        TreeNode current = root.right;

        while (current.left != null) {
            current = current.left;
        }

        current.left = root.left;
        root.left = null;
        return root.right;
    }


    // recursive approach
    public TreeNode deleteNodeRecursive(TreeNode root, int key) {
        if (root == null) {
            return null;
        }

        TreeNode current = root;

        if (current.val > key) {
            current.left = deleteNodeRecursive(current.left, key);
        } else if (current.val < key) {
            current.right = deleteNodeRecursive(current.right, key);
        } else {
            if (current.left == null && current.right == null) {
                return null;
            }
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            } else {
                TreeNode successor = current.right;
                while (successor.left != null) {
                    successor = successor.left;
                }
                current.val = successor.val;
                current.right = deleteNodeRecursive(current.right, successor.val);
            }
        }

        return current;
    }


}
