package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntervalIntersection {

    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {

        if(firstList.length==0 || secondList.length ==0){
            return new int[][]{};
        }
        int i=0;
        int j = 0;
        List<int[]> result = new ArrayList<>();
        while(i < firstList.length && j < secondList.length){

            int newStart = Math.max(firstList[i][0], secondList[j][0]);
            int newEnd = Math.min(firstList[i][1], secondList[j][1]);

            // if intersect
            if(newStart <= newEnd){
                int [] interval = new int[]{newStart, newEnd};
                result.add(interval);
            }

            // increment the behind list
            if(firstList[i][1] < secondList[j][1]){
                i++;
            }else{
                j++;
            }
        }

        int [][] res = new int[result.size()][2];
        for( i=0; i < res.length; i++){
            for( j=0; j < res[0].length; j++){
                res[i][j] = result.get(i)[j];
            }
        }

       // return result.toArray(new int[result.size()][2]);
        return res;
    }

    @Test
    public void test() {



    }

}
