package myanswers.easy;

import org.junit.Test;

public class ReverseBits {
    public int reverseBits(int n) {

        int k = 32;
        int position = 1;
        int setbit;
        int reverse=0;
        while (k-- > 0) {
            int msb = n & 0x80000000;
            setbit = msb == 0x80000000 ? position : 0;
            n = n << 1;
            reverse = reverse | setbit;
            position *= 2;
        }
        return reverse;

    }

    @Test
    public void test() {
        int n = 0x80000001;
        String s = Integer.toBinaryString(n);
        int leading = 32 - s.length();
        while (leading-- > 0) {
            System.out.print(0);
        }
        System.out.println(s);

        n = n >>> 1;


        s = Integer.toBinaryString(n);
        leading = 32 - s.length();
        while (leading-- > 0) {
            System.out.print(0);
        }
        System.out.println(s);

    }
}
