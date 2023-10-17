package myanswers;

import org.junit.Test;

import java.util.*;

public class KclosestIntegers {



    int minDistance(int start, int[] arr, int x, int k) {
        int end = start + k - 1;
        return Math.min(Math.abs(arr[start] - x), Math.abs(arr[end] - x));
    }
    public List<Integer> findClosestElements(int[] arr, int k, int x) {

        int left = 0;
        int right = arr.length - k;

        // Binary search against the criteria described
        while (left < right) {
            int mid = (left + right) / 2;
            if (x - arr[mid] > arr[mid + k] - x) {
                left = mid + 1;
            } else {
                right = mid-1;
            }
        }

        // Create output in correct format
        List<Integer> result = new ArrayList<Integer>();
        for (int i = left; i < left + k; i++) {
            result.add(arr[i]);
        }

        return result;
    }

    List<Integer> createList(int[] arr, int low, int k) {
        List<Integer> res = new ArrayList<>();

        while (k-- > 0) {
            res.add(arr[low++]);
        }
        return res;
    }

    public List<Integer> findClosestElements1(int[] arr, int k, int x) {

        PriorityQueue<Integer> pq = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (Math.abs(arr[o2] - x) == Math.abs(arr[o1] - x)) {
                    return o2 - o1;
                }
                return Integer.compare(Math.abs(arr[o2] - x), Math.abs(arr[o1] - x));
            }
        });

        for (int i = 0; i < arr.length; i++) {

            if (pq.size() == k && Math.abs(arr[i] - x) > Math.abs(arr[pq.peek()] - x)) {
                break;
            }

            pq.offer(i);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        List<Integer> result = new ArrayList<>();
        while (pq.size() > 0) {
            result.add(arr[pq.poll()]);
        }
        Collections.sort(result);
        return result;
    }


    @Test
    public void test() {

        int[] arr = new int[]{0,2,2,3,4,6,7,8,9,9};
        List<Integer> res = findClosestElements(arr, 4, 5);
        for (int s : res) {
            System.out.print(s + " ");
        }

    }
}
