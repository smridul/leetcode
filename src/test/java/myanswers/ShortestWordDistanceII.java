package myanswers;

import java.util.*;

public class ShortestWordDistanceII {

    Map<String, List<Integer>> map = new HashMap<>();

    public void WordDistance(String[] wordsDict) {
        for (int i = 0; i < wordsDict.length; i++) {
            if (!map.containsKey(wordsDict[i])) {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                map.put(wordsDict[i], list);
            } else {
                List<Integer> list = map.get(wordsDict[i]);
                list.add(i);
                map.put(wordsDict[i], list);
            }
        }
    }

    public int shortest(String word1, String word2) {

        List list1 = map.get(word1);
        List list2 = map.get(word2);
        int i = 0;
        int j = 0;
        int min = Integer.MAX_VALUE;
        while (true) {

            int distance = Math.abs((int) list1.get(i) - (int) list2.get(j));
            min = Math.min(distance, min);
            if ((int) list1.get(i) < (int) list2.get(j)) {
                i++;
                if (i == list1.size()) {
                    break;
                }
            } else {
                j++;
                if (j == list2.size()) {
                    break;
                }
            }
        }
        return min;
    }
}
