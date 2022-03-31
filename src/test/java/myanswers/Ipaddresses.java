package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 1/8/19.
 */
public class Ipaddresses {

    @Test
    public void test() {

        String s = "19216811";

        int length = s.length();
        for (int i = 1; i <= length - 3; i++) {

            if (!isValid(s.substring(0, i))) continue;
            for (int j = i + 1; j <= length - 2; j++) {
                if (!isValid(s.substring(i, j))) continue;

                for (int k = j + 1; k <= length - 1; k++) {
                    if (!isValid(s.substring(j, k)) || !isValid(s.substring(k, length))) continue;

                    System.out.println(s.substring(0, i) + "." + // 0(include) to i(exclusive)
                            s.substring(i, j) + "." + // i to j
                            s.substring(j, k) + "." +
                            s.substring(k, length));
                }
            }
        }
    }


    boolean isValid(String s) {
        if (s.startsWith("0") && s.length() > 1) {
            return false;
        }

        int val = Integer.parseInt(s);
        return (val <= 255 && val >= 0);

    }

    @Test
    public void testSincurveString() {

        String s = "Hello world!";


        for (int top = 1; top < s.length(); top = top + 4) {
            print(s.charAt(top));
        }

        for (int middle = 0; middle < s.length(); middle = middle + 2) {
            print(s.charAt(middle));
        }

        for (int bottom = 3; bottom < s.length(); bottom = bottom + 4) {
            print(s.charAt(bottom));
        }
    }

    void print(char c) {
        System.out.print(c == ' ' ? "_" : c);

    }

    @Test
    public void encodeString() {

        String s = "aaaabccaa";
        StringBuilder ss = new StringBuilder();
        int count = 1;


        if (s.length() == 1) {
            ss.append(count).append(s);
        } else {
            for (int current = 1; current < s.length(); current++) {
                if (s.charAt(current) == s.charAt(current - 1)) {
                    count++;
                } else {
                    ss.append(count).append(s.charAt(current - 1));
                    count = 1;
                }

                // we can handle this edge condtiion in above too by continuing the loop till s.length and check
                // the condtion to print with edge case too
                if (current == s.length() - 1) {
                    ss.append(count).append(s.charAt(current));
                }
            }
        }
        System.out.print(ss.toString());

    }

}
