package myanswers;


import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;

import static myanswers.BinaryTree.insert;

public class BstSequences {

    @Test
    public void test() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>();
       /* root.data = 10;

        insert(5, root);
        insert(15, root);
        insert(2, root);
        insert(8, root);
        insert(12, root);
        insert(20, root);
        insert(1, root);
        insert(3, root);
        insert(6, root);
        insert(9, root);
        */

        root.data = 100;

        insert(100, root);
        insert(50, root);
        insert(20, root);
        insert(75, root);
        insert(150, root);
        insert(120, root);
        insert(170, root);


        ArrayList<LinkedList<Integer>> results=  createSequence(root);

        for(LinkedList<Integer> result: results){


            for(int a: result){
                System.out.print(a + " ");
            }
            System.out.println();
        }
        System.out.println("Total size is " + results.size());

    }

    public ArrayList<LinkedList<Integer>> createSequence(BinaryTreeNode root) {

        if (root == null) {
            LinkedList<Integer> emptyList = new LinkedList<>();
            ArrayList<LinkedList<Integer>> res = new ArrayList<>();
            res.add(emptyList);
            return res;
        }


        int firstElement = (int) root.data;

        ArrayList<LinkedList<Integer>> leftseqs = createSequence(root.left);

        ArrayList<LinkedList<Integer>> rightseqs = createSequence(root.right);

        ArrayList<LinkedList<Integer>> result = new ArrayList<>();

        for (LinkedList<Integer> left : leftseqs) {
            for (LinkedList<Integer> right : rightseqs) {
                LinkedList<Integer> temp = new LinkedList<>();
                temp.addFirst(firstElement);
                weaveLists(left, right, result, temp);
            }
        }

        return result;
    }

    public static void weaveLists(LinkedList<Integer> first, LinkedList<Integer> second, ArrayList<LinkedList<Integer>> results, LinkedList<Integer> prefix) {
		/* One list is empty. Add the remainder to [a cloned] prefix and
		 * store result. */
        if (first.size() == 0 || second.size() == 0) {
           // LinkedList<Integer> result = (LinkedList<Integer>) prefix.clone();
            LinkedList<Integer> result = new LinkedList<>(prefix);

            result.addAll(first);
            result.addAll(second);
            results.add(result);
            return;
        }


        try {
            // select from first
            int firstSelect = first.getFirst();
            prefix.addLast(firstSelect);
            first.removeFirst();

            weaveLists(first, second, results, prefix);

            //undo what is done
            prefix.removeLast();
            first.addFirst(firstSelect);


            // select from second
            int secondSelect = second.getFirst();
            prefix.addLast(secondSelect);
            second.removeFirst();
            weaveLists(first, second, results, prefix);

            //undo
            prefix.removeLast();
            second.addFirst(secondSelect);
        }catch (Exception e){
            System.out.print("");
        }
    }
}