package myanswers;

import org.junit.Test;

import java.util.*;

public class SearchSuggestionSystem {

    private TrieNode root;

    public void addWord(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                node.put(currentChar, new TrieNode());
            }
            node = node.get(currentChar);
        }
        node.isEnd = true;
    }

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        root = new TrieNode();
        for (String word : products) {
            addWord(word);
        }

        List<List<String>> finalResult = new ArrayList<>();
        for (int i = 0; i < searchWord.length(); i++) {
            List<String> result = new ArrayList<>();
            searchRecursionHelper(searchWord.substring(0, i + 1), root, "", result);
            finalResult.add(result);
        }
        return finalResult;
    }


    private void searchRecursionHelper(String word, TrieNode node, String prefix, List<String> result) {
        if (result.size() == 3) {
            return;
        }
        if (word.isEmpty() && node.isEnd) {
            result.add(prefix);
        }
        if (!word.isEmpty()) {
            // still traversing to right node first
            char ch = word.charAt(0);
            if (node.containsKey(ch)) {
                searchRecursionHelper(word.substring(1), node.get(ch), prefix + ch, result);
            } else {
                return;
            }
        } else {
            //word is traversed. now we are genertating all possible words from this word
            for (int i = 0; i < 26; i++) {
                char ch = (char) ('a' + i);
                if (node.containsKey(ch)) {
                    searchRecursionHelper("", node.get(ch), prefix + ch, result);
                }
            }
        }
    }


    public List<List<String>> suggestedProductsUsingBinarySearch(String[] products, String searchWord) {

        Arrays.sort(products);

        List<List<String>> finalResult = new ArrayList<>();
        for (int i = 0; i < searchWord.length(); i++) {
            List<String> result = new ArrayList<>();

            int index = lowerBound(products, searchWord.substring(0, i + 1));
            if (index != -1) {
                for (int k = index; k < index + 3 && k < products.length; k++) {
                    if (products[k].contains(searchWord.substring(0, i + 1))) {
                        result.add(products[k]);
                    }
                }
            }
            finalResult.add(result);
        }
        return finalResult;
    }

    public int lowerBound(String[] products, String searchWord) {
        int low = 0;
        int high = products.length - 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (products[mid].compareTo(searchWord) >= 0) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return products[low].compareTo(searchWord) >= 0 ? low : -1;
    }


    @Test
    public void test() {
        String[] products = new String[]{"mobile","mouse","moneypot","monitor","mousepad"};
        String searchWord = "mouse";
        List<List<String>> result = suggestedProductsUsingBinarySearch(products, searchWord);
        int a = 0;

    }

}
