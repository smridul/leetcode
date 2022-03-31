package myanswers;

import java.util.*;

public class TopKFrequent2 {

    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();

        for (String i : words) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                map.put(i, map.get(i) + 1);
            }
        }

        PriorityQueue<TopKNode1> priorityQueue = new PriorityQueue<>(new Comparator<TopKNode1>() {
            @Override
            public int compare(TopKNode1 o1, TopKNode1 o2) {
               return o1.freq == o2.freq ? o2.key.compareTo(o1.key) : o1.freq-o2.freq;
            }
        });

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            TopKNode1 node = new TopKNode1(entry.getKey(), entry.getValue());
            priorityQueue.add(node);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }

        //now we have top k values in heap
        LinkedList<String> result = new LinkedList<>();
        while (!priorityQueue.isEmpty()){
            result.addFirst(priorityQueue.poll().key);
        }

        return result;
    }
}

class TopKNode1 {
    String key;
    int freq;

    TopKNode1(String key, int freq) {
        this.key = key;
        this.freq = freq;
    }
}
