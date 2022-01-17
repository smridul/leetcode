package myanswers;

import org.junit.Test;

import java.util.*;

/**
 * Created by smridul on 1/6/19.
 */
public class Removeinvalidparen {


    @Test
    public void test() {

        List<String> list = removeInvalidParenthesesBfs("(()))()");

        for (String s : list) {
            System.out.println(s);
        }


    }


    public List<String> removeInvalidParenthesesBfs(String s) {
        List<String> res = new ArrayList<>();

        // sanity check
        if (s == null) return res;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        // initialize
        queue.add(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {
            s = queue.poll();

            if (isValid(s)) {
                // found an answer, add to the result
                res.add(s);
                found = true;
            }

            if (found) continue;

            // generate all possible states
            for (int i = 0; i < s.length(); i++) {
                // we only try to remove left or right paren
                if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;

                String t = s.substring(0, i) + s.substring(i + 1);

                if (!visited.contains(t)) {
                    // for each state, if it's not visited, add it to the queue
                    queue.add(t);
                    visited.add(t);
                }
            }
        }

        return res;
    }

    // helper function checks if string s contains valid parantheses
    boolean isValid(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')' && count-- == 0) return false;
        }

        return count == 0;
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
