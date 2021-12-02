package myanswers;

import org.junit.Test;

import java.util.*;

public class CombinationsII {
    public List<List<Integer>> combine(int n, int k) {

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        List<List<Integer>> result = new ArrayList<>();
        combineHelper(nums, k, new ArrayList<>(), result, 0);
        return result;
    }

    public void combineHelper(int[] nums, int k, List<Integer> current, List<List<Integer>> result, int index) {

        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (index == nums.length) {
            return;
        }

        current.add(nums[index]);
        combineHelper(nums, k, current, result, index + 1);

        current.remove(current.size() - 1);
        combineHelper(nums, k, current, result, index + 1);
    }

    @Test
    public void test() {
        int[] nums = new int[]{1, 2, 2, 4, 5};
        List<List<Integer>> res = combine(5, 3);

        System.out.println(res.size());

        for (List l : res) {
            System.out.println(l);
        }
    }

    @Test
    public void test2() {
        int[] nums = new int[]{1, 2, 2, 4, 5};
        List<List<Integer>> res = combineWithDups(nums, 3);

        System.out.println(res.size());

        for (List l : res) {
            System.out.println(l);
        }
    }

    public List<List<Integer>> combineWithDups(int[] nums, int k) {

        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        combineHelperDups(nums, k, new ArrayList<>(), result, 0, -1);
        return result;
    }

    public void combineHelperDups(int[] nums, int k, List<Integer> current, List<List<Integer>> result, int index, int lastSelect) {

        if (current.size() == k) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (index == nums.length) {
            return;
        }


        boolean added = false;

        int temp = lastSelect;
        if (index == 0 || nums[index] != nums[index - 1] || lastSelect == index - 1) {
            current.add(nums[index]);
            lastSelect = index;
            added = true;
            combineHelperDups(nums, k, current, result, index + 1, lastSelect);
        }

        //case 2 selected nums[i] not selected
        if (added) {
            current.remove(current.size() - 1);
        }
        lastSelect = temp;

        combineHelperDups(nums, k, current, result, index + 1, lastSelect);
    }
}
