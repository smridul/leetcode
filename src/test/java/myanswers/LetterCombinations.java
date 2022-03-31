package myanswers;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LetterCombinations {

    public List<String> letterCombinations(String digits) {
        LinkedList<String> ans = new LinkedList<String>();
        if (digits.isEmpty()) return ans;
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        while (ans.peek().length() != digits.length()) {

            String removed = ans.poll();

            String letters = mapping[digits.charAt(removed.length()) - '0'];

            for (char c : letters.toCharArray()) {
                ans.add(removed + c);
            }
        }

        return ans;
    }
}
