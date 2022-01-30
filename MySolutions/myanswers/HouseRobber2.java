package myanswers;

import javafx.util.Pair;
import org.junit.Test;

public class HouseRobber2 {
    public int rob(int[] nums) {

        if (nums.length == 1) {
            return nums[0];
        }

        int prevToPrev = 0;
        int prev = nums[0];

        int prevToPrev2 = 0;
        int prev2 = 0;
        int ans=prev;
        int ans2= 0;
        for (int i = 1; i < nums.length; i++) {

            // case 1 selected
            if (i != nums.length - 1) {
                int choice1 = nums[i] + prevToPrev;
                //case 2 not selected
                int choice2 = prev;
                ans = Math.max(choice1, choice2);
                prevToPrev = prev;
                prev = ans;
            }


            int choice3 = nums[i] + prevToPrev2;
            //case 2 not selected
            int choice4 = prev2;

            ans2 = Math.max(choice3, choice4);
            prevToPrev2 = prev2;
            prev2 = ans2;
        }

        return Math.max(ans, ans2);
    }


    @Test
    public void test() {
        int[] nums = new int[]{2,1};
        System.out.println(rob(nums));
        int n = 12;
        char[] arr = String.valueOf(n).toCharArray();
        int a=0;

    }

}
