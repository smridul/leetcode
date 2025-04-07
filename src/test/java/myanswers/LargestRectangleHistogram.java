package myanswers;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class LargestRectangleHistogram {

    public int largestRectangleArea(int[] heights) {

        Deque<Integer> stack = new ArrayDeque<>();
        int max = 0;
        for (int i = 0; i < heights.length; i++) {

            int top = 0;
            if (!stack.isEmpty()) {
                top = stack.getLast();
            }

            while (!stack.isEmpty() && heights[stack.getLast()] > heights[i]) {
                int j = stack.removeLast();
                // right boundary is correct which is top

                // but left boundary is next top i,e the current value on top. We already removed
                // in line above, the element of which we are calculating boundary so

                int left = stack.isEmpty() ? -1 : stack.getLast();

                int area = (top - left) * heights[j];
                max = Math.max(max, area);
            }
            stack.addLast(i);
        }


        int lastIndex  = heights.length-1;
        while (!stack.isEmpty()) {
            int j = stack.removeLast();
            int right = lastIndex;
            int left = stack.isEmpty() ? -1 : stack.getLast();
            int area = (right - left) * heights[j];
            max = Math.max(area, max);
        }

        return max;
    }


    @Test
    public void test() {

        int[] num = new int[]{3, 4, 5};
        int res = largestRectangleArea(num);
        System.out.println(res);

        int x = -7;
        System.out.println(x << 1);

    }

}
