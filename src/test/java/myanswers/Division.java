package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 6/12/18.
 */
public class Division {
    @Test
    public void test() {

        int dividend = Integer.MIN_VALUE;
        int divisor = -1;

        int result = devideAll(dividend, divisor);
        System.out.println(result);
    }

    public int devideAll(int dividend, int divisor) {
        if (dividend == 0) return 0;
        boolean needTofFlipTheSign = (dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0);

        // lets make them both negative
        if (dividend > 0) {
            dividend = ~dividend + 1;
        }

        if (divisor > 0) {
            divisor = ~divisor + 1;
        }

        int result = devideNegative(dividend, divisor);

        if (result < 0) {
            // overflow has happened;

            // but if sign of result is negative
            if (needTofFlipTheSign) {
                return Integer.MIN_VALUE;
            } else {
                return Integer.MAX_VALUE;
            }
        }

        if (needTofFlipTheSign) {
            // change back the sign
            result = ~result + 1;
        }
        return result;
    }

    public int devideNegative(int dividend, int divisor) {
        int result = 0;
        int delta = divisor;
        int sum = 0;
        int factor = 1;


        // we are going in -ve direction so sum should always be -ve
        // all comparisions are reversed so have number line in mind

        while (sum + divisor >= dividend && (sum + divisor<0)) {

            // sum + delta> 0 overflow
            if (sum + delta < dividend ||  sum + delta> 0) {

               // lower the delta down==> devide by 2 untill sum is under the range

                while(sum + delta < dividend ||  sum + delta > 0){
                    delta >>=1;// preserve the sign dont use >>>
                    factor >>=1;
                }

                /* or just ignore the while loop and use this ==> down to beginning
                delta = divisor;
                factor = 1;
                */
            }

            sum = sum + delta;
            result = result + factor;


            if(factor<<1 >= 0) {
                factor += factor;
                delta += delta;
            }
        }

        return result;
    }
}
