package myanswers;

import org.junit.Test;

import java.util.*;

public class TopKFrequent {

    public int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i : nums) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                map.put(i, map.get(i) + 1);
            }
        }


        PriorityQueue<TopKNode> priorityQueue = new PriorityQueue<>(new Comparator<TopKNode>() {
            @Override
            public int compare(TopKNode o1, TopKNode o2) {
                return o1.freq - o2.freq; // min heap
            }
        });

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            TopKNode node = new TopKNode(entry.getKey(), entry.getValue());
            priorityQueue.add(node);
            if (priorityQueue.size() > k) {
                priorityQueue.poll();
            }
        }

        // return the heap node
        Iterator<TopKNode> iter = priorityQueue.iterator();
        List<Integer> result = new ArrayList<>();
        while (iter.hasNext()){
            result.add(iter.next().key);
        }

        return result.stream().mapToInt(i->i).toArray();
    }

    @Test
    public void test() {

        int [] arr = { 1,1,1,2,2,3};
        int k=2;
        int [] res = topKFrequent(arr, k);
        int a = 0;
    }
}

class TopKNode {
    int key;
    int freq;

    TopKNode(int key, int freq) {
        this.key = key;
        this.freq = freq;
    }
}