package myanswers;


import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class LRU {
    LRUNode head, tail = null;
    Map<Integer, LRUNode> map = new HashMap<>();
    int size, capacity = 0;

    public void removeNode(LRUNode node){
        if(head==tail){
            head = null;
            tail = null;
            return;
        }

        // more than 1 case
        if(node.prev !=null){
            node.prev.next = node.next;
        }else{
            head = head.next;
            head.prev = null;
        }
        if(node.next!=null){
            node.next.prev = node.prev;
        }else{
            tail = tail.prev;
            tail.next = null;
        }
        node.next = null;
        node.prev = null;
    }

    public void addToFront(LRUNode node){
        node.next = head;
        if(head!=null){
            head.prev = node;
            head = node;
        }else{
            //first node
            head = node;
            tail = node;
        }
    }


    public void put(int key, int value){
        if(map.containsKey(key)){
            LRUNode node = map.get(key);
            node.value = value;
            removeNode(node);
            addToFront(node);
        }else{
            LRUNode node = new LRUNode();
            node.value = value;
            node.key=key;
            map.put(key, node);
            if(size < capacity){
                addToFront(node);
                size++;
            }else{
                map.remove(tail.key);
                removeNode(tail);
                addToFront(node);
            }
        }
    }

    public int get(int key){

        if(map.containsKey(key)){
            LRUNode node = map.get(key);
            removeNode(node);
            addToFront(node);
            return node.value;
        }else{
            return -1;
        }

    }

    @Test
    public void test(){

        LRU lru = new LRU();
        lru.capacity = 2;
       //  ["LRUCache","put","put","get","put","get","put","get","get","get"]
            //[[2],[1,0],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]
        lru.put(1, 0);
        lru.put(2, 2);
        System.out.println(lru.get(1));
        lru.put(3, 3);
        System.out.println(lru.get(2));
        lru.put(4, 4);
        System.out.println(lru.get(1));
        System.out.println(lru.get(3));
        System.out.println(lru.get(4));


    }
}

class LRUNode{
    int value;
    LRUNode prev;
    LRUNode next;
    int key;
}
