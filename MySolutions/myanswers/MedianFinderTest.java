package myanswers;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFinderTest {
    @Test
    public void test(){
        MedianFinder medianFinder = new MedianFinder();
        medianFinder.addNum(1);
        System.out.println(medianFinder.findMedian());

        medianFinder.addNum(2);
        System.out.println(medianFinder.findMedian());

        medianFinder.addNum(3);
        System.out.println(medianFinder.findMedian());


    }
}
