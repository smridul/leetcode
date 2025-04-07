package myanswers;

import java.util.*;

class AuthenticationManager {

    int ttl ;
    DoublyLinkList dll;
    Map<String, AuNode> nodeMap = new HashMap<>();
    public AuthenticationManager(int timeToLive) {
        ttl = timeToLive;
        dll = new DoublyLinkList();
    }

    public void generate(String tokenId, int currentTime) {
        AuNode node = new AuNode();
        node.val = tokenId;
        node.expiry = currentTime + ttl;
        dll.addNodeToLast(node);
        nodeMap.put(tokenId, node);
        removeExpired(currentTime);
    }

    public void renew(String tokenId, int currentTime) {
        //first check if tokenid is expired
        AuNode node = nodeMap.get(tokenId);
        if(node==null || node.expiry <= currentTime){
            return;
        }

        node.expiry = currentTime + ttl;
        dll.removeNode(node);
        dll.addNodeToLast(node);
        removeExpired(currentTime);
    }

    public int countUnexpiredTokens(int currentTime) {
        removeExpired(currentTime);
        return dll.size;
    }

    void removeExpired(int currentTime){
        while(dll.head!= null && dll.head.expiry <= currentTime){
            dll.removeNode(dll.head);
        }
    }
}



class DoublyLinkList {

    AuNode head;
    AuNode tail;
    int size;

    public DoublyLinkList() {
        head = null;
        tail = null;
        size = 0;
    }

    // needed for LRU
    public void addNodeToLast(AuNode newnode) {
        // first node
        if (head == null) {
            head = newnode;
            tail = newnode;
        } else {
            // we are adding after tail
            tail.next = newnode;
            newnode.prev = tail;
            tail = newnode;
        }
        size++;
    }

    public void removeNode(AuNode node) {
        // case1
        if(node.prev != null){
            //this is not head
            node.prev.next = node.next;
        }

        if(node.next!=null){
            // this is not tail
            node.next.prev = node.prev;
        }

        if(node == head){
            head = head.next;
        }
        if(node == tail){
            tail = tail.prev;
        }
        node.prev = null;
        node.next = null;
        size--;
    }
}

class AuNode {
    public String val;
    public int expiry;
    public AuNode prev;
    public AuNode next;
    public AuNode() {}
};
