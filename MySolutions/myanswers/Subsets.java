package myanswers;

import org.junit.Test;

import java.util.*;

public class Subsets {

    public List<List<Integer>> subsets(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();

        subsets2(nums, result, 0, new ArrayList<>());
        return result;
    }


    private List<List<Integer>> subsets1(int[] nums, List<List<Integer>> result, int index) {

        if (index == nums.length) {
            result.add(new ArrayList());
            return result;
        }
        List<List<Integer>> allList = subsets1(nums, result, index + 1);

        result = new ArrayList<>();
        for (List<Integer> l : allList) {
            List<Integer> newList = new ArrayList<>(l);
            newList.add(nums[index]);
            result.add(newList);
        }
        result.addAll(allList);

        return result;
    }

    private void subsets2(int[] nums, List<List<Integer>> result, int index, List<Integer> current) {

        if (index == nums.length) {
            result.add(new ArrayList(current));
            return;
        }

        current.add(nums[index]);
        subsets2(nums, result, index + 1, current);

        current.remove(current.size()-1);
        subsets2(nums, result, index + 1, current);

        return;
    }

    @Test
    public void test() {

        int[] nums = new int[]{1, 2, 3, 4};
        List<List<Integer>> res = subsets(nums);

        System.out.println(res.size());

        for (List l : res) {
            System.out.println(l);
        }
    }


}
