package myanswers;

import org.junit.Test;

import java.util.*;

public class WordLadder2_NoBidirectional {
    List<List<String>> paths = new ArrayList<>();
    Set<String> dict;

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {


        List<List<String>> currentPaths = new ArrayList<>();
        //Set<String> lastLevel = new HashSet<>();
        Set<String> excludedForThisIterations = new HashSet<>();



        dict = new HashSet<>();
        for (String word : wordList) {
            dict.add(word);
        }
        if (!dict.contains(endWord)) {
            return new ArrayList<>();
        }

        currentPaths.add(Arrays.asList(beginWord));

        boolean found = false;
        while (!currentPaths.isEmpty()) {


            Set<String> excludedForNextIterations = new HashSet<>();
            List<List<String>> nextLevelPaths = new ArrayList<>();
            for (List<String> path : currentPaths) {
                String lastWord = path.get(path.size() - 1);
                for (String neighbour : getNeighbours(lastWord)) {

                    if (!excludedForThisIterations.contains(neighbour) ) {

                        excludedForNextIterations.add(neighbour);

                        List<String> newPath = new ArrayList<>(path);
                        newPath.add(neighbour);
                        if (neighbour.equals(endWord)) {
                            paths.add(newPath);
                            found = true;
                        } else {
                            nextLevelPaths.add(newPath);
                        }
                    }


                }
            }

            if(found){
                break;
            }
            currentPaths = nextLevelPaths;
            excludedForThisIterations.addAll(excludedForNextIterations);

        }


        return paths;
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
        List<List<String>> ans = findLadders0("red", "tax", words);
        for (List l : ans) {
            System.out.println(l);
        }
    }


    public List<List<String>> findLadders0(String beginWord, String endWord, List<String> wordList) {


        List<List<String>> currentPaths = new ArrayList<>();


        dict = new HashSet<>();
        for (String word : wordList) {
            dict.add(word);
        }
        if (!dict.contains(endWord)) {
            return new ArrayList<>();
        }

        currentPaths.add(Arrays.asList(beginWord));

        boolean found = false;
        while (!currentPaths.isEmpty()) {


            Set<String> visited = new HashSet<>();


            List<List<String>> nextLevelPaths = new ArrayList<>();
            for (List<String> path : currentPaths) {

                String lastWord = path.get(path.size() - 1);
                //visited.add(lastWord);
                for (String neighbour : getNeighbours(lastWord)) {

                    visited.add(neighbour);
                    List<String> newPath = new ArrayList<>(path);
                    newPath.add(neighbour);
                    if (neighbour.equals(endWord)) {
                        paths.add(newPath);
                        found = true;
                    } else {
                        nextLevelPaths.add(newPath);
                    }



                }
            }

            if(found){
                break;
            }
            currentPaths = nextLevelPaths;
            for(String s : visited){
                dict.remove(s);
            }
        }
        return paths;
    }
}
