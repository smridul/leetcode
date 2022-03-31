package myanswers;

import org.junit.Test;

public class InsertNodeCircularList {

    public Node insert2(Node head, int insertVal) {
        Node node = new Node(insertVal);
        if (head == null) {
            node.next = node;
            return node;
        }

        Node current = head;
        Node prev = null;
        while (true) {

            if(prev!=null && node.val >= prev.val && node.val <=current.val){
                insertBetween(prev, current, node);
                return head;
            }

            if(prev!=null && prev.val > current.val){
                // prev is greatest. current is smallest
                if(node.val <= current.val || node.val >= prev.val){
                    insertBetween(prev, current, node);
                    return head;
                }
            }
            prev = current;
            current = current.next;
            if(current == head){
                break;
            }
        }

        // else all element same case or case when element already sorted and added element is out of that range
        insertBetween(prev, current, node);
        return head;
    }

    private void insertBetween(Node one, Node two, Node node){
        one.next = node;
        node.next = two;
    }


    // break the circle approach
    public Node insert(Node head, int insertVal) {
        Node node = new Node(insertVal);
        if (head == null) {
            node.next = node;
            return node;
        }

        Node current = head;
        Node prev = null;
        Node start = null;
        Node end = null;
        while (true) {
            if (prev != null && (prev.val > current.val)) {
                start = current;
                end = prev;
                break;
            }
            prev = current;
            current = current.next;
            if (current == head) {
                start = current;
                end = prev;
                break;
            }
        }

        // break the circle make is linear and process
        // in the end bring circle again
        prev = null;
        current = start;
        end.next = null;
        while (current != null) {
            if (node.val < current.val) {
                node.next = current;
                if (prev != null) { // 1st add before start
                    prev.next = node;
                } else {
                    start = node;
                }
                break;
            }
            prev = current;
            current = current.next;
        }

        if (current == null) {// last add after end
            prev.next = node;
            end = node;
        }
        end.next = start;
        return head;
    }

    @Test
    public void test() {
        Node node1 = new Node(1);
        node1.next = node1;

        Node n = insert(node1, 0);
        int a = 0;


    }

}
