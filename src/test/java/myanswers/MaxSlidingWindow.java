package myanswers;

import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by smridul on 1/2/19.
 */
public class MaxSlidingWindow {


    @Test
    public void test(){
        int[] nums = new int[] {1, 3, 1, 2, 0, 5};
        int k = 3;
        int [] max= maxSlidingWindow(nums, k);
        for(int i: max){
            System.out.print(i + " ");
        }
    }
    public int[] maxSlidingWindow(int[] nums, int k) {

        QueuewithMax queuewithMax = new QueuewithMax();

        for(int i=0; i< k; i++){
            queuewithMax.add(nums[i]);
        }

        int[] max = new int[nums.length-k+1];
        max[0] = queuewithMax.getMax();

        for(int i=1; i < nums.length-k+1; i++){
            queuewithMax.add(nums[i+k-1]);
            queuewithMax.remove();
            max[i] = queuewithMax.getMax();
        }
        return max;
    }
}

class QueuewithMax {

    Deque<Integer> queue = new LinkedList<>();
    Deque<Integer> maxQueue = new LinkedList<>();


    public void add(int a) {
        queue.offer(a);


        while (!maxQueue.isEmpty() && a > maxQueue.getLast()) {
            maxQueue.removeLast();
        }
        maxQueue.offer(a);
    }

    public void remove() {
        if (!queue.isEmpty()) {
            int a = queue.poll();

            if (a == maxQueue.peek()) {
                maxQueue.poll();
            }
        }
    }

    public int getMax() {
        if(maxQueue.isEmpty()){
            return Integer.MIN_VALUE;
        }
        return maxQueue.peek();
    }

}

