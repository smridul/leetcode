package myanswers;

import org.junit.Test;

import java.util.*;

public class PermutationWithDuplicates {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();

        List<Integer> numList = new ArrayList<Integer>();
        for (int i : nums) {
            numList.add(i);
        }
        permuteHelperWithDuplicates(numList, 0, result);
        return result;
    }

    public void permuteHelperWithDuplicates(List<Integer> nums, int index, List<List<Integer>> result) {
        if (index == nums.size() - 1) {
            //last element
            List<Integer> perm = new ArrayList<>(nums);
            result.add(perm);
            return;
        }

        Set<Integer> positionAtThisIndex = new HashSet<>();
        for (int i = index; i < nums.size(); i++) {
            if(!positionAtThisIndex.contains(nums.get(i))) {
                positionAtThisIndex.add(nums.get(i));
                Collections.swap(nums, i, index);
                permuteHelperWithDuplicates(nums, index + 1, result);
                Collections.swap(nums, i, index);
            }
        }
    }

    @Test
    public void test() {
        int[] array = new int[]{1, 2, 2, 4};
        List<List<Integer>> result = permute(array);

        System.out.println("Total: " +result.size());
        for (List<Integer> list : result) {
            for (int i : list) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
