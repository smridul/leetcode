package myanswers;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {

    public int[] maxSlidingWindow(int[] nums, int k) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] result = new int[nums.length - k + 1];
        int j=0;
        for (int i = 0; i <= nums.length - 1; i++) {
            while (!deque.isEmpty() && nums[i] > nums[deque.getLast()]) {
                deque.removeLast();
            }
            deque.addLast(i);
            if (deque.getFirst() < i - k + 1) {
                deque.removeFirst();
            }
            if (i >= k - 1) {
                result[j++] = nums[deque.getFirst()];
            }
        }
        return result;
    }

    @Test
    public void test() {
        int a[] = new int[]{1, 3, -1, -3, 5, 3, 6, 7};
        int[] res = maxSlidingWindow(a, 1);
        int b=0;
    }
}
