package myanswers;


import java.util.Comparator;
import java.util.PriorityQueue;

class MedianFinder {

    PriorityQueue<Integer> maxHeap;
    PriorityQueue<Integer> minHeap;

    public MedianFinder() {
        minHeap = new PriorityQueue<>();
        maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    }

    public void addNum2(int num) {
        boolean isEven = (minHeap.size() + maxHeap.size()) % 2 == 0;
        if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
            // in first set
            maxHeap.add(num);
            if (!isEven) {
                int a = maxHeap.poll();
                minHeap.add(a);
            }
        } else {
            // or in second set
            minHeap.add(num);
            if (isEven) {
                int a = minHeap.poll();
                maxHeap.add(a);
            }
        }
    }


    //better
    public void addNum(int num) {

        minHeap.add(num);
        maxHeap.add(minHeap.poll());

        if(maxHeap.size() == minHeap.size()+2){
            minHeap.add(maxHeap.poll());
        }
    }

    public double findMedian() {
        int a = maxHeap.isEmpty() ? 0 : maxHeap.peek();
        int b = minHeap.isEmpty() ? 0 : minHeap.peek();
        boolean isEven = (minHeap.size() + maxHeap.size()) % 2 == 0;
        if (isEven) {
            return (double) (a + b) / 2;
        } else {
            return a;
        }
    }
}
