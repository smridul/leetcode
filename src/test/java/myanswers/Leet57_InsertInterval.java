package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smridul on 1/14/19.
 */
public class Leet57_InsertInterval {


    @Test
    public void test() {

        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1, 2));
        intervals.add(new Interval(3, 5));
        intervals.add(new Interval(6, 7));
        intervals.add(new Interval(8, 10));
        intervals.add(new Interval(12, 16));
        Interval newInterval = new Interval(-3, -2);

//        intervals.add(new Interval(1, 3));
//        intervals.add(new Interval(6, 9));
//        Interval newInterval = new Interval(-1, 3);


        List<Interval> result = insert(intervals, newInterval);

        for (Interval res : result) {
            System.out.print("[" + res.start + " " + res.end + "] ");
        }

    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {

        List<Interval> solution = new ArrayList<>();
        Interval merged = null;
        for (int i = 0; i < intervals.size(); ) {
            Interval thisInterval = intervals.get(i);
            if (newInterval.start > thisInterval.end) {
                solution.add(thisInterval);
                i++;
                continue;
            }
            if (merged == null) {
                merged = new Interval(newInterval.start, newInterval.end);
                merged.start = Math.min(thisInterval.start, newInterval.start);
                while (i < intervals.size() && newInterval.end >= intervals.get(i).start ) {
                    merged.end = Math.max(intervals.get(i).end, newInterval.end);
                    i++;
                }
                solution.add(merged);
                continue;
            }
            solution.add(thisInterval);
            i++;
        }

        if (merged == null) {
            solution.add(newInterval);
        }
        return solution;
    }
}


