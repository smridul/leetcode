package myanswers.standards;

import org.junit.Test;

import java.util.*;

public class AllInOne {
    DllNode head = null;

    DllNode tail = null;

    Map<String, Integer> wordToCount = new HashMap<>();
    Map<Integer, DllNode> countToWord = new HashMap<>();

    public DllNode addToFront(int count, String key) {

        DllNode node = new DllNode(count);
        node.strings = new HashSet<>();
        node.strings.add(key);

        if (head == null && tail == null) {
            head = node;
            tail = node;
            return node;
        }


        node.next = head;
        head.prev = node;
        head = node;
        return node;
    }


    // addjacent next to node
    public DllNode addAdjacent(DllNode oldNode, String key) {

        DllNode node = new DllNode(oldNode.count + 1);
        node.strings = new HashSet<>();
        node.strings.add(key);

        // guaranteed that node already existed

        // so only 2 cases
        // if oldnode is tail
        if (oldNode == tail) {
            tail.next = node;
            node.prev = tail;
            tail = node;
        } else {
            // we are adding in between

            oldNode.next.prev = node;
            node.next = oldNode.next;

            oldNode.next = node;
            node.prev = oldNode;
        }
        return node;
    }

    public DllNode addPrevTo(DllNode oldNode, String key) {

        DllNode node = new DllNode(oldNode.count - 1);
        node.strings = new HashSet<>();
        node.strings.add(key);

        // two cases
        // if oldnode was head
        if (oldNode == head) {

            node.next = head;
            head.prev = node;
            head = node;
        } else {
            oldNode.prev.next = node;
            node.prev = oldNode.prev;

            node.next = oldNode;
            oldNode.prev = node;
        }
        return node;
    }


    public void remove(DllNode node) {

        if(head==tail){
            head=null;
            tail=null;
        }else if (node==head){
            head = head.next;
            head.prev= null;
        }else if(node==tail){
            tail.prev.next= null;
            tail = tail.prev;
        }else{
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        node.next = null;
        node.prev = null;

    }

    public void inc(String key) {

        wordToCount.put(key, wordToCount.getOrDefault(key, 0) + 1);
        int count = wordToCount.get(key);

        if (count == 1) {
            // only add to 1
            // no remvoe from 0
            if (!countToWord.containsKey(1)) {
                DllNode node = addToFront(1, key);
                countToWord.put(1, node);
            } else {
                countToWord.get(1).strings.add(key);
            }
        } else {

            int oldCount = count - 1;

            if (countToWord.containsKey(count)) {
                countToWord.get(count).strings.add(key);
            } else {
                DllNode node = addAdjacent(countToWord.get(oldCount), key);
                countToWord.put(count, node);
            }

            countToWord.get(oldCount).strings.remove(key);
            if (countToWord.get(oldCount).strings.size() == 0) {

                remove(countToWord.get(oldCount));
                countToWord.remove(oldCount);
            }

        }

    }

    public void dec(String key) {
        wordToCount.put(key, wordToCount.getOrDefault(key, 0) - 1);
        int count = wordToCount.get(key);
        if (count == 0) {
            wordToCount.remove(key);
        }

        int oldCount = count + 1;
       // countToWord.get(oldCount).strings.remove(key);


        if (count > 0) {
            // we need to add key in new bucket

            if (countToWord.containsKey(count)){
                countToWord.get(count).strings.add(key);
            }else{

                DllNode node= addPrevTo(countToWord.get(oldCount), key);
                countToWord.put(count, node);

            }
        }

        // rmove from old bucket

        countToWord.get(oldCount).strings.remove(key);
        if(countToWord.get(oldCount).strings.size() == 0){

            remove(countToWord.get(oldCount));
            countToWord.remove(oldCount);
        }

    }


    public String getMaxKey() {

        if(tail == null){
            return "";
        }

        Iterator<String> iter = tail.strings.iterator();
        return iter.next();
    }

    public String getMinKey() {
        if(head == null){
            return "";
        }

        Iterator<String> iter = head.strings.iterator();
        return iter.next();
    }

    @Test
    public void test(){
//        ["AllOne","inc","inc","inc","inc","inc","dec","getMaxKey","getMinKey","inc","inc","inc","getMaxKey","getMinKey","inc","inc","getMinKey"]
//[[],["hello"],["hello"],["world"],["world"],["hello"],["world"],[],[],["world"],["world"],["leet"],[],[],["leet"],["leet"],[]]
//

        inc("hello");
        inc("hello");
        inc("world");
        inc("world");
        inc("hello");
        dec("world");
        System.out.println(getMaxKey());
        System.out.println(getMinKey());

    }
}

class DllNode {

    int count;
    DllNode next;
    DllNode prev;
    Set<String> strings;

    DllNode(int val) {
        this.count = val;
    }

}
