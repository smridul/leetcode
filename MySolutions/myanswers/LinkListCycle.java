package myanswers;

import org.junit.Test;
import leetcode_classes.ListNode;

public class LinkListCycle {
    public ListNode detectCycle(ListNode head) {
       if(head == null){
           return null;
       }

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != null && fast != slow) {

            slow = slow.next;
            fast = fast.next;

            if (fast != null) {
                fast = fast.next;
            }
        }
        if (fast != slow) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        slow = dummy;

        while (fast != slow) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;

    }

    @Test
    public void test() {
        ListNode three = new ListNode(3);
        ListNode two = new ListNode(2);
        ListNode zero = new ListNode(0);
        ListNode four = new ListNode(-4);

        three.next = two;
        two.next = zero;
        zero.next = four;
        four.next = two;
        System.out.println(detectCycle(three).val);

    }
}
