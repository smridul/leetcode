package myanswers.standards;

import org.junit.Test;

import java.util.*;

public class Trie {

    public List<List<Integer>> palindromePairs(String[] words) {

        TrieNode root = new TrieNode();
        int i = 0;
        for (String word : words) {
            addWithPalindromIndex(word, root, i++);
        }

        List<List<Integer>> ans = new ArrayList<>();
        for (i = 0; i < words.length; i++) {

            List<Integer> pair = search(words[i], root);
            if(pair!=null){
                for (int j : pair) {
                    if (j == i) continue;
                    ans.add(Arrays.asList(i, j));
                }
            }
        }

        return ans;

    }


    void addWithPalindromIndex(String word, TrieNode root, int index) {
        StringBuilder sb = new StringBuilder(word);
        word = sb.reverse().toString();
        TrieNode current = root;


        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            boolean isPalindrome = isPalindromeTheRemainingPart(word, i, word.length() - 1);
            if (isPalindrome) {
                current.palindromes.add(index);
            }

            if (current.contains(ch)) {
                current = current.children.get(ch);

            } else {
                TrieNode node = new TrieNode();
                current.children.put(ch, node);
                current = node;
            }
        }

        current.isWord = true;
        current.index = index;
    }

    List<Integer> search(String word, TrieNode root) {
        TrieNode current = root;
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {

            //if current is a valid (fullword) and remaming part of word is palindrome
            // that means we have to add all palindrome list to answer
            // case when word 1 is longer than word 2
            if(current.isWord && isPalindromeTheRemainingPart(word, i, word.length()-1)){
                ans.add(current.index);
            }

            char ch = word.charAt(i);

            if (current.contains(ch)) {
                current = current.children.get(ch);
            } else {
                return ans;
            }
        }





        // case when word 2 is longer than word 1
        //we have finished the word. so now we can add palindromes part as well if there are any
        if (current.palindromes!=null && !current.palindromes.isEmpty()) {
            ans.addAll(current.palindromes);
        }
        // same length exact reverse
        if(current.isWord){
            ans.add(current.index);
        }
        return ans;

    }

    @Test
    public void test() {
        String[] words = new String[]{"a","b","c","ab","ac","aa"};
        List<List<Integer>> pairs = palindromePairs(words);
        for (List<Integer> l : pairs) {
            System.out.println(l);
        }




    }

    boolean isPalindromeTheRemainingPart(String word, int start, int end) {
        while (start < end) {
            if (word.charAt(start) != word.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }


    //////////////////////////////////////////////////////
    //normal add
    void add(String word, TrieNode root) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (current.contains(ch)) {
                current = current.children.get(ch);
            } else {
                TrieNode node = new TrieNode();
                current.children.put(ch, node);
                current = node;
            }
        }

        current.isWord = true;
    }

    boolean isPrefix(String word, TrieNode root) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);

            if (current.contains(ch)) {
                current = current.children.get(ch);
            } else {
                return false;
            }
        }
        return true;
    }

    boolean isWord(String word, TrieNode root) {
        TrieNode current = root;
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (current.contains(ch)) {
                current = current.children.get(ch);
            } else {
                return false;
            }
        }
        return current.isWord;
    }
}

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isWord;
    int index;
    List<Integer> palindromes = new ArrayList<>();// specific to this question

    boolean contains(char ch) {
        return children.containsKey(ch);
    }
}


