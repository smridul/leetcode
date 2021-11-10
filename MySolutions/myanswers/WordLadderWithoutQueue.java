package myanswers;

import org.junit.Test;

import java.util.*;

public class WordLadderWithoutQueue {

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {

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
        Set<String> allVisited = new HashSet<>();
        boolean found = false;
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            steps++;
            if (exploreAllNodesOfLevel( beginSet, endSet, dictionary, allVisited)) {
                found = true;
                break;
            }
            steps++;
            if (exploreAllNodesOfLevel( endSet, beginSet, dictionary, allVisited)) {
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

    public boolean exploreAllNodesOfLevel( Set<String> visited,
                                          Set<String> visitedFromOtherDirection, Set<String> dictionary,
                                           Set<String> allVisited) {

        Set<String> temp = new HashSet<>();
        for(String node: visited) {
            for (String neighbour : getAllNeighbour(node, dictionary)) {
                if (visitedFromOtherDirection.contains(neighbour)) {
                    return true;
                }

                if(!allVisited.contains(neighbour)) {
                    allVisited.add(neighbour);
                    temp.add(neighbour);
                }

            }
        }
        visited.clear();
        visited.addAll(temp);
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
      //  wordList.add("dot");
        wordList.add("dog");
      //  wordList.add("lot");
       // wordList.add("log");
        //wordList.add("cog");
        System.out.println(ladderLength("hot", "dog", wordList));
    }
}
