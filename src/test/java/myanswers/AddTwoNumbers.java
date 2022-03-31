package myanswers;

import org.junit.Test;

import java.util.Stack;

public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        int carry = 0;
        //dummy node
        ListNode head = new ListNode(0);
        ListNode current = head;

        while (l1 != null || l2 != null) {
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;

            int number = val1 + val2 + carry;
            ListNode newNode = new ListNode(number % 10);
            current.next = newNode;
            current = newNode;
            carry = number / 10;
            l1 = l1 != null ? l1.next : null;
            l2 = l2 != null ? l2.next : null;
        }

        if (carry > 0) {
            ListNode newNode = new ListNode(carry);
            current.next = newNode;
        }
        return head.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();

        while(l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        };
        while(l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        int sum = 0;
        ListNode list = new ListNode(0);
        while (!s1.empty() || !s2.empty()) {
            if (!s1.empty()) sum += s1.pop();
            if (!s2.empty()) sum += s2.pop();
            list.val = sum % 10;
            ListNode head = new ListNode(sum / 10);
            head.next = list;
            list = head;
            sum /= 10;
        }

        return list.val == 0 ? list.next : list;
    }

    @Test
    public void test(){
        ListNode node1= new ListNode(9);
        ListNode node2 = new ListNode(9);
        addTwoNumbers2(node1, node2);
    }








}
