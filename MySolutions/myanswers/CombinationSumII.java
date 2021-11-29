package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CombinationSumII {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {


        Map<Integer, Integer> countMap = new HashMap<>();
        Map<Integer, Integer> countMapIndex = new HashMap<>();

        for (int i = 0; i < candidates.length; i++) {
            countMap.put(candidates[i], countMap.getOrDefault(candidates[i], 0) + 1);
            countMapIndex.put(i, countMap.get(candidates[i]));
        }


        List<List<Integer>> result = new ArrayList<>();
        combinationSumRecursionHelper2(candidates, target, 0, result, new ArrayList<>(), new HashMap<>(), countMapIndex);
        int a = 0;
        return result;
    }


    // each number can be used multiple times
    public void combinationSumRecursionHelper(int[] candidates, int target, int index,
                                              List<List<Integer>> result, List<Integer> current, Map<Integer, Integer> map) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (index == candidates.length) {
            return;
        }
        //case 1 selected nums[i]

        boolean added = false;
        if (!map.containsKey(candidates[index]) || map.get(candidates[index]) == index) {
            current.add(candidates[index]);
            added = true;
            map.put(candidates[index], index);
            combinationSumRecursionHelper(candidates, target - candidates[index], index, result, current, map);
        }

        //case 2 selected nums[i] not selected
        if (added == true) {
            current.remove(current.size() - 1);
        }
        combinationSumRecursionHelper(candidates, target, index + 1, result, current, map);
    }


    // each number can be used 1 times
    public void combinationSumRecursionHelper2(int[] candidates, int target, int index,
                                               List<List<Integer>> result, List<Integer> current, Map<Integer, Integer> currentCountMap, Map<Integer, Integer> countMapIndex) {
        if (target < 0) {
            return;
        }
        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }
        if (index == candidates.length) {
            return;
        }
        boolean added = false;

        if (countMapIndex.get(index) == currentCountMap.getOrDefault(candidates[index], 0) +1) {
            current.add(candidates[index]);
            currentCountMap.put(candidates[index], currentCountMap.getOrDefault(candidates[index], 0)+1);
            added = true;
            combinationSumRecursionHelper2(candidates, target - candidates[index], index + 1, result, current, currentCountMap, countMapIndex);
        }

        //case 2 selected nums[i] not selected
        if (added == true) {
            current.remove(current.size() - 1);
            currentCountMap.put(candidates[index], currentCountMap.getOrDefault(candidates[index], 1)-1);
        }
        combinationSumRecursionHelper2(candidates, target, index + 1, result, current, currentCountMap, countMapIndex);
    }

    @Test
    public void test() {

        int[] nums = new int[]{10, 1, 2, 7, 6, 1, 5};
        List<List<Integer>> res = combinationSum(nums, 8);

        for (List l : res) {
            System.out.println(l);
        }
    }
}

class Pair1 {
    int firstOccurence;
    int index;

    public Pair1(int a, int b) {
        firstOccurence = a;
        index = b;
    }
}
