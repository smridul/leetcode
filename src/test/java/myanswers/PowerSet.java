package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by smridul on 7/14/18.
 */
public class PowerSet {

    @Test
    public void testPowset() {
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        input.add(5);

        List<List<Integer>> results = powset(input, 0, input.size());


        for (List<Integer> set : results) {
            for (Integer ii : set) {
                System.out.print(ii + " ");
            }
            System.out.println();
        }

        System.out.println("Total size is " + results.size());

    }


    // Don't use this solution
    public List<List<Integer>> powset(List<Integer> input, int i, int remaining) {
        if (remaining == 0) {
            List<List<Integer>> results = new ArrayList<>();
            results.add(new ArrayList<>());
            return results;
        }


        List<List<Integer>> results = powset(input, i + 1, remaining - 1);
        List<List<Integer>> results2 = new ArrayList<>();

        for (List<Integer> set : results) {

            List<Integer> set1 = new ArrayList<>(set);
            set1.add(input.get(i));
            results2.add(set1);
        }
        results2.addAll(results);
        return results2;

    }


    @Test
    public void testPowset2() {
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        input.add(5);

        List<List<Integer>> results = new ArrayList<>();
        powset2(input, new ArrayList<>(), results, 0);


        for (List<Integer> set : results) {
            for (Integer ii : set) {
                System.out.print(ii + " ");
            }
            System.out.println();
        }

        System.out.println("Total size is " + results.size());

    }


    // Solution2
    // input is 1,2, 3, 4,5,


    // select 1 and then power set of remaining
    // or don't select select 1 and power set of remaining
    public void powset2(List<Integer> input, List<Integer> selected, List<List<Integer>> results,
                        int start) {
        if (start == input.size()) {
            results.add(new ArrayList<Integer>(selected));
            return;
        }


        // first is selected
        int first = input.get(start);
        selected.add(first);

        powset2(input, selected, results, start + 1);

        // first is not selected
        int lastElementIndex = selected.size() - 1;
        selected.remove(lastElementIndex);
        powset2(input, selected, results, start + 1);
    }


    @Test
    public void testPowsetWithrepetition() {
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
     //   input.add(3);
        input.add(2);
        //input.add(5);


        HashMap<Integer, Integer> map = new HashMap();
        HashMap<Integer, Integer> selectedmap = new HashMap();
        List<List<Integer>> results = new ArrayList<>();


//        int [] nums = new int[]{1, 2, 3, 4};
//
//        input = Arrays.stream(nums)
//                .boxed()
//                .collect(Collectors.toList());

        powsetWithRepetition(input, map, selectedmap, new ArrayList<>(), results, 0);






        for (List<Integer> set : results) {
            for (Integer ii : set) {
                System.out.print(ii + " ");
            }
            System.out.println();
        }

        System.out.println("Total size is " + results.size());

    }


    // Solution2
    // input is 1,2, 3, 4,5,


    // select 1 and then power set of remaining
    // or don't select select 1 and power set of remaining
    public void powsetWithRepetition(List<Integer> input, HashMap<Integer, Integer> occurencemap, HashMap<Integer, Integer> selectedmap, List<Integer> selected, List<List<Integer>> results,
                                     int start) {
        if (start == input.size()) {
            results.add(new ArrayList<Integer>(selected));
            return;
        }

        // first is selected
        int first = input.get(start);
        int selectedCount = selectedmap.containsKey(first) ? selectedmap.get(first) : 0;
        int occurenceCount = occurencemap.containsKey(first) ? occurencemap.get(first) : 0;

        occurencemap.put(first, ++occurenceCount);


        // select only if occurence count == selectedcount +1

        boolean added= false;
        if (occurenceCount == selectedCount + 1) {
            selected.add(first);
            added = true;
            selectedmap.put(first, ++selectedCount);
            powsetWithRepetition(input, occurencemap, selectedmap, selected, results, start + 1);
        }

        // first is not selected


        int lastElementIndex = selected.size() - 1;
        if(lastElementIndex >=0 && added) {
            selectedmap.put(first, --selectedCount);
            selected.remove(lastElementIndex);
        }
        powsetWithRepetition(input, occurencemap, selectedmap, selected, results, start + 1);
        occurencemap.put(first, --occurenceCount);

    }



    @Test
    public void testsubsets(){


        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        input.add(5);

        List<List<Integer>> results = new ArrayList<>();
        subsetK(input, new ArrayList<>(), results, 0, 2, 2);

        for (List<Integer> set : results) {
            for (Integer ii : set) {
                System.out.print(ii + " ");
            }
            System.out.println();
        }

        System.out.println("Total size is " + results.size());

    }

    // wrong
    public List<List<Integer>> subsets(int[] nums, int k, List<List<Integer>> res, List<Integer> each) {

        for (int i =0 ; i<k; i++){
            each.add(nums[i]);
        }

        res.add(new ArrayList<>(each));

        // from next element continue swapping that element into each positoin of k set

        for(int i=k ; i<nums.length; i++){

            for(int j=0; j<k; j++){
                // swap array[k] with array[j]

                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                addToList(nums, k, each);
                res.add(new ArrayList<Integer>(each));

                //unswap
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }

        }
        return res;
    }

    void addToList(int[]nums, int k, List<Integer> each){
        each.clear();
        for(int i=0; i<k; i++){
            each.add(nums[i]);
        }
    }


    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> each = new ArrayList<>();
        helper(res, each, 0, nums);
        return res;
    }
    public void helper(List<List<Integer>> res, List<Integer> each, int pos, int[] n) {
        if (pos <= n.length) {
            res.add(each);
        }
        int i = pos;
        while (i < n.length) {
            each.add(n[i]);
            helper(res, new ArrayList<>(each), i + 1, n);
            each.remove(each.size() - 1);
            i++;
            while (i < n.length && n[i] == n[i - 1]) {i++;}
        }
        return;
    }


    public void subsetK(List<Integer> input, List<Integer> selected, List<List<Integer>> results,
                        int start, int k, int remaining) {
        if(start +remaining-1 > input.size()-1){
            return;
        }
        if (selected.size() == k ) {
            results.add(new ArrayList<Integer>(selected));
            return;
        }


        // first is selected
        int first = input.get(start);
        selected.add(first);

        subsetK(input, selected, results, start + 1, k, remaining-1);

        // first is not selected
        int lastElementIndex = selected.size() - 1;
        selected.remove(lastElementIndex);
        subsetK(input, selected, results, start + 1, k, remaining);
    }

}