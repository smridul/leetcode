package myanswers;

import org.junit.Test;

import java.util.*;

public class Removeinvalidparen {


    // dfs version

    public List<String> removeInvalidParentheses1(String s) {

        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                left++;
            } else if (s.charAt(i) == ')') {
                if(left == 0){
                    right++;
                }else{
                    left--;
                }
            }
        }

        int leftToRemove = left;
        int rightToRemove = right;


        List<String> res = new ArrayList<>();
        dfs(s, 0, res, "", leftToRemove, rightToRemove, 0, -1);
        return new ArrayList<String>(res);

    }


    public void dfs(String s, int i, List<String> res, String path, int leftToRemove, int rightToRemove,
                    int open, int lastSelected) {
        if (leftToRemove < 0 || rightToRemove < 0 || open < 0) {
            return;
        }
        if (i == s.length()) {
            if (leftToRemove == 0 && rightToRemove == 0 && open == 0) {
                res.add(path);
            }
            return;
        }

        char c = s.charAt(i);

        if (c == '(') {
            // remove it
            dfs(s, i + 1, res, path, leftToRemove - 1, rightToRemove, open, lastSelected);
            // add it

            if(i==0 || s.charAt(i) != s.charAt(i-1) || lastSelected == i-1) {
                dfs(s, i + 1, res, path + c, leftToRemove, rightToRemove, open + 1, i);
            }

        } else if (c == ')') {
            //remove it
            dfs(s, i + 1, res, path, leftToRemove, rightToRemove - 1, open, lastSelected);
            // add it
            if(i==0 || s.charAt(i) != s.charAt(i-1) || lastSelected == i-1) {
                dfs(s, i + 1, res, path + c, leftToRemove, rightToRemove, open - 1, i);
            }

        } else {
            dfs(s, i + 1, res, path + c, leftToRemove, rightToRemove, open, i);
        }

    }








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
