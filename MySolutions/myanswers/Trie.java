package myanswers;

class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode();
        root.isEnd = true;
    }

    public void insert(String word) {
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

    public boolean search(String word) {
        TrieNode node = root;
        for (int i = 0; i < word.length(); i++) {
            char currentChar = word.charAt(i);
            if (!node.containsKey(currentChar)) {
                return false;
            } else {
                node = node.get(currentChar);
            }
        }
        return node.isEnd;
    }

    public boolean searchRecursion(String word) {
        return searchRecursionHelper(word, root);
    }

    private boolean searchRecursionHelper(String word, TrieNode node) {
        if (word.isEmpty()) {
            return node.isEnd;
        }
        char ch = word.charAt(0);
        if (node.containsKey(ch)) {
            return searchRecursionHelper(word.substring(1), node.get(ch));
        } else {
            return false;
        }
    }

    public boolean startsWith(String prefix) {
        TrieNode node = root;
        for (int i = 0; i < prefix.length(); i++) {
            char currentChar = prefix.charAt(i);
            if (!node.containsKey(currentChar)) {
                return false;
            } else {
                node = node.get(currentChar);
            }
        }
        return true;
    }



    public boolean startsWithRecursion(String prefix) {
        return startsWithRecursionHelper(prefix, root);
    }

    private boolean startsWithRecursionHelper(String word, TrieNode node) {
        if (word.isEmpty()) {
            return true;
        }
        char ch = word.charAt(0);
        if (node.containsKey(ch)) {
            return startsWithRecursionHelper(word.substring(1), node.get(ch));
        } else {
            return false;
        }
    }
}

class TrieNode {
    boolean isEnd;
    TrieNode[] links;

    public TrieNode() {
        links = new TrieNode[26];
    }

    public boolean containsKey(char ch) {
        return links[ch - 'a'] != null;
    }

    public TrieNode get(char ch) {
        return links[ch - 'a'];
    }

    public TrieNode[] getAllChild() {
        return links;
    }

    public void put(char ch, TrieNode node) {
        links[ch - 'a'] = node;
    }

    public void setEnd() {
        isEnd = true;
    }

    public boolean isEnd() {
        return isEnd;
    }
}