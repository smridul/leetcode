package myanswers.standards;

import leetcode_classes.Node;

import java.util.*;


public class DoublyLinkList {

    Node head;
    Node tail;
    int size;

    public DoublyLinkList() {
        head = null;
        tail = null;
        size = 0;
    }

    // needed for LRU
    // standard method could be just add, given the position where to add
    // here we are adding always at the beginning
    public void addNodeToFront(Node newnode) {
        // first node
        if (head == null) {
            head = newnode;
            tail = newnode;
        } else {
            // we are adding before head
            newnode.right = head;
            head.left = newnode;
            head = newnode;
        }
        size++;
    }

    //this will be standard
    public void removeNode(Node node) {
        // case1
        if (node.left != null) {
            //this is not head
            node.left.right = node.right;
        }

        if (node.right != null) {
            // this is not tail
            node.right.left = node.left;
        }

        //update head if needed
        if (node == head) {
            head = head.right;
        }
        // update tail if needed
        if (node == tail) {
            tail = tail.left;
        }
        node.left = null;  //[this is imp step] because we are adding this same node at front
        // it prev should be null. I made this mistake in interview
        node.right = null;
        size--;
    }
}


/*
LFU cache
 */
class LRUCache {
    int capacity;
    Map<Integer, Node> map = new HashMap<>();
    DoublyLinkList dll;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        dll = new DoublyLinkList();
    }

    public int get(int key) {
        Node node = getNode(key);
        if (node!=null) {
            return node.val;
        } else {
            return -1;
        }
    }

    public Node getNode(int key) {
        if (map.containsKey(key)) {
            Node node = map.get(key);
            dll.removeNode(node);
            dll.addNodeToFront(node);
            return node;
        } else {
            return null;
        }
    }

    public void put(int key, int value) {
        Node existingNode = getNode(key);
        if (existingNode!=null) {
            existingNode.val = value;
        } else {
            // add new node to front
            Node node = new Node();
            node.val = value;
            node.key = key;

            if (dll.size == capacity) {
                int toRemove = dll.tail.key;
                dll.removeNode(dll.tail);
                //remove from map too
                map.remove(toRemove);
            }
            dll.addNodeToFront(node);
            map.put(key, node);
        }
    }
}

/*
LFU cache
*/
class LFUCache {

    int capacity = 0;
    int currentSize = 0;
    int minFreq = 0;
    Map<Integer, DoublyLinkList> freqMap = new HashMap<>();
    Map<Integer, Node> nodeMap = new HashMap<>();

    public LFUCache(int capacity) {
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = getNode(key) ;
        if(node ==null){
            return -1;
        }else{
            return node.val;
        }
    }

    public Node getNode(int key) {
        if (nodeMap.containsKey(key)) {
            // we need to increase the freq count of the node
            // so remove it from current freq count and then
            // add it to freq+1 list

            // and if the current freq count list become empty then remove it from map
            // and also update min freq if required
            Node node = nodeMap.get(key);
            int freqCount = node.freqCount;
            node.freqCount++;

            DoublyLinkList oldDll = freqMap.get(freqCount);
            oldDll.removeNode(node);

            if (oldDll.size == 0) {
                freqMap.remove(freqCount);
                if (minFreq == freqCount) {
                    // new min will be freqcount+1;
                    minFreq++;
                }
            }
            DoublyLinkList newDll = freqMap.getOrDefault(freqCount + 1, new DoublyLinkList());
            newDll.addNodeToFront(node);

            freqMap.put(freqCount + 1, newDll);// can be done via if/else because this  step is only required when get(freqCount+1) is null
            return node;
        } else {
            return null;
        }
    }

    public void put(int key, int value) {
        Node existingNode = getNode(key);
        if (existingNode != null) {
            existingNode.val = value;
        } else {
            Node node = new Node();
            node.val = value;
            node.key = key;
            node.freqCount = 1;
            //add to 1 list
            DoublyLinkList oldDll = freqMap.getOrDefault(1, new DoublyLinkList());
            oldDll.addNodeToFront(node);
            nodeMap.put(key, node);
            if (!freqMap.containsKey(1)) {
                freqMap.put(1, oldDll);
            }

            if (currentSize == capacity) {
                // remove node from minfreq list in LRU fashion
                DoublyLinkList dll = freqMap.get(minFreq);
                int toRemove = dll.tail.key;
                dll.removeNode(dll.tail);
                nodeMap.remove(toRemove);

                // if after removing list become empty remove it from map too
                if(dll.size==0){
                    freqMap.remove(minFreq);
                }
            }else{
                currentSize++;
            }
            minFreq = 1;
        }
    }
}


class MaxStack {

    DoublyLinkList dll  = new DoublyLinkList();
    TreeMap<Integer, Deque<Node>> map = new TreeMap<>();
    public MaxStack() {

    }

    public void push(int x) {

        // we need to add at last
        // and we should be able to delete any node
        // so can we use our same above dll
        // i think yes. for adding node we will use add to front
        // for pop, we will use remove(dll.head) so it works as stack
        // for pop max just get the node and call remove
        Node node = new Node();
        node.val = x;
        dll.addNodeToFront(node);
        Deque<Node> list = map.getOrDefault(x, new ArrayDeque());
        list.addLast(node);
        map.put(x, list);
    }

    public int pop() {

        Node node = dll.head;
        dll.removeNode(dll.head);

        int val = node.val;
        Deque<Node> list = map.get(val);
        list.removeLast();

        if(list.isEmpty()){
            map.remove(val);
        }
        return val;
    }

    public int top() {
        return dll.head.val;
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int max = map.lastKey();
        Deque<Node> list = map.get(max);

        Node node = list.removeLast();
        dll.removeNode(node);
        if(list.isEmpty()){
            map.remove(max);
        }
        return max;
    }
}