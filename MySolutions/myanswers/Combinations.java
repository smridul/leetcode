package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by smridul on 7/13/18.
 */
public class Combinations {

    @Test
    public void testCom() {
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        input.add(5);
        input.add(6);

        List<List<Integer>> combs = combinations1(input, 1);


        for (List<Integer> set : combs) {
            for (Integer ii : set) {
                System.out.print(ii + " ");
            }
            System.out.println();
        }

        System.out.println("Total size is " + combs.size());

    }



    // generating the set of indexes not the actual elements
    // To convert it into actual element just map the index to actual elements
    // non repetitive
    // for k > 0
    public List<List<Integer>> combinations1(List<Integer> input, int k) {

        if (k == 1) {
            List<List<Integer>> result = new ArrayList<>();

            for (int i = 0; i < input.size(); i++) {
                result.add(Arrays.asList(i));
            }
            return result;
        }

        List<List<Integer>> combNminus1 = combinations1(input, k - 1);
        List<List<Integer>> result = new ArrayList<>();
        for (List<Integer> set : combNminus1) {

            for (int i = set.get(set.size() - 1) + 1; i < input.size(); i++) {
                List newSet = new ArrayList<Integer>(set);
                newSet.add(i);
                result.add(newSet);
            }
        }
        return result;
    }




    @Test
    public void testCom2() {
        List<Integer> input = new ArrayList<>();
        input.add(1);
        input.add(2);
        input.add(3);
        input.add(4);
        input.add(5);
        input.add(6);
        List<List<Integer>> results = new ArrayList<>();

        combinations2(input, 2, 2, 0, new ArrayList<>(), results);


        for (List<Integer> set : results) {
            for (Integer ii : set) {
                System.out.print(ii + " ");
            }
            System.out.println();
        }

        System.out.println("Total size is " + results.size());
    }


    // non repetitive
    public void combinations2(List<Integer> input, int k, int remaining, int start, List<Integer> partial,
                              List<List<Integer>> result) {

        if (partial.size() == k) {
            result.add(new ArrayList<Integer>(partial));
            return;
        }

        // either the first element is included or it is not
        // if included then select k-1 from remaning input
        // if not included then select k from remaining input



        // word length from start to last position is lastindex-firstindex+1
        // hence k should be less than or equal to that length
        // if string length is 4 and remaining is 5
        // that is impossible to form
        // btw even if we don't include that condition it will work but just that it
        // will iterate more


        // if (; start < input.size(); ) { // will work too
        if( start < input.size() &&  remaining <= input.size()-1-start+1 ) {
            int first = input.get(start);
            partial.add(first);
            combinations2(input, k, remaining - 1, start + 1, partial, result);

            int lastElementIndex = partial.size() - 1;
            partial.remove(lastElementIndex);
            combinations2(input, k, remaining, start + 1, partial, result);
        }
    }

}
