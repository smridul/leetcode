package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by smridul on 3/9/19.
 */
public class CoinPath {

    @Test
    public void test() {


        int[] A = new int[]{0,0,0,0,0,0};
        int B = 3;
        List<Integer> res = cheapestJump(A, B);
        for (int i : res) {
            System.out.print(i + " ");
        }
    }

    public List<Integer> cheapestJump(int[] A, int B) {


        if (A == null || A.length == 0) {
            return new ArrayList<>();
        }
        int[] dp = new int[A.length];
        int[] path = new int[A.length];
        Arrays.fill(dp, -1);
        Arrays.fill(path, -1);
        dp[0] = 0;

        LinkedList<Integer> result = new LinkedList<>();

        for (int i = 1; i < A.length; i++) {
            int k = B;
            int min = Integer.MAX_VALUE;
            while (k != 0 && A[i]!=-1) {
                if (i - k >= 0 && dp[i - k] != -1) {
                    int weight = dp[i - k] + A[i];

                    if (weight < min) {
                        min = weight;
                        path[i] = i- k;
                        dp[i] = weight;
                    } else if (min == weight && i-k > path[i]) {
                        path[i] = i-k;
                    }
                }
                k--;
            }
        }


        // construct the result from path
        int k = A.length - 1;
        if (dp[k] == -1) {
            return new ArrayList<>();
        } else {

            while (k != -1) {
                result.addFirst(k + 1);
                k = path[k];
            }
        }
        return result;
    }
}
