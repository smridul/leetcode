package myanswers;

import org.junit.Test;

import java.util.Arrays;
import java.util.Stack;

public class TrappingRainWater {

    public int trap2(int[] height) {
        int lmax = 0;
        int rmax = 0;
        int left = 0;
        int right = height.length - 1;
        int water = 0;
        while (left < right) {

            if (height[left] <= height[right]) {
                int contribution = Math.max(lmax - height[left], 0);
                water += contribution;
                lmax = Math.max(lmax,height[left]);
                left++;
            } else {
                int contribution = Math.max(rmax - height[right], 0);
                water += contribution;
                rmax = Math.max(rmax,height[right]);
                right--;
            }
        }
        return water;
    }

    public int trap(int[] height) {
        int lmax[] = new int[height.length];
        int rmax[] = new int[height.length];

        int max = height[0];
        for (int i = 0; i < height.length; i++) {

            if (height[i] < max) {
                lmax[i] = max;
            }
            max = Math.max(max, height[i]);
        }

        //{0,1,0,2,1,0,1,3,2,1,2,1};


        max = height[height.length - 1];
        for (int i = height.length - 1; i >= 0; i--) {
            if (height[i] < max) {
                rmax[i] = max;
            }
            max = Math.max(max, height[i]);
        }

        int water = 0;
        for (int i = 0; i < height.length; i++) {
            int heightOfCell = Math.min(lmax[i], rmax[i]);
            int contribution = Math.max(heightOfCell - height[i], 0);
            water = water + contribution;
        }

        return water;
    }

    public int trap1(int[] height) {

        int lmax[] = new int[height.length];
        int rmax[] = new int[height.length];

        Arrays.fill(lmax, -1);
        Arrays.fill(rmax, -1);

        Stack<Integer> stack = new Stack<>();
        // pushing values not indices
        for (int i = 0; i < height.length; i++) {

            while (!stack.isEmpty() && height[i] >= stack.peek()) {
                stack.pop();
            }

            if (!stack.isEmpty()) {
                lmax[i] = stack.peek();
            }
            stack.push(height[i]);
        }


        stack = new Stack<>();
        // pushing values not indices
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > stack.peek()) {
                int j = stack.pop();
                rmax[j] = height[i];
            }
            stack.push(height[i]);
        }


        int water = 0;
        for (int i = 0; i < height.length; i++) {
            int heightOfCell = Math.min(lmax[i], rmax[i]);
            int contribution = Math.max(heightOfCell - height[i], 0);
            water = water + contribution;
        }

        return water;
    }

    @Test
    public void test() {
        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trap2(height));
    }
}
