package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MinMovesToMakeEqual {
    public int minMoves(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int i : nums) {
            list.add(i);
        }

        Collections.sort(list);
        int max = list.get(list.size() - 1);
        int min = list.get(0);

        int totalSteps = 0;
        while (min != max) {
            //remove max
            list.remove(list.size() - 1);
            int steps = max - min;
            totalSteps = totalSteps + steps;

            max = list.get(list.size() - 1);
            min = list.get(0);
        }

        return totalSteps;
    }

    @Test
    public void test(){
        int [] nums = new int[]{1,3, 5,6};
        System.out.println(minMoves(nums));
    }
}
