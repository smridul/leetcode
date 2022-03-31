package myanswers;

import org.junit.Test;

import java.util.*;

public class CombinationSum {
    public List<List<Integer>> combinationSumDpDifferentView(int[] candidates, int target) {
        List<List<Integer>> dp[][] = new List[2][target + 1];
        for (int i = 0; i <= target; i++) {
            dp[0][i] = new ArrayList<>();
        }
        dp[0][0].add(new ArrayList<>());

        for (int i = 0; i < candidates.length; i++) {
            for (int j = 0; j <= target; j++) {
                //dp[i+1][j]
                List<List<Integer>> result = new ArrayList<>();
                // case 1 if a[i] is selected
                if (j >= candidates[i]) {
                    List<List<Integer>> alllists = dp[1][j - candidates[i]];

                    for (List l : alllists) {
                        List<Integer> newList = new ArrayList<>(l);
                        newList.add(candidates[i]);
                        result.add(newList);
                    }
                }

                // case 2 if a[i] is not selected
                List<List<Integer>> alllists2 = dp[0][j];
                result.addAll(alllists2);

                dp[1][j] = result;
            }

            dp[0] = dp[1];
        }
        return dp[1][target];
    }


    public List<List<Integer>> combinationSumDp(int[] candidates, int target) {
        List<List<Integer>> dp[][] = new List[candidates.length + 1][target + 1];
        for (int i = 0; i <= target; i++) {
            dp[0][i] = new ArrayList<>();
        }
        dp[0][0].add(new ArrayList<>());
        for (int i = 0; i < candidates.length; i++) {
            for (int j = 0; j <= target; j++) {
                //dp[i+1][j]
                List<List<Integer>> result = new ArrayList<>();
                for (int a = 0; a * candidates[i] <= j; a++) {

                    List<List<Integer>> alllists = dp[i][j - a * candidates[i]];
                    List<Integer> newTail = new ArrayList<>();
                    for (int m = 1; m <= a; m++) {
                        newTail.add(candidates[i]);
                    }

                    for (List l : alllists) {
                        List<Integer> newList = new ArrayList<>(l);
                        newList.addAll(newTail);
                        result.add(newList);
                    }
                }
                dp[i + 1][j] = result;
            }
        }
        return dp[candidates.length][target];
    }

    public List<List<Integer>> combinationSumRecursion(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();
        combinationSumRecursionHelper(candidates, target, 0, result, new ArrayList<>());
        int a = 0;
        return result;
    }

    public void combinationSumRecursionHelper(int[] candidates, int target, int index,
                                              List<List<Integer>> result, List<Integer> current) {

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

        for (int i = 0; i * candidates[index] <= target; i++) {
            List<Integer> tail = new ArrayList<>();
            for (int m = 1; m <= i; m++) {
                tail.add(candidates[index]);
            }

            current.addAll(tail);
            combinationSumRecursionHelper(candidates, target - i * candidates[index], index + 1, result, current);
            current.removeAll(tail);
        }
    }


    public List<List<Integer>> combinationSumRecursion2(int[] candidates, int target) {

        List<List<Integer>> result = new ArrayList<>();
        combinationSumRecursionHelper2(candidates, target, 0, result, new ArrayList<>());
        int a = 0;
        return result;
    }

    public void combinationSumRecursionHelper2(int[] candidates, int target, int index,
                                              List<List<Integer>> result, List<Integer> current) {
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
        current.add(candidates[index]);
        combinationSumRecursionHelper2(candidates, target-candidates[index], index, result, current);

        //case 2 selected nums[i] not selected
        current.remove(current.size()-1);
        combinationSumRecursionHelper2(candidates, target, index+1, result, current);
    }

    @Test
    public void test() {

        int[] nums = new int[]{2, 3, 7};
        List<List<Integer>> res = combinationSumRecursion2(nums, 7);

        for (List l : res) {
            System.out.println(l);
        }
    }

    public List<List<Integer>> combinationSumLeetCode(int[] candidates, int target) {
        List<List<Integer>>[] dp = new List[target + 1];
        for (int i = 0; i <= target; i++)
            dp[i] = new ArrayList<>();
        dp[0].add(new ArrayList<>());
        for (int candidate : candidates) {
            for (int j = candidate; j <= target; j++) {
                for (List<Integer> comb : dp[j - candidate]) {
                    List<Integer> newComb = new ArrayList(comb);
                    newComb.add(candidate);
                    dp[j].add(newComb);
                }
            }
        }
        return dp[target];
    }

}
