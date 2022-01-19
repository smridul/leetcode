package myanswers;

public class InorderSuccessor {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(p==null || root==null){
            return null;
        }
        // step 1 find the node, save the potential answer
        TreeNode current  = root;
        TreeNode parent = null;
        while(current !=null){
            if(current.val > p.val){
                parent = current;
                current = current.left;
            }else if(current.val < p.val){
                current = current.right;
            }else{
                // check if p has right
                if(current.right ==null){
                    return parent;
                }
                break;
            }
        }

        // if p not present then return null
        if(current==null){
            return null;
        }

        //current is found but right is there
        current = current.right;

        while(current.left!=null){
            current = current.left;
        }

        return current;

    }
}
