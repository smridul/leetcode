package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 1/15/19.
 */
public class Candy {

    @Test
    public void test() {

       // int[] ratings = new int[]{1, 0, 2};

        //int[] ratings = new int[]{1,2,2};
        int[] ratings = new int[]{1,3,2,2,1};
        System.out.print(candy2(ratings));
    }

    public int candy(int[] ratings) {
        if (ratings.length == 0) {
            return 0;
        }
        if (ratings.length == 1) {
            return 1;
        }
        // start with candy 1
        int candies = 1;
        int peak = 1;
        int down = 0;
        for (int i = 1; i < ratings.length; i++) {

            if (ratings[i] > ratings[i - 1]) {
                peak++;
                candies = candies + peak;
                down = 0;

            } else if (ratings[i] < ratings[i - 1]) {

                down++;
                if (down < peak) {
                    candies = candies + down;
                } else {
                    candies = candies + (down + 1);
                }
            }

        }
        return candies;
    }

    // lets handle now the equal case
    public int candy2(int[] ratings) {
        if (ratings.length == 0) {
            return 0;
        }
        if (ratings.length == 1) {
            return 1;
        }
        // start with candy 1
        int candies = 1;
        int peak = 1;
        int down = 0;
        int up = 0;
        for (int i = 1; i < ratings.length; i++) {

            if (ratings[i] > ratings[i - 1]) {
                up++;
                peak = up+1;
                candies = candies + peak;
                down = 0;

            } else if (ratings[i] == ratings[i - 1]) {
                candies = candies+1;
                peak = 1;
                down=0;
                up = 0;

            } else if (ratings[i] < ratings[i - 1]) {

                up = 0;
                down++;
                if (down < peak) { // no problem condition : 4, 1, 2, 3
                    candies = candies + down;
                } else { // problem condition : 4,1, 2, 3, 4 ==> 4, 1, 2, 3, 5 = > 5, 1, 2, 3, 4
                    candies = candies + (down + 1);
                }
            }
        }
        return candies;
    }
}

