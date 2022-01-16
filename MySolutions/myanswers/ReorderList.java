package myanswers;

import leetcode_classes.ListNode;

public class ReorderList {
    public void reorderList(ListNode head) {

        if(head == null || head.next ==null){
            return;
        }
        ListNode current = head;

        int n=0;
        while(current!=null){
            n++;
            current = current.next;
        }

        current = head;
        ListNode prev= null;
        int steps = n/2;
        while (steps-- > 0){
            prev = current;
            current = current.next;
        }

        prev.next = null;

        ListNode head1 = head;
        ListNode head2 = current;

        head2 = reverseList(head2);

        merge(head1, head2);
    }

    public ListNode merge(ListNode head1, ListNode head2){

        ListNode dummy = new ListNode(0);
        dummy.next = head1;
        ListNode current = dummy;

        while (head1!=null && head2!=null){
            current.next = head1;
            head1 = head1.next;
            current = current.next;


            current.next = head2;
            head2 = head2.next;
            current = current.next;
        }

        current.next = head1==null ? head2 : head1;
        return dummy.next;
    }


    public ListNode reverseList(ListNode head){

        if(head==null){
            return null;
        }
        ListNode current = head;
        ListNode next = current.next;
        current.next = null;

        while(next!=null){
            ListNode save = next.next;
            next.next = current;
            current = next;
            next = save;
        }
        return current;
    }
}
