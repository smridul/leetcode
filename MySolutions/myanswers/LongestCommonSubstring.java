package myanswers;

import org.junit.Test;

public class LongestCommonSubstring {

    public int findLength(int[] nums1, int[] nums2) {
        int L1 = nums1.length;
        int L2 = nums2.length;
        int dp[][] = new int[L1 + 1][L2 + 1];
        for (int i = 0; i <= L2; i++) {
            dp[L1][i] = 0;
        }
        for (int i = 0; i <= L1; i++) {
            dp[i][L2] = 0;
        }
        int max = 0;
        for (int i = L1 - 1; i >= 0; i--) {
            for (int j = L2 - 1; j >= 0; j--) {

                if (nums1[i] == nums2[j]) {
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                    max = Math.max(max, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return max;
    }

    @Test
    public void test() {
        int num1[] = new int[]{0,0,0,0,0};
        int num2[] = new int[]{0,0,0,0,0};

        System.out.println(findLength(num1, num2));
    }
}