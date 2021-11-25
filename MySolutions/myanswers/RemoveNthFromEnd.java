package myanswers;

public class RemoveNthFromEnd {

    public ListNode removeNthFromEnd(ListNode head, int n) {

        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode first = head;
        ListNode second = head;

        int steps = n - 1;
        while (steps-- > 0) {
            second = second.next;
        }

        ListNode prev = dummy;
        while (second.next != null) {
            prev = first;
            first = first.next;
            second = second.next;
        }
        prev.next = first.next;
        return dummy.next;
    }
}
