package myanswers;

import java.util.Comparator;
import java.util.PriorityQueue;

import javafx.util.Pair;
import org.junit.Test;

public class MaximumSwap {


    public int maximumSwap(int num) {
        char[] digits = Integer.toString(num).toCharArray();

        PriorityQueue<Pair<Character, Integer>> pq = new PriorityQueue<>(new Comparator<Pair<Character, Integer>>() {
            @Override
            public int compare(Pair<Character, Integer> o1, Pair<Character, Integer> o2) {
                if(o2.getKey() == o1.getKey()){
                    return o2.getValue() - o1.getValue();// we want to rightmost to be returned first
                }
                return o2.getKey() - o1.getKey();
            }
        });


        for (int i = 0; i < digits.length; i++) {
            pq.offer(new Pair<>(digits[i], i));
        }

        for (int i = 0; i < digits.length; i++) {
            if (pq.peek().getKey() > digits[i]){
                int j = pq.peek().getValue();
                char tmp = digits[i];
                digits[i] = digits[j];
                digits[j] = tmp;
                return Integer.valueOf(new String(digits));
            } else if(pq.peek().getValue() == i){
                while(!pq.isEmpty() && pq.peek().getKey() == digits[i]){
                    pq.poll();
                }
            }
        }
        return num;
    }

    @Test
    public void test(){
        System.out.println(maximumSwap(1));
    }
}
