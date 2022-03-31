package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 3/2/19.
 */
public class FindKthNumber {

    @Test
    public void test() {


        int n=100;

        for(int i=1; i<=n; i++) {
            System.out.print(findKthNumber(n, i) + " ");
        }
    }


    public int findKthNumber(int n, int k) {

        int all = 0;
        int i = 0;
        for (i = 1; i <= 9; i++) {
            int currentDigitStartingNumbers = getNumbers(n, i);

            if (all + currentDigitStartingNumbers >= k) {
                // k lies in this this
                break;
            }
            all = all + currentDigitStartingNumbers;
        }


        // now get jth number // j= all-k number
        // starting with current digit
        // series goes like 1, 11, 111, 1111,

        return getKthNumber(i, k - all);
    }


    // get all numbers <=n and start with starting digit
    public int getNumbers(int n, int startingDigit) {
        int allNumbers = 0;
        int digitsInN = (int) Math.log10(n) + 1;

        for (int i = 1; i <= digitsInN - 1; i++) {
            allNumbers += Math.pow(10, i - 1);
        }
        double k = Math.pow(10, digitsInN - 1);

        if (startingDigit * k <= n) {
            allNumbers += n % (Math.pow(10, digitsInN - 1)) + 1;
        }
        return allNumbers;
    }

    public int getKthNumber(int startingDigit, int k) {
        int allNumbers = 0;
        int a = 0;
        int i = 1;
        for (i = 1; i <= 10; i++) {
            int currentSet = (int) Math.pow(10, i - 1);
            if (currentSet + allNumbers >= k) {
                a = k - allNumbers;
                break;
            }
            allNumbers = allNumbers + currentSet;
        }
        return startingDigit * (int) Math.pow(10, i - 1) + a - 1;

    }


}
