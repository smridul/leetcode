package myanswers;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class SlidingWindowMedian {

    PriorityQueue<Integer> min = new PriorityQueue<>();
    PriorityQueue<Integer> max = new PriorityQueue<>(Comparator.reverseOrder());


    @Test
    public void test() {

        int[] num = new int[]{1, 2, 3, 4, 2, 3, 1, 4, 2};
        int k = 3;
        for (double d : medianSlidingWindow(num, k)) {
            System.out.print(d + " ");
        }
    }

    public double[] medianSlidingWindow(int[] nums, int k) {


        int len = nums.length;
        double[] result = new double[len - k + 1];

        int index = 0;
        for (int i = 0; i < len; i++) {

            if (i < k) {
                add(nums[i]);
            } else {
                add(nums[i]);
                remove(nums[i - k]);
            }

            if (i >= k - 1) {
                result[index++] = median();
            }

        }

        return result;
    }

    void add(int n) {
        max.offer(n);
        min.offer(max.poll());
        if (min.size() > max.size()) {
            max.offer(min.poll());
        }
    }

    void remove(int n) {
        if (n <= max.peek()) {
            max.remove(n);
            if (max.size() < min.size()) {
                max.offer(min.poll());
            }
        } else {
            min.remove(n);
            if (max.size() - min.size() > 1) {
                min.offer(max.poll());
            }
        }
    }

    double median() {
        if (max.size() > min.size()) {
            return max.peek();
        } else {
            return ((double) max.peek() + (double) min.peek()) / 2;
        }
    }

}
