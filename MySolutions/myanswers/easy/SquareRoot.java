package myanswers.easy;

import org.junit.Test;

public class SquareRoot {
    public int mySqrt(int x) {

        if (x < 2) {
            return x;
        }

        int l = 0;
        int h = x;
        return recurse(1, h, x);
    }

    int recurse(int l, int h, int x) {
        if (l == h) {
            return l;
        }
        int prev = l;
        int seed = 1;
        int base = l;
        while (l < x / l) {
            prev = l;
            l = base + seed;
            seed = seed * 2;
        }
        if (l == x / l) {
            return l;
        } else {
            return recurse(prev, l - 1, x);
        }
    }


    @Test
    public void test() {
        System.out.println(mySqrt(4));
    }
}
