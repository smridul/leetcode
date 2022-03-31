package myanswers;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

public class SlidingWindowMedianTreeSet {



    @Test
    public void test() {

        int[] num = new int[]{1,2,3,4,2,3,1,4,2};
        int k = 3;
        for (double d : medianSlidingWindow(num, k)) {
            System.out.print(d + " ");
        }
    }

    TreeSet<Integer> max ;
    TreeSet<Integer> min ;
    public double[] medianSlidingWindow(int[] nums, int k) {

        max = new TreeSet<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if(nums[o2] == nums[o1]){
                    return o1-o2;
                }
                return Integer.compare(nums[o2], nums[o1]);
            }
        });
        min = new TreeSet<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2){
                if(nums[o2] == nums[o1]){
                    return o1-o2;
                }
                return Integer.compare(nums[o1], nums[o2]);
            }
        });

        int len = nums.length;
        double[] result = new double[len - k + 1];

        int index = 0;
        for (int i = 0; i < len; i++) {

            if (i < k) {
                add(i);
            } else {
                add(i);
                remove(i-k);
            }

            if (i >= k - 1) {
                result[index++] = median(nums);
            }

        }

        return result;
    }

    void add(int n) {
        max.add(n);
        min.add(max.pollFirst());
        if(min.size() > max.size()) {
            max.add(min.pollFirst());
        }
    }


    void remove(int n) {
        if (max.remove(n)) {
            if (max.size() < min.size()) {
                max.add(min.pollFirst());
            }
        } else {
            min.remove(n);
            if (max.size() - min.size() > 1) {
                min.add(max.pollFirst());
            }
        }
    }

    double median(int [] nums) {
        if (max.size() > min.size()) {
            return nums[max.first()];
        } else {
            return ((double) nums[max.first()] + (double) nums[min.first()]) / 2;
        }
    }
}
