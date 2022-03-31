package myanswers;


import org.junit.Test;

import java.util.*;

public class MergeIntervals {


    TreeSet<Endpoint> treeSet =null;



    public int[][] merge(int[][] intervals) {

        if(intervals == null || intervals.length<=1){
            return intervals;
        }

        List<int[]> result = new ArrayList<>();

        Comparator<int[]> comparator = new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        };
        Arrays.sort(intervals, comparator);

        result.add(intervals[0]);
        for(int[] interval : intervals){
            int[] lastAddedInterval = result.get(result.size()-1);
            int endLast = lastAddedInterval[1];
            if(endLast >= interval[0]){
                lastAddedInterval[1] = Math.max(endLast, interval[1]);
            }else{
                result.add(interval);
            }
        }

        return result.toArray(new int[result.size()][]);
    }





    @Test
    public void test() {

        List<Interval> intervals = new ArrayList<>();
        //[[1,3],[2,6],[8,10],[15,18]]
        intervals.add(new Interval(1, 3));
        intervals.add(new Interval(2, 6));
        intervals.add(new Interval(8, 10));
        intervals.add(new Interval(15, 18));

//        intervals.add(new Interval(1, 4));
//        intervals.add(new Interval(4, 6));


        List<Interval> result = merge(intervals);
        for (Interval res : result){
            System.out.print("["+res.start + " "+ res.end+ "] ");
        }
    }

    public List<Interval> merge(List<Interval> intervals) {


        List<Endpoint> endpoints = new ArrayList<>();
        for (Interval interval : intervals) {
            endpoints.add(new Endpoint(interval.start, true));
            endpoints.add(new Endpoint(interval.end, false));
        }

        List<Interval> result = new ArrayList<>();

        Collections.sort(endpoints, new Endpoint());
        int start = 0;
        int startPoint = 0;
        for (Endpoint endpoint : endpoints) {

            if(start == 0 && endpoint.start){
                startPoint = endpoint.point;
            }
            if (endpoint.start) {
                start++;
            } else {
                start--;
            }

            if (start == 0) {
                result.add(new Interval(startPoint, endpoint.point));
            }

        }

        return result;
    }


    @Test
    public void testCalendarIII(){

        Comparator<Endpoint> comparator = new Comparator<Endpoint>() {
            @Override
            public int compare(Endpoint o1, Endpoint o2) {
                if (o1.point != o2.point) {
                    return o1.point - o2.point;
                } else {
                    if (o1.start != o2.start) {
                        return o1.start ? -1 : 1;
                    } else {
                        return 0;
                    }
                }
            }
        };

        treeSet = new TreeSet<>(comparator);



        System.out.println(calendarIII(10, 20));
        calendarIII(50, 60);
        calendarIII(10, 40);





    }

    public int calendarIII(int start, int end){

        Endpoint startPoint = new Endpoint(start, true);
        Endpoint endpoint = new Endpoint(end, false);

        treeSet.add(startPoint);
        treeSet.add(endpoint);




        for(Endpoint point : treeSet){
       //    System.out.print(point.point+"[" + point.start+"]" + " ");
        }

        System.out.println();

        int concurrent = 0;
        int maxConcurrent = 0;
        for(Endpoint point : treeSet){
            if(point.start) {
                concurrent++;
            }else{
                concurrent--;
            }
            maxConcurrent = Math.max(concurrent, maxConcurrent);
        }
        return maxConcurrent;
    }


}

class Interval {
    int start;
    int end;

    Interval() {
        start = 0;
        end = 0;
    }

    Interval(int s, int e) {
        start = s;
        end = e;
    }


}

class Endpoint implements Comparator<Endpoint> {
    int point;
    boolean start;

    Endpoint(int point, boolean start) {
        this.point = point;
        this.start = start;
    }
    Endpoint() {
        this.point = 0;
        this.start = true;
    }

    @Override
    public int compare(Endpoint o1, Endpoint o2) {
        if (o1.point != o2.point) {
            return o1.point - o2.point;
        } else {
            if (o1.start != o2.start) {
                return o1.start ? -1 : 1;
            } else {
                return 0;
            }
        }
    }
}
