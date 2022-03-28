package myanswers.concurrency;

import org.junit.Test;

import java.util.Arrays;

public class BlockingQueueTest {

    @Test
    public void test() {

        BoundedBlockingQueue queue = new BoundedBlockingQueue(2);


        ConsumerThread consumerThread1 = new ConsumerThread(queue);
        ConsumerThread consumerThread2 = new ConsumerThread(queue);

        ProducerThread producerThread = new ProducerThread(queue);

        consumerThread1.start();
        consumerThread2.start();

        System.out.println("end");

    }

    @Test
    public void test2()throws Exception {

        BoundedBlockingQueue queue = new BoundedBlockingQueue(2);
        queue.dequeue();
        queue.dequeue();


    }


}

class ConsumerThread extends Thread {
    BoundedBlockingQueue queue;

    ConsumerThread(BoundedBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            queue.dequeue();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class ProducerThread extends Thread {
    BoundedBlockingQueue queue;

    ProducerThread(BoundedBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            queue.enqueue(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}