package myanswers;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by smridul on 2/16/19.
 */
public class RussianDoll {

    @Test
    public void test() {

        List a = Arrays.asList("12", "24");
        int[] arr1 = new int[]{2, 3, 4, 5, 6, 7, 6, 6, 8, 10, 11};
        int index = Arrays.binarySearch(arr1, 0, 0, 7);
        System.out.println(index);


        int[][] env = new int[][]{{5, 4}, {6, 4}, {6, 7}, {2, 3}};
        System.out.println(maxEnvelop(env));
    }


    int maxEnvelop(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0
                || envelopes[0] == null || envelopes[0].length != 2)
            return 0;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] arr1, int[] arr2) {
                if (arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                else
                    return arr1[0] - arr2[0];
            }
        });
        int dp[] = new int[envelopes.length];
        int len = 0;
        for (int[] envelope : envelopes) {

            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            if (index < 0) {
                index = -(index + 1);
            }
            dp[index] = envelope[1];
            if (index == len) {
                len++;
            }
        }
        return len;
    }
}


