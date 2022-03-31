package myanswers.syntaxes;

import org.junit.Test;

import java.util.*;

/**
 * Created by smridul on 6/23/18.
 */
public class MyDeque {

    @Test
    public void test(){

        Deque<Integer> stack = new ArrayDeque<>();
        stack.addFirst(1);
        stack.addFirst(2);
        stack.addFirst(3);
        stack.addFirst(4);
        stack.push(5);

        // for stack operations
        // use addFirst or push ...
        // use removeFirst or pop
        // for peeking top elemnt
        // use getfirst or peekfirst or peek

        // remember 0th element will be the latest element pushed



        int element2 = stack.peek();
        int element3 = stack.peekFirst();
        int element4 = stack.getFirst();

        Iterator<Integer> iter = stack.descendingIterator();

        int element5 = iter.next();
          // read elements in order they are pushed
        // in the order of size th elemnt to 0th element




        Iterator<Integer> iter2 = stack.iterator();
        int element6 = iter2.next();
        // in the order of 0th elemnt to size element
        // OR in the order of pop




        Deque<Integer> queue = new ArrayDeque<>();
        queue.addLast(1);
        queue.addLast(2);
        queue.addLast(3);
        queue.addLast(4);
        queue.add(5);

        // for Queue operations
        // use addLast or enqueue ...
        // use removeFirst or deque
        // for peeking top elemnt
        // use getfirst or peekfirst or peek

        // If you want this to operate as doubble ended queue just use the function as name suggest


        Queue<Integer> queue2 = new ArrayDeque<>();
        queue2.add(1);
        queue2.add(2);
        queue2.add(3);
        queue2.add(4);
        queue2.add(5);

        // we cannot use addLast here because Type is Queue
        // but add => addLast
        // and remove ==> removeFirst

        queue2.remove();

    }


    @Test
    public void tt(){

        LinkedList<Integer> qq = new LinkedList<>();
        qq.remove();


        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(1);
    }



}
