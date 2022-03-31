package myanswers;

import org.junit.Test;
import org.w3c.dom.NodeList;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by smridul on 1/1/19.
 */
public class MergeKsorted {

    @Test
    public void merge() {


        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;

        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);
        node4.next = node5;
        node5.next = node6;

        ListNode node7 = new ListNode(2);
        ListNode node8 = new ListNode(6);
        node7.next = node8;

        ListNode[] nodeLists = new ListNode[]{node1, node4, node7};


        ListNode mergenode = mergeKListsRecursion(nodeLists);
        int a = 1;


    }


    // Recursion
    public ListNode mergeKListsRecursion(ListNode[] lists) {


        int k = lists.length;


        return mergeKListsRecursion(lists, 0, k - 1);


    }

    public ListNode mergeKListsRecursion(ListNode[] lists, int start, int end) {


        if (start == end) {
            return lists[start];
        } else if (start > end) {
            return null;
        }

        // merge k/2 first half list
        int mid = (start + end) / 2;
        ListNode first = mergeKListsRecursion(lists, start, mid);

        // merge k/2 second half list
        ListNode second = mergeKListsRecursion(lists, mid + 1, end);


        // merge these 2 sorted list
        return merge(first, second);

    }

    public ListNode merge(ListNode first, ListNode second) {

        ListNode newList = new ListNode(0);
        ListNode current = newList;

        while (first != null && second != null) {

            if (first.val < second.val) {
                current.next = first;
                current = first;
                first = first.next;
            } else {
                current.next = second;
                current = second;
                second = second.next;
            }
        }

        if (first == null) {
            current.next = second;
        } else {
            current.next = first;
        }
        return newList.next;
    }


    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }

        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val < o2.val)
                    return -1;
                else if (o1.val == o2.val)
                    return 0;
                else
                    return 1;
            }
        });


        for (ListNode node : lists) {
            if (node != null) queue.add(node);
        }
        ListNode newList = new ListNode(0);
        ListNode current = newList;

        while (queue.size() != 0) {

            ListNode minFromQueue = queue.poll();
            if (minFromQueue.next != null) {
                queue.add(minFromQueue.next);
            }

            current.next = minFromQueue;
            current = current.next;
        }

        return newList.next;
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
