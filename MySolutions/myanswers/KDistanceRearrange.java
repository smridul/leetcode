package myanswers;

import org.junit.Test;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class KDistanceRearrange {

    public String reorganizeString(String s, int k) {
        if (k == 0 || k == 1) {
            return s;
        }


        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            ++count[s.charAt(i) - 'a'];
        }

        PriorityQueue<Character> pq = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return Integer.compare(count[o2 - 'a'], count[o1 - 'a']);
            }
        });

        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                pq.add((char) ('a' + i));
            }
        }


        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        while (!pq.isEmpty()) {

            char maxc = pq.poll();
            count[maxc - 'a']--;
            queue.offer(maxc);
            sb.append(maxc);
            if (queue.size() == k) {
                char c = queue.poll();
                if (count[c - 'a'] > 0) {
                    pq.add(c);
                }
            }
        }

        return sb.length() == s.length() ? sb.toString() : "";
    }


    @Test
    public void test() {
        // System.out.println(reorganizeString("aabbcc", 3));
        char[] tasks = new char[]{'A', 'A', 'A', 'B', 'B', 'B'};
        System.out.println(leastInterval(tasks, 2));
    }


    public int leastInterval(char[] tasks, int n) {

        int k = n + 1;
        int[] count = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            ++count[tasks[i] - 'A'];
        }

        PriorityQueue<Character> pq = new PriorityQueue<>(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return Integer.compare(count[o2 - 'A'], count[o1 - 'A']);
            }
        });

        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                pq.add((char) ('A' + i));
            }
        }


        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new LinkedList<>();
        int idle = 0;
        int allTasksToCover = tasks.length;
        while (allTasksToCover > 0) {

            if (pq.isEmpty()) {
                idle++;
                queue.offer('*');
                sb.append("*");
            } else {

                char maxc = pq.poll();

                count[maxc - 'A']--;
                allTasksToCover--;

                queue.offer(maxc);
                sb.append(maxc);
            }


            if (queue.size() == k) {
                char c = queue.poll();
                if (c != '*' && count[c - 'A'] > 0) {
                    pq.add(c);
                }
            }
        }

        System.out.println(sb.toString());

        return idle + tasks.length;
    }
}
