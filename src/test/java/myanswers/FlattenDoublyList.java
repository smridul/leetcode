package myanswers;
import javafx.util.Pair;
import org.junit.Test;

public class FlattenDoublyList {


    @Test
    public void test(){
        Node3 node1= new Node3();
        node1.val = 1;

        Node3 node2= new Node3();
        node2.val = 2;
        Node3 node3= new Node3();
        node3.val = 3;

        node1.next=node2;
        node2.prev = node1;

        node1.child = node3;

        Node3 nn = flatten(node1);
        System.out.println(11%-6);


    }
    public Node3 flatten(Node3 head) {


        return recurse(head).getKey();
    }



    Pair<Node3, Node3> recurse (Node3 head){

        Node3 start = head;
        Node3 prev = null;
        while(start!=null){

            if(start.child!=null){

                Pair<Node3, Node3> child = recurse(start.child);
                Node3 childHead= child.getKey();
                Node3 childTail = child.getValue();

                Node3 next = start.next;

                start.next = childHead;
                childHead.prev = start;

                if(next!=null){
                    next.prev = childTail;
                    childTail.next = next;
                }

                //jump to tail
                start = childTail;
            }
            prev=start;
            start=start.next;
        }

        return new Pair<>(head, prev);
    }



    String mybase2(int n){
        StringBuilder sb = new StringBuilder();
        while(n!=0){
            int rem = n%-2;
            n = n/-2;
            if(rem < 0){
                rem = -rem;
                n+=1;
            }
            sb.append(rem);


        }

        return sb.reverse().toString();
    }

    public String base2(int n){
        if(n == 0){
            return "0";
        }
        StringBuilder sb= new StringBuilder();
        while(n!=0){
            if( (n&1) ==1){
                sb.append("1");
            }else{
                sb.append("0");
            }
            n= -(n>>1);
        }
        return sb.reverse().toString();
    }
    @Test
    public void test1(){
        System.out.println(base2(3));
        System.out.println(mybase2(3));

        System.out.println(-1%-2);
        System.out.println(-1/-2);
        String s  ="43";

    }
}

class Node3 {
    public int val;
    public Node3 prev;
    public Node3 next;
    public Node3 child;
};
