package myanswers;

import org.junit.Test;

import java.util.*;

public class SparseVector {

    Map<Integer, Integer> map;

    SparseVector(int[] nums) {
        map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                map.put(i, nums[i]);
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        Set<Integer> set1 = map.keySet();
        Set<Integer> set2 = vec.map.keySet();


        Set<Integer> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);

        Iterator<Integer> iter = intersection.iterator();
        int sum = 0;
        while (iter.hasNext()) {
            int pos = iter.next();
            sum = sum + map.get(pos) + vec.map.get(pos);
        }
        return sum;
    }

    public static void main(String[] args){
        int[] num1 = new int[]{1,0,0,2,3};
        int[] num2 = new int[]{0,3,0,4,0};
        SparseVector sparseVector1 = new SparseVector(num1);

        sparseVector1.dotProduct(new SparseVector(num2));


    }
}
