package myanswers;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class AddBoldTag {

    //ignore this. wasted time on this sliding window approach
    public String addBoldTag1(String s, String[] words) {

        if (words == null || words.length == 0) {
            return s;
        }
        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        Map<Integer, Integer> tags = new HashMap<>();
        int start = 0;
        int end = 0;
        int startTag = -1;

        boolean findStart = false;
        while (end < s.length()) {

            if (!findStart) {
                if (trie.startsWith("" + s.charAt(end))) {
                    tags.put(end, 0);// 0 is start tag
                    findStart = true;
                    start = end;

                    if (end == s.length() - 1) {
                        tags.put(end, 1);
                    }
                }
                end++;
            } else {
                if (trie.startsWith(s.substring(start, end + 1))) {

                    if (end == s.length() - 1) {
                        tags.put(end, 1);
                    }

                    end++;
                } else {
                    //try to shrink start

                    while (start <= end && !trie.startsWith(s.substring(start, end + 1))) {
                        start++;
                    }

                    if (start > end) {
                        tags.put(end - 1, 1);
                        findStart = false;
                    }
                }
            }


        }

        StringBuilder str = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            if (tags.containsKey(i)) {
                if (tags.get(i) == 0) {
                    str.append("<b>" + s.charAt(i));
                } else {
                    str.append(s.charAt(i) + "</b>");
                }
            } else {
                str.append(s.charAt(i));
            }
        }
        return str.toString();
    }

    public String addBoldTag(String s, String[] words) {

        int maxStretch = -1;
        boolean[] bold = new boolean[s.length()];
        for (int i = 0; i < s.length(); i++) {

            for (String word : words) {
                if (s.startsWith(word, i)) {
                    maxStretch = Math.max(maxStretch, i + word.length() - 1);
                }
            }

            bold[i] = maxStretch >= i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (bold[i] && (i == 0 || bold[i - 1] == false)) {
                sb.append("<b>");
            }
            sb.append(s.charAt(i));

            if (bold[i] && (i == s.length() - 1 || bold[i + 1] == false)) {
                sb.append("</b>");
            }
        }

        return sb.toString();
    }


    @Test
    public void test() {
        String[] words = new String[]{"a"};
        String s = "aaabbcc";
        System.out.println(addBoldTag(s, words));
    }
}
