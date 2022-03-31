package myanswers;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ListCopy {

    public Node1 copyRandomList(Node1 head) {
        if (head == null) return null;
        Map<Node1, Node1> map = new HashMap<>();
        return dfs(head, map);
    }


    public Node1 dfs(Node1 node, Map<Node1, Node1> map) {
        if (map.get(node) != null) {
            return map.get(node);
        }

        Node1 n = new Node1(node.val);
        map.put(node, n);

        if (node.next != null) {
            n.next = dfs(node.next, map);
        }

        if (node.random != null) {
            n.random = dfs(node.random, map);
        }
        return n;
    }


    public Node1 copyRandomListIterative(Node1 head) {
        if (head == null) return null;
        Node1 dummy = new Node1(0);
        Node1 current = head;

        dummy.next = head;
        while (current != null) {
            Node1 duplicate = new Node1(current.val);
            duplicate.next = current.next;
            current.next = duplicate;
            current = duplicate.next;
        }


        current = head;
        while (current != null) {
            Node1 duplicate = current.next;
           if(current.random!=null){
               duplicate.random = current.random.next;
           }
            current = duplicate.next;
        }

        current = head;
        Node1 prevDuplicate = dummy;

        while (current != null) {
            Node1 currentDuplicate = current.next;
            prevDuplicate.next = current.next;
            current.next = currentDuplicate.next;
            prevDuplicate = currentDuplicate;
            current = currentDuplicate.next;
        }
        return dummy.next;
    }


    @Test()
    public void test(){
        Node1 node1 = new Node1(7);
        Node1 node2 = new Node1(13);
        Node1 node3 = new Node1(11);
        Node1 node4 = new Node1(10);
        Node1 node5 = new Node1(1);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        node2.random = node1;
        node3.random = node5;
        node4.random = node3;
        node5.random = node1;
        copyRandomListIterative(node1);
    }
}


class Node1 {
    int val;
    Node1 next;
    Node1 random;

    public Node1(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}