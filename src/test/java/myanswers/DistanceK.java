package myanswers;

import org.junit.Test;

import java.util.*;

public class DistanceK {

    Map<TreeNode, DistanceAndDirection> map = new HashMap<>();

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        List<Integer> result = new ArrayList<>();
        findPath(root, target);

        for(Map.Entry<TreeNode, DistanceAndDirection> entry :  map.entrySet()){

            TreeNode node = entry.getKey();
            DistanceAndDirection value = entry.getValue();

            if(k-value.distance == 0){
                result.add(node.val);
            } else if(k- value.distance > 0){
                if(value.direction == 0){
                    traverse(node, value.distance, k, result);
                } else if(value.direction == 1){
                    traverse(node.left, value.distance+1, k, result);
                }else if(value.direction == 2){
                    traverse(node.right, value.distance+1, k, result);
                }
            }
        }
        return result;
    }


    public void traverse(TreeNode root, int length, int K, List<Integer> result){

        if(root == null)return;
        if(length == K){
            result.add(root.val);
        }
        traverse(root.left, length+1,K, result);
        traverse(root.right, length+1,K, result);
    }

    int findPath(TreeNode root, TreeNode target){

        if(root == null) return -1;

        if(root == target){
            map.put(root, new DistanceAndDirection(0, 0));
            return 0;
        }

        int val = findPath(root.left, target);
        if(val>=0){
            map.put(root, new DistanceAndDirection(2, val+1));
            return val+1;
        }
         val = findPath(root.right, target);
        if(val>=0){
            map.put(root, new DistanceAndDirection(1, val+1));
            return val+1;
        }
        return -1;
    }


    @Test
    public void test(){

        TreeNode seven = new TreeNode(7);
        TreeNode four = new TreeNode(4);

        TreeNode two = new TreeNode(2);
        two.left = seven;
        two.right = four;

        TreeNode five = new TreeNode(5);
        five.right=two;

        TreeNode six = new TreeNode(6);
        five.left = six;

        TreeNode zero = new TreeNode(0);
        TreeNode eight = new TreeNode(8);

        TreeNode one = new TreeNode(1);
        one.left = zero;
        one.right = eight;

        TreeNode three = new TreeNode(3);
        three.left = five;
        three.right = one;

        List<Integer> res = distanceK(three, five, 2);
        int a=0;
    }
}



class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    int treeCount;

    TreeNode(int x) {
        val = x;
    }
}

class DistanceAndDirection{
    int distance;
    int direction; // 0 both,  1 left,  2 right
    DistanceAndDirection(int direction, int distance){
        this.direction = direction;
        this.distance = distance;
    }
}
