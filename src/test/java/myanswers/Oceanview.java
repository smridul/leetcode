package myanswers;

import org.junit.Test;

import java.util.*;

public class Oceanview {
    public int[] findBuildings(int[] heights) {
        int max = 0;
        List<Integer> ans = new ArrayList<>();
        for(int i=heights.length-1; i>=0; i--){

            if(heights[i]> max){
                ans.add(i);
            }
            max= Math.max(max, heights[i]);
        }

        int[] ansarray = new int[ans.size()];
        int N= ans.size();
        int index=0;
        for(int i=N-1; i>=0; i--){
            ansarray[index++]= ans.get(i);
        }
        return ansarray;
    }

    @Test
    public void test(){
        int[] heights = new int[]{4,2,3,1};
        int [] res = findBuildings(heights);
        int i=0;
    }
}
