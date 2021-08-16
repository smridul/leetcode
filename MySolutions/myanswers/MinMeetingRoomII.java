package myanswers;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class MinMeetingRoomII {

    public int minMeetingRooms(int[][] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = intervals[i][0];
            ends[i] = intervals[i][1];
        }
        Arrays.sort(starts);
        Arrays.sort(ends);
        int rooms = 0;
        int endsItr = 0;
        int ans = 0;
        for (int i = 0; i < starts.length; ) {
            if (starts[i] < ends[endsItr]) {
                rooms++;
                i++;
            } else {
                endsItr++;
                rooms--;
            }
            ans = Math.max(ans, rooms);
        }
        return ans;
    }


    public int minMeetingRooms2(int[][] intervals) {

        // Check for the base case. If there are no intervals, return 0
        if (intervals.length == 0) {
            return 0;
        }

        Integer[] start = new Integer[intervals.length];
        Integer[] end = new Integer[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        // Sort the intervals by end time
        Arrays.sort(
                end,
                new Comparator<Integer>() {
                    public int compare(Integer a, Integer b) {
                        return a - b;
                    }
                });

        // Sort the intervals by start time
        Arrays.sort(
                start,
                new Comparator<Integer>() {
                    public int compare(Integer a, Integer b) {
                        return a - b;
                    }
                });

        // The two pointers in the algorithm: e_ptr and s_ptr.
        int startPointer = 0, endPointer = 0;

        // Variables to keep track of maximum number of rooms used.
        int usedRooms = 0;

        // Iterate over intervals.
        while (startPointer < intervals.length) {

            // If there is a meeting that has ended by the time the meeting at `start_pointer` starts
            if (start[startPointer] >= end[endPointer]) {
                usedRooms -= 1;
                endPointer += 1;
            }

            // We do this irrespective of whether a room frees up or not.
            // If a room got free, then this used_rooms += 1 wouldn't have any effect. used_rooms would
            // remain the same in that case. If no room was free, then this would increase used_rooms
            usedRooms += 1;
            startPointer += 1;

        }

        return usedRooms;
    }

    @Test
    public void test() {
        int[][] intervals = new int[][]{
                {1, 6},
                {2, 7},
                {3, 8},
                {20, 21}
        };


        // System.out.println(minMeetingRooms(intervals));
        System.out.println(minMeetingRooms2(intervals));


        int[] ca = new int[26];
        String s = "aabc";
        for (char c : s.toCharArray()) ca[c - 'a']++;
        String keyStr = String.valueOf(ca);
        System.out.println("keystr is" + keyStr);
    }
}
