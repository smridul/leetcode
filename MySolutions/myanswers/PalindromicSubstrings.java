package myanswers;

import org.junit.Test;

public class PalindromicSubstrings {

    int count = 0;

    void expand(int i, int j, String s) {
        if (i != j) {
            i++;
            j--;
        }
        while (i - 1 >= 0 && j + 1 <= s.length() - 1 && s.charAt(i - 1) == s.charAt(j + 1)) {
            i--;
            j++;
            count++;
        }
    }

    public int countSubstrings(String s) {
        count = 0;
        if (s.length() < 2) {
            return s.length();
        }

        for (int i = 0; i <= s.length() - 2; i++) {
            expand(i, i, s);
            expand(i, i + 1, s);
        }

        return count + s.length();
    }

    @Test
    public void test() {
        int count = countSubstrings("aa");
        System.out.println(count);

    }
}
