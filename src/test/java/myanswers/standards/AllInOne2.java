package myanswers.standards;

import org.junit.Test;

import java.util.*;

public class AllInOne2 {

    DllNode2 head = null;

    DllNode2 tail = null;

    Map<String, Integer> wordToCount = new HashMap<>();
    Map<Integer, DllNode2> countToWord = new HashMap<>();

    public void addToFront(String key) {
        DllNode2 node = new DllNode2();
        node.strings = new HashSet<>();
        node.strings.add(key);

        if(head == null){
            head = node;
            tail = node;
        }else {
            node.next = head;
            head.prev = node;
            head = node;
        }
    }

    public void remove (DllNode2 toRemove){

        if(toRemove!=tail){
            toRemove.next.prev = toRemove.prev;
        }
        if(toRemove!=head){
            toRemove.prev.next = toRemove.next;
        }

        if(toRemove == head){
            head = toRemove.next;
        }

        if(toRemove == tail){
            tail = toRemove.prev;
        }
        toRemove.next = null;
        toRemove.prev = null;
    }

    public DllNode2 addNext(String key, DllNode2 oldNode){

        DllNode2 node = new DllNode2();
        node.strings = new HashSet<>();
        node.strings.add(key);

        DllNode2 next = oldNode.next;
        oldNode.next = node;
        node.prev = oldNode;

        node.next  = next;

        if(next!=null){
            next.prev = node;
        }else {
            //next was null
            // oldNode was tail
            // hence updte tail
            tail = node;
        }

        return node;

    }


    public DllNode2 addBefore(String key, DllNode2 oldNode){
        DllNode2 node = new DllNode2();
        node.strings = new HashSet<>();
        node.strings.add(key);


        DllNode2 prev = oldNode.prev;

        node.next = oldNode;
        oldNode.prev = node;

        node.prev = prev;

        if(prev!=null){
            prev.next = node;
        }else{
            //prev is null
            // old node was head;
            //update head
            head = node;
        }
        return node;
    }


    public void inc(String key) {
        // it is new key
        if(!wordToCount.containsKey(key)){
            wordToCount.put(key, 1);
            if(countToWord.containsKey(1)){
                countToWord.get(1).strings.add(key);
            }else{
                addToFront(key);
                countToWord.put(1, head);
            }
        }else{
            //this is not a new key
            int existingCount = wordToCount.get(key);
            int newCount = existingCount+1;
            wordToCount.put(key, newCount);

            if(countToWord.containsKey(newCount)){
                countToWord.get(newCount).strings.add(key);
            }else{
                //we need to insert a new node after existing count dll node'

                DllNode2 node = addNext(key, countToWord.get(existingCount));
                countToWord.put(newCount, node);
            }

            //remove from old count

            if(countToWord.get(existingCount).strings.size() > 1){
                countToWord.get(existingCount).strings.remove(key);
            }else{
                //we need to remove existing count dllnode
                remove(countToWord.get(existingCount));
                //from map too
                countToWord.remove(existingCount);

            }
        }
    }

    public void dec(String key){
        int existingCount = wordToCount.get(key);
        int newCount = existingCount-1;
        if(newCount == 0){
            wordToCount.remove(key);
        }else{
            wordToCount.put(key, newCount);
        }

        if(newCount!=0) {
            if (countToWord.containsKey(newCount)) {
                countToWord.get(newCount).strings.add(key);
            } else {
                DllNode2 node = addBefore(key, countToWord.get(existingCount));
                countToWord.put(newCount, node);
            }
        }
        //remove
        if(countToWord.get(existingCount).strings.size() > 1){
            countToWord.get(existingCount).strings.remove(key);
        }else{
            remove(countToWord.get(existingCount));
            //from map too
            countToWord.remove(existingCount);
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
        inc("hello");
        inc("hello");
        System.out.println(getMaxKey());
        System.out.println(getMinKey());
        inc("leet");
        System.out.println(getMaxKey());
        System.out.println(getMinKey());
    }

}



class DllNode2 {

    //int count;
    DllNode2 next;
    DllNode2 prev;
    Set<String> strings;

}
