package myanswers;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FractionToDecimal {

    public String fractionToDecimal(int num, int den) {

        String sign = (((double) (num)) / den) < 0 ? "-" : "";

        long numerator = Math.abs((long) num);
        long denominator = Math.abs((long) den);
        long part1int = numerator / denominator;
        String part1 = "" + part1int;
        long remainder = numerator % denominator;

        if (remainder == 0) {
            return sign + part1;
        }
        String part2 = "";
        Map<Long, Integer> set = new HashMap<>();
        int index = 0;
        while (remainder != 0 && !set.containsKey(remainder)) {
            set.put(remainder, index);
            long number = remainder * 10;
            long quotient = number / denominator;
            part2 = part2 + quotient;
            remainder = number % denominator;
            index++;
        }


        if (remainder == 0) {
            return sign + part1 + "." + part2;
        } else {
            index = set.get(remainder);
            String repeatingpart = part2.substring(0, index) + "(" + part2.substring(index) + ")";
            return sign + part1 + "." + repeatingpart;
        }
    }


    public String fractionToDecimal2(int num, int den) {

        //String sign = (((double) (num)) / den) < 0 ? "-" : "";

        String sign = (num <0) ^ (den <0) ? "" : "-";
        long numerator = Math.abs((long) num);
        long denominator = Math.abs((long) den);
        long part1int = numerator / denominator;
        String part1 = "" + part1int;
        long remainder = numerator % denominator;

        if (remainder == 0) {
            return sign + part1;
        }
        StringBuilder part2 = new StringBuilder();
        Map<Long, Integer> set = new HashMap<>();
        int index = 0;
        while (remainder != 0) {
            if (set.containsKey(remainder)) {

                part2.insert(set.get(remainder), "(");
                part2.append(")");
                break;
            }

            set.put(remainder, index);
            long number = remainder * 10;
            long quotient = number / denominator;
            part2.append(quotient);
            remainder = number % denominator;
            index++;
        }

        return sign + part1 + "." + part2;
    }

    @Test
    public void test() {
        System.out.println(fractionToDecimal(-1, -2147483648));
    }
}
