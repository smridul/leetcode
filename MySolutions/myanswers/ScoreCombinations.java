package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 1/22/19.
 */
public class ScoreCombinations {

    @Test
    public void test(){
        int[] arr = new int[]{2, 3, 7};

        System.out.println(scoreCombinations(arr, 12, 0));
    }

    int scoreCombinations(int[] arr, int target, int start){

        // last element
        if(start == arr.length-1){
            return target % arr[start] == 0? 1:0;
        }

        int ways = 0;
        for(int i=0; i * arr [start] <= target; i++){

            int remaining  = target - arr[start] * i;
            ways+=scoreCombinations(arr, remaining, start+1);

        }
        return ways;
    }
}
