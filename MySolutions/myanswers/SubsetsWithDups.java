package myanswers;

import org.junit.Test;

import java.util.*;

public class SubsetsWithDups {

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        subsetsWithDupHelper(nums,  0, result, new ArrayList<>(), -1);
        return result;
    }


    public void subsetsWithDupHelper(int[] candidates, int index, List<List<Integer>> result, List<Integer> current, int lastSelect) {

        if (index == candidates.length) {
            result.add(new ArrayList<>(current));
            return;
        }
        boolean added = false;

        int temp = lastSelect;
        if (index == 0 || candidates[index] != candidates[index - 1] || lastSelect == index - 1) {
            current.add(candidates[index]);
            lastSelect = index;
            added = true;
            subsetsWithDupHelper(candidates, index + 1, result, current, lastSelect);
        }

        //case 2 selected nums[i] not selected
        if (added) {
            current.remove(current.size() - 1);
        }
        lastSelect = temp;
        subsetsWithDupHelper(candidates, index + 1, result, current, lastSelect);
    }

    @Test
    public void test() {

        int[] nums = new int[]{1, 2, 2, 4};
        List<List<Integer>> res = subsetsWithDup(nums);

        System.out.println(res.size());

        for (List l : res) {
            System.out.println(l);
        }
    }

}
