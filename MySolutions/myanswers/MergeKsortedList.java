package myanswers;

import javafx.util.Pair;
import leetcode_classes.ListNode;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKsortedList {

    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<Pair<ListNode, Integer>> pq = new PriorityQueue<>(new Comparator<Pair<ListNode, Integer>>() {
            @Override
            public int compare(Pair<ListNode, Integer> o1, Pair<ListNode, Integer> o2) {
                return o1.getKey().val - o2.getKey().val;
            }
        });
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) {
                pq.offer(new Pair<>(lists[i], i));
            }
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while (!pq.isEmpty()) {
            Pair<ListNode, Integer> pair = pq.poll();
            ListNode node = pair.getKey();
            int index = pair.getValue();
            current.next = node;
            current = node;
            lists[index] = lists[index].next;
            if (lists[index] != null) {
                pq.offer(new Pair<>(lists[index], index));
            }
        }
        return dummy.next;
    }


    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return mergeKListsRecursion(0, lists.length - 1, lists);
    }

    public ListNode mergeKListsRecursion(int low, int high, ListNode[] lists) {

        if (low == high) {
            return lists[low];
        }

        int mid = low + (high - low) / 2;

        ListNode first = mergeKListsRecursion(low, mid, lists);
        ListNode second = mergeKListsRecursion(mid + 1, high, lists);
        return merge2Lists(first, second);

    }

    ListNode merge2Lists(ListNode first, ListNode second) {
        if (first == null) {
            return second;
        }
        if (second == null) {
            return first;
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (first != null && second != null) {
            if (first.val < second.val) {
                current.next = first;
                first = first.next;
            } else {
                current.next = second;
                second = second.next;
            }
            current = current.next;
        }
        current.next = first == null ? second : first;
        return dummy.next;
    }

}
