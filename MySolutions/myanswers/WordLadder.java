package myanswers;

import org.junit.Test;

import java.util.*;

public class WordLadder {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> beginQueue = new LinkedList<>();
        Queue<String> endQueue = new LinkedList<>();

        beginQueue.add(beginWord);
        beginQueue.add(null);

        endQueue.add(endWord);
        endQueue.add(null);

        // all visited nodes from begin
        Set<String> beginSet = new HashSet<>();
        beginSet.add(beginWord);
        // all visited nodes from end
        Set<String> endSet = new HashSet<>();
        endSet.add(endWord);

        int steps = 0;
        Set<String> dictionary = new HashSet<>(wordList);
        if(!dictionary.contains(endWord)){
            return 0;
        }
        boolean found = false;
        while (!beginQueue.isEmpty() && !endQueue.isEmpty()) {
            steps++;
            if (exploreAllNodesOfLevel(beginQueue, beginSet, endSet, dictionary)) {
                found = true;
                break;
            }
            steps++;
            if (exploreAllNodesOfLevel(endQueue, endSet, beginSet, dictionary)) {
                found = true;
                break;
            }
        }

        if(found) {
            return steps + 1;
        }else{
            return 0;
        }
    }

    public boolean exploreAllNodesOfLevel(Queue<String> queue, Set<String> visited,
                                          Set<String> visitedFromOtherDirection, Set<String> dictionary) {
        String node = queue.poll();
        while (node != null) {
            for (String neighbour : getAllNeighbour(node, dictionary)) {
                if (visitedFromOtherDirection.contains(neighbour)) {
                    return true;
                }

                if(!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.add(neighbour);
                }

            }
            node = queue.poll();
        }
        if (!queue.isEmpty()) {
            queue.add(null);
        }
        return false;
    }

    List<String> getAllNeighbour(String word, Set<String> dictionary) {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < word.length(); i++) {
            for (int k = 0; k < 26; k++) {
                StringBuilder s = new StringBuilder(word);
                s.replace(i, i + 1, "" + (char) ('a'+k));
                if (dictionary.contains(s.toString())) {
                    result.add(s.toString());
                }
            }
        }
        return result;
    }

    @Test
    public void test() {

        List<String> wordList = new ArrayList<>();
        //"hot","dot","dog","lot","log","cog"
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        System.out.println(ladderLength("hit", "cog", wordList));
    }

}
