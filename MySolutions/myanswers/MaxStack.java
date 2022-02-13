package myanswers;

import java.util.*;

public class MaxStack {
    Dll dll = new Dll();
    TreeMap<Integer, List<DllNode>> map;

    public MaxStack() {
        map = new TreeMap();
    }


    public void push(int x) {

        DllNode node = new DllNode(x);
        dll.add(node);
        if (!map.containsKey(x)) {
            map.put(x, new ArrayList<>());
        }
        map.get(x).add(node);
    }

    public int pop() {
        DllNode node = dll.pop();
        List<DllNode> list = map.get(node.val);
        if (list.size() == 1) {
            map.remove(node.val);
        } else {
            list.remove(list.size() - 1);
        }

        return node.val;
    }

    public int top() {
        return dll.tail.val;
    }

    public int peekMax() {
        return map.lastKey();
    }

    public int popMax() {
        int toreturn  = map.lastKey();
        List<DllNode> list = map.get(map.lastKey());
        dll.remove(list.get(list.size()-1));
        if(list.size() ==1){
            map.remove(map.lastKey());
        }else{
            list.remove(list.size()-1);
        }

        return toreturn;
    }
}


class Dll {
    DllNode head;
    DllNode tail;
    int size = 0;

    public void add(DllNode node) {
        size++;
        if (head == null) {
            head = node;
            tail = node;
        } else {
            // we add always to end
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }

    public DllNode pop() {
        size--;
        DllNode temp = tail;
        if (tail.prev != null) {
            tail.prev.next = null;
            tail = tail.prev;
        } else {
            head = null;
            tail = null;
        }
        return temp;
    }

    public void remove(DllNode node) {
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

class DllNode {
    int val;
    DllNode prev, next;

    public DllNode(int v) {
        val = v;
    }
}