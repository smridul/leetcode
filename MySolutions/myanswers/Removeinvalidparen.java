package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by smridul on 1/6/19.
 */
public class Removeinvalidparen {


    @Test
    public void test() {

        List<String> list = removeInvalidParentheses("(j))(");

        for (String s : list) {
            System.out.println(s);
        }


    }

    public List<String> removeInvalidParentheses(String s) {

        LinkedList<String> ans = new LinkedList<>();
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        if (ans.isEmpty()) {
            ans.add("");
        }
        return ans;
    }


    public void remove(String s, LinkedList<String> ans, int last_i, int last_j, char[] par) {

        removeHelper(s, ans, last_i, last_j, par, false);

    }

    // Will fix any imbalanced right parentheses
    public void removeHelper(String s, LinkedList<String> ans, int last_i, int last_j, char[] par, boolean reverseBeforeAdding) {
        int stack = 0, i = last_i;
        for (; i < s.length(); ++i) {
            if (s.charAt(i) == par[0]) stack++;
            if (s.charAt(i) == par[1]) stack--;
            if (stack >= 0) continue;
            for (int j = last_j; j <= i; ++j) {
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1])) {
                    removeHelper(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par, reverseBeforeAdding);
                }
            }
            return;
        }

        if (stack == 0) {
            if (reverseBeforeAdding) {
                String reverse = new StringBuilder(s).reverse().toString();
                ans.addFirst(reverse);
            } else {
                ans.addFirst(s);
            }
        } else {
            String reversed = new StringBuilder(s).reverse().toString();
            removeHelper(reversed, ans, 0, 0, new char[]{')', '('}, true);
        }
    }
}
