package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 1/2/19.
 */
public class Exponential {


    @Test
    public void xpowery() {
        int x = 3;
        int y = 7;

        System.out.println("x power y is " + Math.pow(x, y));


        int result = 1;
        int sum = x;
        while (y != 0) {

            int ybit = y & 1;
            y = y >>> 1;

            if (ybit == 1) {
                result = result * sum;
            }

            // logic is that hume double to krte jana hai. ab multiply karna hia ya nhi depend on the bit
            // 7 =  (1) 2^0 + (1)2^1 + (1)2^2
            // we are doubling each time but to add or not depends on bit of 7
            sum = sum * sum;

        }

        System.out.println("x power y is " + result);

    }
}
