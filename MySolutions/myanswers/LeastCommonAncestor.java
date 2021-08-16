package myanswers;

import CtCILibrary.TreeNode;

public class LeastCommonAncestor {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return lca(root, p, q).node;
    }

    public NodeAndCount lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return new NodeAndCount(null,0);
        int thisNodeCount = 0;
        if (root.data == p.data || root.data == q.data) {
            thisNodeCount++;
        }

        NodeAndCount lnode = lca(root.left, p, q);

        if (lnode.node != null) {
            return lnode;
        } else if (lnode.count + thisNodeCount == 2) {
            return new NodeAndCount(root, 2);
        }

        NodeAndCount  rnode = lca(root.right, p, q);
        if (rnode.node != null) {
            return rnode;
        } else if (rnode.count + thisNodeCount + lnode.count == 2) {
            return new NodeAndCount(root, 2);
        }

        return new NodeAndCount(null, thisNodeCount+ rnode.count+ lnode.count);
    }

}

class NodeAndCount {
    TreeNode node;
    int count;

    public NodeAndCount(TreeNode node, int count) {
        this.count = count;
        this.node = node;
    }
}
