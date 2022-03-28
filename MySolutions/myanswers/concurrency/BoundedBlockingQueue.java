package myanswers.concurrency;


import java.util.*;

public class BoundedBlockingQueue {

    int capacity;
    Queue<Integer> q;

    public BoundedBlockingQueue(int capacity) {
        this.capacity = capacity;
        q = new LinkedList<>();
    }


    public synchronized void enqueue(int element) throws InterruptedException {
        System.out.println("inside enque");

        if (q.size() == capacity) {
            wait();
        }

        q.add(element);
        notifyAll();
    }


    public synchronized int dequeue() throws InterruptedException {

        System.out.println("inside deque");
        if (q.size() == 0) {
            System.out.println("inside deque waiting");
            wait();
        }

        int element = q.poll();
        notifyAll();
        System.out.println("quit deque");

        return element;
    }

    public int size() {
        return q.size();
    }
}