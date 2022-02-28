package myanswers;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

public class WordLadder2_1 {

    List<List<String>> paths = new ArrayList<>();

    Set<String> dict;
    Map<String, String> mapBegin;
    Map<String, String> mapEnd;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {


        Set<String> begin = new HashSet<>();
        Set<String> end = new HashSet<>();
        Set<String> global = new HashSet<>();


        mapBegin = new HashMap<>();
        mapEnd = new HashMap<>();

        mapBegin.put(beginWord, null);
        mapEnd.put(endWord, null);

        dict = new HashSet<>();

        for (String word : wordList) {
            dict.add(word);
        }

        if (!dict.contains(endWord)) {
            return new ArrayList<>();
        }

        boolean exploreBegin = true;
        begin.add(beginWord);
        end.add(endWord);
        global.add(beginWord);
        global.add(endWord);
        while (!begin.isEmpty() && !end.isEmpty()) {

            if (exploreLevelNode(begin, end, global, exploreBegin)) {
                break;
            }

            if (exploreLevelNode(end, begin, global, !exploreBegin)) {
                break;
            }
        }


        return paths;
    }


    void createPath(String up, String down, LinkedList<String> list) {

        // first create path from node to begin word
        while (up != null) {
            list.addFirst(up);
            up = mapBegin.get(up);
        }


        // now from down to end
        while (down != null) {
            list.add(down);
            down = mapEnd.get(down);
        }
    }


    boolean exploreLevelNode(Set<String> currentSet, Set<String> otherDirSet, Set<String> global,
                             boolean exploreBegin) {
        boolean found = false;
        Set<String> nextLevel = new HashSet<>();
        for (String node : currentSet) {
            for (String neighbour : getNeighbours(node)) {
                if (otherDirSet.contains(neighbour)) {
                    found = true;

                    LinkedList<String> list = new LinkedList<>();
                    if (exploreBegin) {
                        createPath(node, neighbour, list);
                    } else {
                        createPath(neighbour, node, list);
                    }
                    paths.add(list);
                }

                if (!global.contains(neighbour)) {
                    nextLevel.add(neighbour);
                    global.add(neighbour);
                    if (exploreBegin) {
                        mapBegin.put(neighbour, node);
                    } else {
                        mapEnd.put(neighbour, node);
                    }
                }
            }

        }

        currentSet.clear();
        currentSet.addAll(nextLevel);
        return found;
    }

    Set<String> getNeighbours(String word) {
        Set<String> neighbours = new HashSet<>();
        for (int i = 0; i < word.length(); i++) {
            for (int j = 0; j < 26; j++) {
                //replace ith char with j
                if (j == (word.charAt(i) - 'a')) {
                    continue;
                }
                String s = word.substring(0, i) + ("" + (char) (j + 'a')) + word.substring(i + 1, word.length());
                if (dict.contains(s)) {
                    neighbours.add(s);
                }

            }
        }

        return neighbours;
    }


    @Test
    public void test() {

        List<String> words = Arrays.asList("ted", "tex", "red", "tax", "tad", "den", "rex", "pee");
        List<List<String>> ans = findLadders("red", "tax", words);
        for (List l : ans) {
            System.out.println(l);
        }
    }

    
}
