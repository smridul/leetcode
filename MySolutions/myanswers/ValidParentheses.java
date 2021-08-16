package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ValidParentheses {
    public List<String> generateParenthesis(int n) {

        List<String> result = new ArrayList<>();
        generateParenthesis("", 0, 0, n, result);
        return result;
    }


    void generateParenthesis(String str, int noOfL, int noOfR, int k, List<String> result) {

        if (str.length() == 2 * k) {
            result.add(str);
            return;
        } else {
            if (noOfL < k) {
                generateParenthesis(str + "(", noOfL + 1, noOfR, k, result);
            }
            if (noOfR < k && noOfL > noOfR) {
                generateParenthesis(str + ")", noOfL, noOfR + 1, k, result);
            }
        }

    }


    @Test
    public void test() {
        List<String> list = generateParenthesis(3);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
