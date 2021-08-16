package myanswers.syntaxes;

import org.junit.Test;


public class MyLinkList {


    @Test
    public void test() {
        //LinkedList<String> list = new LinkedList<>(Arrays.asList("1", "2", "3", "4", "5"));
        MyNode head = new MyNode(0, null);
        addNext(head, new MyNode(1, null));
        addNext(head, new MyNode(2, null));
        addNext(head, new MyNode(3, null));
        addNext(head, new MyNode(4, null));
       // print(head);

        //isPalindrome(head);


        MyNode head2 = new MyNode(1, null);
        addNext(head2, new MyNode(3, null));
        addNext(head2, new MyNode(5, null));
        addNext(head2, new MyNode(5, null));
        addNext(head2, new MyNode(2, null));
        addNext(head2, new MyNode(9, null));
        addNext(head2, new MyNode(10, null));
        addNext(head2, new MyNode(6, null));

        print(head2);
        print(pivoting(head2,  5));

       // print(evenOdd(head));
        // print(reverse(head));
        // print(reverseOptimized(head));
        // print(reverseSublist(head, 3, 5));
    }

    public MyNode evenOdd(MyNode head) {

        MyNode dummyeven = new MyNode(0, null);
        MyNode dummyOdd = new MyNode(0, null);

        MyNode eventail = dummyeven;
        MyNode oddtail = dummyOdd;

        MyNode current = head;
        boolean iseven = true;
        while (current != null) {
            if (iseven) {
                eventail.next = current;
                eventail = eventail.next;
            } else {
                oddtail.next = current;
                oddtail = oddtail.next;
            }
            iseven = !iseven;
            current = current.next;
        }

        eventail.next = dummyOdd.next;
        oddtail.next = null;
        return dummyeven.next;
    }


    // nice concept of tail
    public MyNode pivoting(MyNode head, int pivot){
        MyNode dummySmall = new MyNode(0, null);
        MyNode dummyEqual = new MyNode(0, null);
        MyNode dummyLarge = new MyNode(0, null);

        MyNode smallTail = dummySmall;
        MyNode equalTail = dummyEqual;
        MyNode largeTail = dummyLarge;

        MyNode current = head;
        while (current!=null){

            if(current.val < pivot){
                smallTail.next = current;
                smallTail = smallTail.next;
            } else if(current.val > pivot){
                largeTail.next = current;
                largeTail = largeTail.next;
            }else{
                equalTail.next = current;
                equalTail= equalTail.next;
            }
            current = current.next;
        }
        smallTail.next = dummyEqual.next;
        equalTail.next = dummyLarge.next;
        largeTail.next = null;

        return dummySmall.next;
    }


    public boolean isPalindrome(MyNode head){
        MyNode slow = head;
        MyNode fast = head;

        while(fast!=null && fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        MyNode fhalf = head;
        MyNode shalf = reverse(slow);

        //tricky solution after this in EPI
        // if odd length first/second half have same length list
        // if even length, first have 1 node than second half
        // but does not matter because we are checkning till any one of list become null, so last extra node checking is
        // ignored. JUMBOOOO tricky

        return true;
    }
    public void reverseWrong(MyNode head) {
        if (head == null) {
            return;
        }
        MyNode current = head.next;
        MyNode tail = head;

        while (current != null) {

            tail.next = current.next;
            current.next = head;
            head = current;
            current = tail.next;
        }
    }

    public MyNode reverse(MyNode head) {
        if (head == null) {
            return null;
        }
        MyNode current = head.next;
        MyNode tail = head;

        while (current != null) {

            tail.next = current.next;
            current.next = head;
            head = current;
            current = tail.next;
        }
        return head;
    }

    public MyNode reverseOptimized(MyNode head) {

        MyNode dummy = new MyNode(0, head);
        MyNode current = dummy;

        while (current.next != null) {
            MyNode temp = current.next;
            current.next = temp.next;
            temp.next = dummy;
            dummy = temp;
        }

        head.next = null;
        return dummy;
    }

    public void addNext(MyNode head, MyNode node) {
        MyNode dummy = new MyNode(0, head);
        MyNode current = dummy;
        while (current.next != null) {
            current = current.next;
        }

        current.next = node;
    }

    public void print(MyNode head) {
        MyNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }


    public MyNode reverseSublist(MyNode head, int a, int b) {

        MyNode current = head;
        MyNode tail = new MyNode(0, head);
        MyNode dummynode = tail;

        int i = 1;
        while (i < a) {
            tail = current;
            current = current.next;
            i++;
        }

        // now tail is at a-1 position

        // start the reverse process
        MyNode reverseStart = current;
        MyNode currentNext;
        while (i < b) {
            currentNext = current.next;
            current.next = currentNext.next;
            currentNext.next = reverseStart;

            reverseStart = currentNext;
            i++;
        }


        // if k=1, then tail be the dummy node we created
        // hence tail.next is the first node
        // and we assign this forst node to reversestart

        // when tail is not first node, then dummynode.next is still the same whoch is head not modified

        // magic of dummy node
        tail.next = reverseStart;
        return dummynode.next;

    }

}

class MyNode {
    int val;
    MyNode next;

    MyNode(int val, MyNode next) {
        this.next = next;
        this.val = val;
    }
}