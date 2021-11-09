package myanswers;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PairDivisibleBy60 {
    public int numPairsDivisibleBy60(int[] time) {
        for (int i = 0; i < time.length; i++) {
            time[i] = time[i] % 60;
        }
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int i = 0; i < time.length; i++) {
            Set<Integer> set = map.getOrDefault(time[i], new HashSet());
            set.add(i);
            map.put(time[i], set);
        }

        int totalPairs=0;
        for (int i = 0; i < time.length; i++) {
            int toFind = Math.abs((60-time[i])%60);
            Set complement = map.getOrDefault(toFind, new HashSet());
            Set set = map.getOrDefault(time[i], new HashSet());
            set.remove(i);
            int possiblePairs = complement.size();
            totalPairs+=possiblePairs;
        }
        return totalPairs;
    }

    public int numPairsDivisibleBy60_2(int[] time) {

        for (int i = 0; i < time.length; i++) {
            time[i] = time[i] % 60;
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < time.length; i++) {
            map.put(time[i], map.getOrDefault(time[i], 0)+1);
        }

        int totalPairs=0;
        for (int i = 0; i < time.length; i++) {
            int toFind = Math.abs((60-time[i])%60);
            map.put(time[i], map.getOrDefault(time[i], 0)-1);
            int complement = map.getOrDefault(toFind, 0);
            totalPairs+=complement;
        }
        return totalPairs;
    }

    public int numPairsDivisibleBy60_3(int[] time) {
        for (int i = 0; i < time.length; i++) {
            time[i] = time[i] % 60;
        }
        Map<Integer, Integer> map = new HashMap<>();
        int totalPairs=0;
        for (int i = 0; i < time.length; i++) {
            int toFind = Math.abs((60-time[i])%60);
            int complement = map.getOrDefault(toFind, 0);
            map.put(time[i], map.getOrDefault(time[i], 0)+1);
            totalPairs+=complement;
        }
        return totalPairs;
    }

    @Test
    public void test(){
       // int[] time = new int[]{60,60,60};
        int[] time = new int[]{30,20,150,100,40};
        System.out.println(numPairsDivisibleBy60_3(time));
    }

}
