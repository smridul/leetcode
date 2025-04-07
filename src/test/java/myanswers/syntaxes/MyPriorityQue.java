package myanswers.syntaxes;

import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

/**
 * Created by smridul on 6/25/18.
 */
class Point{
    int x;
    int y;
}
public class MyPriorityQue {

    @Test(expected = IllegalArgumentException.class)
    public void test() {
        PriorityQueue<Integer> list = new PriorityQueue<>(5);
        list.add(1);
        list.add(2);
        list.add(6);
        list.add(4);
        list.add(0);

        System.out.println(list.peek());


        //Comparator.reverseOrder() or Collections.reverseOrder())
        PriorityQueue<Integer> list2 = new PriorityQueue<>(5, Comparator.reverseOrder());
        list2.add(1);
        list2.add(2);
        list2.add(6);
        list2.add(4);
        list2.add(0);
        System.out.println(list2.peek());


        PriorityQueue<String> list3 = new PriorityQueue<>(5, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });


        list3.add("a");
        list3.add("dsds");
        list3.add("dd");
        list3.add("vmflvmfv");
        list3.add("p");


        // ==> order is increasing order ==> min heap
        System.out.println(list3.peek());


        PriorityQueue<String> list4 = new PriorityQueue<>(5, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });


        list4.add("a");
        list4.add("dsds");
        list4.add("dd");
        list4.add("vmflvmfv");
        list4.add("p");

        System.out.println(list4.peek());
    }

    @Test
    public void test2() throws Exception {

        //int[] array = new int[]{1, 4, 5, 7, 8, 2, 3, 0, 10}; // median 4
         int[] array = new int[]{1, 4, 5, 7, 8, 2, 3, 0, 10, 11}; // median 4.5


        PriorityQueue<Integer> maxheap = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> minheap = new PriorityQueue<>();

        // maintain maxheap has ceil(n/2) minheap has floor (n/2)


        for (int i = 0; i < array.length; i++) {
            int a = array[i];
            if (minheap.size() == 0 || a < minheap.peek()) {
                maxheap.add(a);
                // now if size of max heap is greater than min heap by 2
                if (maxheap.size() - minheap.size() > 1) {
                    minheap.add(maxheap.poll());
                }

            } else {
                minheap.add(a);
                // now if size of min heap is greater than max heap; remove element from it
                if (minheap.size() > maxheap.size()) {
                    maxheap.add(minheap.poll());
                }
            }

            System.out.print("array is [");
            for (int j = 0; j <= i; j++) {
                System.out.print(array[j] + " ");
            }
            System.out.println("]");
            System.out.println("median is " + getMedian(maxheap, minheap));

        }
    }

    double getMedian(PriorityQueue<Integer> maxheap, PriorityQueue<Integer> minheap) throws Exception {
        if (maxheap.size() > minheap.size()) {
            return maxheap.peek();
        } else if (maxheap.size() == 0 && minheap.size() == 0) {
            throw new NoSuchElementException();
        } else {
            return (double)(maxheap.peek() + minheap.peek()) / 2;
        }
    }
}
