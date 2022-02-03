package myanswers;

import java.util.HashMap;
import java.util.Map;

public class LFUCache {

    Map<Integer, DoublyList> freqMap = new HashMap<>();
    Map<Integer, DoublyNode> nodeMap = new HashMap<>();
    int min = 0;
    int capacity;
    int size = 0;

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        if (!nodeMap.containsKey(key)) {
            return -1;
        }

        DoublyNode node = nodeMap.get(key);
        DoublyList doublyList = freqMap.get(node.freq);

        doublyList.remove(node);
        if(doublyList.size == 0){
            freqMap.remove(node.freq);
        }
        if (node.freq == min && doublyList.size==0) {
            min++;
        }

        node.freq++;


        DoublyList doublyList2 = freqMap.getOrDefault(node.freq, new DoublyList());
        doublyList2.add(node);
        freqMap.put(node.freq, doublyList2);

        return nodeMap.get(key).val;
    }

    public void put(int key, int value) {
        if(capacity == 0){
            return;
        }

        if (!nodeMap.containsKey(key)) {
            DoublyNode node = new DoublyNode();
            node.key = key;
            node.val = value;
            node.freq = 1;
            nodeMap.put(key, node);

            size++;

            if (size > capacity) {
                DoublyList doublyList = freqMap.get(min);
                nodeMap.remove(doublyList.tail.key);
                doublyList.remove(doublyList.tail);
                if(doublyList.size == 0){
                    freqMap.remove(min);
                }
                size--;
            }

            min = 1;
            DoublyList doublyList = freqMap.getOrDefault(1, new DoublyList());
            doublyList.add(node);
            freqMap.put(1, doublyList);
        } else {
            nodeMap.get(key).val = value;
            get(key);
        }

    }

}


class DoublyList {
    DoublyNode head;
    DoublyNode tail;
    int size = 0;

    public void add(DoublyNode node) {
        size++;
        if (head == null) {
            head = node;
            tail = node;
        } else {
            // we add always to front
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    public void remove(DoublyNode node) {
        size--;
        if (size == 0) {
            head = null;
            tail = null;
        } else {
            if (node == head) {
                head = head.next;
                head.prev = null;
            } else {
                node.prev.next = node.next;
                if (node.next != null) {
                    node.next.prev = node.prev;
                }
                if (node == tail) {
                    tail = node.prev;
                }
            }
        }
        node.prev = null;
        node.next = null;
    }


}

class DoublyNode {
    DoublyNode prev;
    DoublyNode next;
    int key;
    int freq;
    int val;
}
