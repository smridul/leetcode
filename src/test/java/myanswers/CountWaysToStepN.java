package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 7/16/18.
 */
public class CountWaysToStepN {


    @Test
    public void testSteps() {


        int n=10;
        int k=3;
        System.out.println(waystostep(n, k, new int[n+1]));
        System.out.print(waystostep(n, k, new int[n+1]));


    }


    public int waystostep(int n, int k, int[] cache) {

        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            return 0;
        }

        if (cache[n] != 0) {
            return cache[n];
        }

        int count = 0;
        for (int i = 1; i <= k; i++) {
            count += waystostep(n - i, k, cache);
        }

        cache[n] = count;
        return count;
    }


    public int waystostepbook(int n, int k, int[] cache) {

        if (n <= 1) {
            return 1;
        }


        int count = 0;
        if (cache[n] == 0) {
            for (int i = 1; i <= k && n - i >= 0; i++) {
                count += waystostep(n - i, k, cache);
            }

        }

        cache[n] = count;
        return count;

    }
}
