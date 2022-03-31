package myanswers.easy;

public class RemoveLinkListElements {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode();

        dummy.next = head;
        ListNode prev = dummy;
        ListNode current = head; // or dummy->next

        while (prev.next != null) {
            if (current.val == val) {
                prev.next = current.next;
            } else {
                prev = current;
            }
            current = current.next;
        }
        return dummy.next;
    }
}


class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}