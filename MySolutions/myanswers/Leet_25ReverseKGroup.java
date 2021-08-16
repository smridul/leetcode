package myanswers;


import org.junit.Test;


public class Leet_25ReverseKGroup {


    @Test
    public void test() {
        //LinkedList<String> list = new LinkedList<>(Arrays.asList("1", "2", "3", "4", "5"));
        MyNode head = new MyNode(1, null);
        addNext(head, new MyNode(2, null));
        addNext(head, new MyNode(3, null));
        addNext(head, new MyNode(4, null));
        addNext(head, new MyNode(5, null));
        print(head);

        print(reverseK(head, 1));
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

    public MyNode reverseK(MyNode head, int k) {

        int length = k;
        MyNode dummy = new MyNode(0, head);
        MyNode current = head;

        MyNode beforeStart = dummy;
        MyNode start = dummy.next;
        MyNode end = head;
        while (current != null) {
            while (length != 0 && current!=null) {
                end = current;
                current = current.next;
                length--;
            }
            if (length == 0) {
                reverseSublist(start, end);
                beforeStart.next = end;

                beforeStart = start;
                start = beforeStart.next;
                current = start;
                length = k;
            }
        }

        return dummy.next;
    }


    //what it does reverses start till end
    // returned node is end
    // new start.next  should be end.next
    public MyNode reverseSublist(MyNode start, MyNode end) {

        MyNode current = start;
        // start the reverse process
        MyNode reverseStart = current;
        MyNode currentNext;
        while (reverseStart != end) {
            currentNext = current.next;
            current.next = currentNext.next;
            currentNext.next = reverseStart;
            reverseStart = currentNext;
        }
        return end;
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