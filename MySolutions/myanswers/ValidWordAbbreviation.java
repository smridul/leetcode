package myanswers;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ValidWordAbbreviation {

    public boolean validWordAbbreviation(String word, String abbr) {

        int i = 0;
        int j = 0;
        int number = 0;

        for (j = 0; j < abbr.length(); j++) {

            if (Character.isLetter(abbr.charAt(j))) {

                i = i + number;
                number = 0; //reset the number
                if (i >= word.length() || abbr.charAt(j) != word.charAt(i)) {
                    return false;
                }

                i++;

            } else {
                int digit = abbr.charAt(j) - '0';
                if (digit == 0 && number == 0) {
                    return false;
                }
                number = number * 10 + digit;
            }
        }
        i = i + number;
        return i == word.length() && j == abbr.length();
    }

    @Test
    public void test() {
        String word = "apple";
        String abbr = "a2e";
        System.out.println(validWordAbbreviation(word, abbr));
    }
}
