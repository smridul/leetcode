package myanswers;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class RemoveOverlappingintervals {
    public int eraseOverlapIntervals(int[][] intervals) {

        if(intervals.length <= 1){
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] first, int[] second) {
                if (first[0] == second[0]) {
                    return Integer.compare(first[1], second[1]);
                } else {
                    return Integer.compare(first[0], second[0]);
                }
            }
        });

        int[] lastInterval = intervals[0];

        int toremove=0;

        for(int i=1; i< intervals.length; i++){
            int []current = intervals[i];
            if(current[0] < lastInterval[1]){
                toremove++;
                if(current[1] < lastInterval[1]){
                    lastInterval = current;
                }
            }else{
                lastInterval = current;
            }
        }
        return toremove;
    }

    @Test
    public void test(){
        int[][] intervals = new int[][]{{-52,31},{-73,-26},{82,97},{-65,-11},{-62,-49},{95,99},{58,95},{-31,49},{66,98},{-63,2},{30,47},{-40,-26}};
        System.out.println(eraseOverlapIntervals(intervals));
    }

    public int eraseOverlapIntervals2(int[][] intervals) {
        if(intervals.length <= 1){
            return 0;
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] first, int[] second) {
                if (first[0] == second[0]) {
                    return Integer.compare(first[1], second[1]);
                } else {
                    return Integer.compare(first[0], second[0]);
                }
            }
        });
        int prevEnd = intervals[0][1];
        int count = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (prevEnd > intervals[i][0]) {
                count++;
                prevEnd = Math.min(intervals[i][1], prevEnd);
            } else {
                prevEnd = intervals[i][1];
            }
        }
        return count;
    }
}
