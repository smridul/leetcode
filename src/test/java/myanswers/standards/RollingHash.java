package myanswers.standards;

import org.junit.Test;

import java.util.*;

public class RollingHash {


    public boolean search(int m, int base, int q, int[] nums) {

        // q is modulus
        // m is length of pattern

        int hash = 0;
        HashSet<Integer> seen = new HashSet<Integer>();
        for (int i = 0; i < m; i++) {
            hash = (hash * base + nums[i]) % q;
        }
        seen.add(hash);
        int k = 1;
        for (int i = 0; i < m; i++) {
            k = (k * base) % q;
        }

        // k is (base^m)% q; useful constant


        for (int i = 1; i <= nums.length - m; i++) {

            // to calculate T[i] from T[i-1]

            // (hash* base - nums[i]*k) % q this can be negative
            // hence use ((hash* base - nums[i]*k) % q + q) %q
            hash = (((hash * base - nums[i-1] * k) % q + q) % q + nums[i+m-1]) % q;
            if (seen.contains(hash)) {
                return true;
            }
            seen.add(hash);
        }
        return false;
    }



    public int longestRepeatingSubstring(String s) {

        int[] nums = new int[s.length()];
        for(int i = 0; i < s.length(); ++i) nums[i] = s.charAt(i) - 'a';
        // base value for the rolling hash function
        int base = 26;
        // modulus value for the rolling hash function to avoid overflow
        int modulus = (int)Math.pow(2, 24);


        int start = 0;
        int end = s.length() - 1;
        int maxLen = 0;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (search(mid, base, modulus, nums)) {
                maxLen = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return maxLen;
    }


    @Test
    public void test() {

        System.out.println(longestRepeatingSubstring("aaaaa"));

        List<Integer> operands = new ArrayList<>();
        int start=0;
        List<Integer> aa=Arrays.asList(operands.get(start));
    }
}
