package myanswers;

import org.junit.Test;

public class RemoveAdjacentDuplicates {
    public String removeDuplicates(String s, int k) {
        if (s == null || s.length() == 0 || s.length() == 1) {
            return s;
        }
        int count[] = new int[s.length()];

        for (int i = 0; i <= s.length() - 1; ) {
            if (i == 0) {
                count[i] = 1;
            } else {
                if (s.charAt(i) == s.charAt(i - 1)) {
                    count[i] = count[i - 1] + 1;
                    if (count[i] >= k) {
                        s = s.substring(0, i - k + 1) + s.substring(i + 1);
                        i = i - k + 1;
                        continue;
                    }
                } else {
                    count[i] = 1;
                }
            }
            i++;
        }
        return s;
    }


    public String removeDuplicates1(String s, int k) {
        if (s == null || s.length() == 0 || s.length() == 1) {
            return s;
        }
        int count[] = new int[s.length()];

        char[] arrays = s.toCharArray();

        int i=0;
        for (int j = 0; j <= s.length() - 1; j++) {
            arrays[i] = arrays[j];
            if (i == 0) {
                count[i] = 1;
            } else {
                if (arrays[i] == arrays[i - 1]) {
                    count[i] = count[i - 1] + 1;
                    if (count[i] >= k) {
                        i = i-k+1;
                        continue;
                    }
                } else {
                    count[i] = 1;
                }
            }
            i++;
        }
        // return first i chars of string

        return new String(arrays, 0, i);
    }
    @Test
    public void test() {
        String s = "deeedbbcccbdaa";

        System.out.println(removeDuplicates1(s, 3));

    }
}
