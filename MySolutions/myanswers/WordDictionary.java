package myanswers;

class WordDictionary {
    private TrieNode root;

    public WordDictionary() {
        root = new TrieNode();
        root.isEnd = true;
    }

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

    public boolean search(String word) {
        return searchRecursionHelper(word, root);
    }

    private boolean searchRecursionHelper(String word, TrieNode node) {
        if (word.isEmpty()) {
            return node.isEnd;
        }
        char ch = word.charAt(0);
        if (ch != '.') {
            if (node.containsKey(ch)) {
                return searchRecursionHelper(word.substring(1), node.get(ch));
            } else {
                return false;
            }
        } else {
            for (TrieNode child : node.getAllChild()) {
                if (child != null) {
                    if(searchRecursionHelper(word.substring(1), child)){
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
