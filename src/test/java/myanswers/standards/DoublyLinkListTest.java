package myanswers.standards;

import com.sun.source.tree.Tree;
import javafx.util.Pair;
import org.junit.Test;

import java.util.*;

public class DoublyLinkListTest {

    @Test
    public void test1(){
        LFUCache lfu = new LFUCache(2);
        lfu = new LFUCache(2);
        lfu.put(1,1);
        lfu.put(2,2);
        System.out.println(lfu.get(1));
        lfu.put(3, 3);
        System.out.println(lfu.get(2));
        System.out.println(lfu.get(3));
        lfu.put(4, 4);

        System.out.println(lfu.get(1));
        System.out.println(lfu.get(3));
        System.out.println(lfu.get(4));

        Deque<Integer> stack = new ArrayDeque<>();
        stack.addLast(1);
        stack.removeLast();
    }

    @Test
    // for testing max stack
    public void test2(){
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(3, 3);
        map.put(4, 4);
        map.put(8, 8);
        map.put(2, 2);
        System.out.println(map.firstKey());
        System.out.println(map.lastKey());


        //["MaxStack","push","push","push","top","popMax","top","peekMax","pop","top"]
        //[[],[5],[1],[5],[],[],[],[],[],[]]

        MaxStack maxStack = new MaxStack();
        maxStack.push(5);
        maxStack.push(1);
        maxStack.push(5);
        System.out.println(maxStack.top());
        System.out.println(maxStack.popMax());
        System.out.println(maxStack.top());


        int[][] times= new int[34][4];
        int n=0;
        int k=0;

        Queue<int[]> q = new PriorityQueue<>(new Comparator<>(){
            @Override
            public int compare(int[] a, int[] b){
                // 1 st element node, 2nd element distance from source
                return Integer.compare(a[1], b[1]);
            }
        });


    }
}