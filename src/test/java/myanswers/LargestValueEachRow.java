package myanswers;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LargestValueEachRow {

    public List<Integer> largestValues(TreeNode root) {

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        List<Integer> result = new ArrayList<>();
        while (!queue.isEmpty()){
            int size = queue.size();
            int max=Integer.MIN_VALUE;
            for(int i=0; i < size; i++) {
                TreeNode node = queue.poll();
                max = Math.max(max, node.val);
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            result.add(max);
        }


        return result;
    }
}
