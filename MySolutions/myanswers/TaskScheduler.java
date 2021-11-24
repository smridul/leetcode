package myanswers;

import java.util.*;

public class TaskScheduler {


    // i tried with tree map but failed, its difficult, so better use Priority queue
    public int leastInterval(char[] tasks, int n) {

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < tasks.length; i++) {
            map.put(tasks[i], map.getOrDefault(tasks[i], 0) + 1); // map key is TaskName, and value is number of times to be executed.
        }

        TreeMap<CharFreq, Integer> treemap = new TreeMap<>(new Comparator<CharFreq>() {
            @Override
            public int compare(CharFreq o1, CharFreq o2) {
                return o1.count - o2.count;
            }
        });

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            treemap.put(new CharFreq(entry.getKey(), entry.getValue()), 1);
        }

        StringBuilder stringBuilder = new StringBuilder();
        List<CharFreq> list = new ArrayList<>();
        while (!treemap.isEmpty()) {

            for(Map.Entry<CharFreq, Integer> entry: treemap.entrySet()){
                list.add(entry.getKey());
                char ch = entry.getKey().ch;
                stringBuilder.append(ch);

            }
        }

        return 0;
    }

    public int leastIntervalWithPq(char[] tasks, int n) {
        return 0;
    }


    }

class CharFreq {
    int count;
    char ch;

    public CharFreq(char ch, int count) {
        this.ch = ch;
        this.count = count;
    }
}
