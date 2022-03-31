package myanswers;

import java.util.HashMap;
import java.util.Map;

public class LongestConsecutiveSequence {


    public int longestConsecutive(int[] nums) {

        Map<Integer, MyPair> map = new HashMap<>();

        int ans = 0;
        for(int i: nums){

            if(!map.containsKey(i)){

                int lower = map.getOrDefault(i-1, new MyPair(i, i)).min;
                int upper = map.getOrDefault(i+1, new MyPair(i, i)).max;

                map.put(i, new MyPair(lower, upper));

                if(map.containsKey(i+1)){
                    map.get(upper).max = upper;
                    map.get(upper).min = lower;
                }

                if(map.containsKey(i-1)){
                    map.get(lower).max = upper;
                    map.get(lower).min = lower;
                }
                ans = Math.max(ans, upper-lower+1);
            }
        }
        return ans;
    }

}

class MyPair{
    int min;
    int max;
    MyPair(int i, int j){
        min = i;
        max = j;
    }
}
