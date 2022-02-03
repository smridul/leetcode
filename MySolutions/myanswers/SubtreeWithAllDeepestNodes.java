package myanswers;

import java.util.*;

public class SubtreeWithAllDeepestNodes {
    public TreeNode subtreeWithAllDeepest(TreeNode root) {

        Queue<TreeNode> q = new LinkedList<>();


        q.offer(root);

        Set<TreeNode> set = new HashSet<>();
        while (!q.isEmpty()){
            int size = q.size();

            List<TreeNode> list = new ArrayList<>();
            boolean found = false;
            for(int i=0; i<size ; i++){

                TreeNode node = q.poll();

                if(node.left!=null){
                    q.offer(node.left);
                    found = true;
                    list.add(node.left);
                }
                if(node.right!=null){
                    q.offer(node.right);
                    found = true;
                    list.add(node.right);
                }
            }


            if(!found){
                set = new HashSet<>(list);
                break;
            }
        }

        return lca(root, set).node;

    }

    public NodeAndCount lca(TreeNode root, Set<TreeNode> set) {
        if (root == null) return new NodeAndCount(null,0);
        int thisNodeCount = 0;
        if (set.contains(root)) {
            thisNodeCount++;
        }

        NodeAndCount lnode = lca(root.left, set);

        if (lnode.node != null) {
            return lnode;
        } else if (lnode.count + thisNodeCount == set.size()) {
            return new NodeAndCount(root, set.size());
        }

        NodeAndCount  rnode = lca(root.right, set);
        if (rnode.node != null) {
            return rnode;
        } else if (rnode.count + thisNodeCount + lnode.count == set.size()) {
            return new NodeAndCount(root, set.size());
        }

        return new NodeAndCount(null, thisNodeCount+ rnode.count+ lnode.count);
    }
}
