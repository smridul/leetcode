package myanswers;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by smridul on 2/17/19.
 */


public class CourseSchedule3 {

    @Test
    public void test() {

       //  int[][] courses = new int[][]{{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}};

       // int[][] courses = {{5,15},{3,19},{6,7},{2,10},{5,16},{8,14},{10,11},{2,19}};
        int[][]   courses = {{7,17},{3,12},{10,20},{9,10},{5,20},{10,19},{4,18}};
        System.out.print(scheduleCourse(courses));
    }

    public int scheduleCourse1(int[][] courses) {
        Arrays.sort(courses, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });

        if (courses == null || courses.length == 0) {
            return 0;
        }

        DpItem[] dp = new DpItem[courses.length];
        dp[0] = new DpItem(1, courses[0][0]);

        int totalMax = 1;
        for (int i = 1; i < courses.length; i++) {
            // let say course[i] is chosen

            // this course has to be chosen on or before
            // course[i][1]-course[i][0]+1
            int max = 1;
            int endtime = courses[i][0];
            dp[i] = new DpItem(max, endtime);
            int onOrBefore = courses[i][1] - courses[i][0] + 1;
            for (int j = 0; j < i; j++) {
                if (dp[j].endTime < onOrBefore) {
                    if(dp[j].max + 1 > max){
                        max = dp[j].max + 1;
                        endtime = dp[j].endTime + courses[i][0];
                    } else if(dp[j].max + 1 == max){
                        if(dp[j].endTime + courses[i][0] < endtime){
                            endtime = dp[j].endTime + courses[i][0];
                        }
                    }
                }
            }
            dp[i].max = max;
            dp[i].endTime = endtime;
            totalMax = Math.max(dp[i].max, totalMax);
        }
        return totalMax;
    }

    public int scheduleCourse(int[][] courses) {
        if (courses == null || courses.length == 0) {
            return 0;
        }

        Arrays.sort(courses, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1] - o2[1];
            }
        });


        PriorityQueue<Integer> pq  = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        int time=0;
        for (int[] course: courses){
            time = time + course[0];
            pq.add(course[0]);

            if(time > course[1]){
                time = time - pq.poll();
            }
        }
        return pq.size();
    }
}

class DpItem {
    int max; // max what can be chosen
    int endTime; // end time

    public DpItem(int max, int endTime) {
        this.endTime = endTime;
        this.max = max;
    }
}