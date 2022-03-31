package myanswers.calculator;

import java.util.Arrays;
import java.util.Comparator;

public class MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] a, int [] b){

                if(a[0]==b[0]){
                    return a[1]-b[1];
                }
                return a[0] - b[0];
            }
        });

        for(int i=1; i< intervals.length; i++){

            if(intervals[i-1][1] > intervals[i][0]){
                return false;
            }

        }
        return true;
    }
}
