package myanswers.standards;

import org.junit.Test;

import java.util.*;

public class LruCache2{
    int capacity;
    Map<Integer, Node> map = new HashMap<>();
    int size=0;
    Node head;
    Node tail;

    public LruCache2(int capacity) {
        this.capacity = capacity;
        head=null;
        tail=null;
    }
    void addToFront(Node node){
        if(head==null){
            head=node;
            tail = node;
        }else{
            node.next = head;
            head.prev= node; // this line
            head = node;
        }
        size++;
    }

    void remove(Node node){
        if(node.next!=null){
            node.next.prev = node.prev;

        }
        if(node.prev!=null){
            node.prev.next = node.next;
        }
        if(node==head){
            head = head.next;
        }
        if(node==tail){
            tail = tail.prev;
        }
        node.next = null;
        node.prev = null; //[this is imp step] because we are adding this same node at front
        // it prev should be null. I made this mistake in interview
        size--;
    }


    public Node getInternal(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            remove(node);
            addToFront(node);
            return node;
        } else {
            return null;
        }
    }

    public int get(int key){
        Node node = getInternal(key);
        if(node== null){
            return -1;
        }else{
            return node.val;
        }
    }

    public void put(int key, int value) {
        Node existingNode = getInternal(key);
        if (existingNode!=null) {
            existingNode.val = value;
        } else  {
            // add new node to front
            Node node = new Node();
            node.val = value;
            node.key = key;
            if(size == capacity){
                int toRemove = tail.key;
                remove(tail);
                //remove from map too
                map.remove(toRemove);
            }
            addToFront(node);
            map.put(key, node);
        }
    }



}

class Node{
    Node prev, next;
    int val;
    int key;
}

