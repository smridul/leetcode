package myanswers;


import org.junit.Test;

import java.util.*;

public class BinaryTree {

    @Test
    public void testInorder() {
        BinaryTreeNode root = createBinaryTree();
        inorder(root);
        System.out.println();
        inorderIterative(root);
        System.out.println();
        preorderIterative(root);
        System.out.println();
        preorderStack(root);
        System.out.println();
        postorderIterative(root);
        System.out.println();
        postorderIterative2(root);
        System.out.println();
        postorderStack(root);
    }


    @Test
    public void testIsBst() {
        BinaryTreeNode root = createBinaryTree();
       // inorder(root);


        BinaryTreeNode<Integer> node1 = new BinaryTreeNode();
        node1.data = 1;
        BinaryTreeNode<Integer> node2 = new BinaryTreeNode();
        node2.left = node1;
        node2.data = 5;


        BinaryTreeNode<Integer> node3 = new BinaryTreeNode();
        node3.data =4;
        node2.right = node3;

        BinaryTreeNode<Integer> node4 = new BinaryTreeNode();
        node4.data = 3;
        node3.left = node4;

        BinaryTreeNode<Integer> node5 = new BinaryTreeNode();
        node5.data =6;
        node3.right = node5;


        inorder(node2);


        System.out.print(isBst(node2).isBst);

    }

    Result isBst(BinaryTreeNode root) {


        Result resultLeft = new Result();
        Result resultRight = new Result();


        if(root!=null && root.left!=null) {
            resultLeft = isBst(root.left);
        }

        if(!resultLeft.isBst){
            return resultLeft;
        }

        if(root!=null && root.right!=null) {
            resultRight = isBst(root.right);
        }

        if(!resultRight.isBst){
            return resultRight;
        }

        if (!(resultLeft.max <= (Integer) root.data && resultRight.min >= (Integer) root.data)) {
            Result falseRes  =new Result();
            falseRes.isBst = false;
            return falseRes;
        }

        Result result = new Result();
        result.max = Math.max(resultRight.max, ((Integer) root.data).intValue());
        result.min = Math.min(resultLeft.min, ((Integer) root.data).intValue());
        return  result;

    }

    public static void inorder(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }


    public void inorderIterative(BinaryTreeNode root) {

        BinaryTreeNode prev = null;
        BinaryTreeNode curr = root;

        while (curr != null) {
            BinaryTreeNode next;

            if (curr.parent == prev) {
                if (curr.left != null) {
                    next = curr.left;
                } else {
                    System.out.print(curr.data + " ");
                    next = (curr.right != null) ? curr.right : curr.parent;
                }
            } else if (curr.left == prev) {
                System.out.print(curr.data + " ");
                next = (curr.right != null) ? curr.right : curr.parent;
            } else {
                next = curr.parent;
            }

            prev = curr;
            curr = next;
        }

    }

    public void postorderIterative(BinaryTreeNode root) {
        BinaryTreeNode prev = null;
        BinaryTreeNode curr = root;

        while (curr != null) {
            BinaryTreeNode next;
            if (curr.parent == prev) {
                if (curr.left != null) {
                    next = curr.left;
                } else {
                    if (curr.right == null) {
                        System.out.print(curr.data + " ");
                    }
                    next = (curr.right != null) ? curr.right : curr.parent;

                }
            } else if (curr.left == prev) {
                if (curr.right == null) {
                    System.out.print(curr.data + " ");
                }
                next = (curr.right != null) ? curr.right : curr.parent;
            } else {
                System.out.print(curr.data + " ");
                next = curr.parent;
            }

            prev = curr;
            curr = next;
        }
    }

    public void postorderIterative2(BinaryTreeNode root) {
        BinaryTreeNode prev = null;
        BinaryTreeNode curr = root;

        while (curr != null) {
            BinaryTreeNode next;
            if (curr.parent == prev) {
                if (curr.left != null) {
                    next = curr.left;
                } else {
                    next = (curr.right != null) ? curr.right : curr.parent;
                }
            } else if (curr.left == prev) {
                System.out.print(prev.data + " ");
                next = (curr.right != null) ? curr.right : curr.parent;
            } else {
                System.out.print(prev.data + " ");
                next = curr.parent;
            }
            prev = curr;
            curr = next;
        }
        System.out.print(prev.data + " ");

    }

    public void preorderIterative(BinaryTreeNode root) {
        BinaryTreeNode prev = null;
        BinaryTreeNode curr = root;

        while (curr != null) {
            BinaryTreeNode next;
            if (curr.parent == prev) {
                System.out.print(curr.data + " ");
                if (curr.left != null) {
                    next = curr.left;
                } else {
                    next = (curr.right != null) ? curr.right : curr.parent;
                }
            } else if (curr.left == prev) {
                next = (curr.right != null) ? curr.right : curr.parent;
            } else {
                next = curr.parent;
            }

            prev = curr;
            curr = next;
        }
    }


    public void preorderStack(BinaryTreeNode root) {
        Deque<BinaryTreeNode> stack = new ArrayDeque<>();
        BinaryTreeNode p = root;
        List<Integer> result = new ArrayList<>();

        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                result.add((Integer) p.data);
                if (p.right != null) {
                    stack.push(p.right);
                }
                p = p.left;

            } else {
                p = stack.pop();
            }
        }

        for (Integer i : result) {
            System.out.print(i.intValue() + " ");
        }
    }

    public void postorderStack(BinaryTreeNode root) {
        Deque<BinaryTreeNode> stack = new ArrayDeque<>();
        LinkedList<Integer> result = new LinkedList<>();
        BinaryTreeNode p = root;
        while (!stack.isEmpty() || p != null) {
            if (p != null) {
                result.addFirst((Integer) p.data);

                if (p.left != null) {
                    stack.push(p.left);
                }
                p = p.right;

            } else {
                p = stack.pop();
            }
        }

        for (Integer i : result) {
            System.out.print(i.intValue() + " ");
        }
    }

    static BinaryTreeNode createBinaryTree() {
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>();
        root.data = 100;

        insert(50, root);
        insert(20, root);
        insert(10, root);
        insert(25, root);
        insert(60, root);
        insert(65, root);
        insert(62, root);
        insert(150, root);
        insert(140, root);
        insert(160, root);

        return root;
    }

    public static void insert(int key, BinaryTreeNode root) {
        insertRec(root, key);
    }

    public static BinaryTreeNode insertRec(BinaryTreeNode root, int key) {

        /* If the tree is empty, return a new node */
        if (root == null) {
            root = new BinaryTreeNode();
            root.data = key;
            return root;
        }

        /* Otherwise, recur down the tree */
        if (key < (Integer) root.data) {
            BinaryTreeNode node = insertRec(root.left, key);
            root.left = node;
            node.parent = root;
        } else if (key > (Integer) root.data) {
            BinaryTreeNode node = insertRec(root.right, key);
            root.right = node;
            node.parent = root;
        }

        /* return the (unchanged) node pointer */
        return root;
    }
}

class BinaryTreeNode<T> {
    BinaryTreeNode<T> left, right, parent, next;
    T data;
    Returned returned = Returned.notpushed;
}



class Result {
    long max = Long.MIN_VALUE;
    long min =Long.MAX_VALUE;
    boolean isBst = true;
}