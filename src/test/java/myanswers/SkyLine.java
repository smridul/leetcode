package myanswers;

import org.junit.Test;

import java.util.*;

/**
 * Created by smridul on 1/9/19.
 */
public class SkyLine {

    @Test
    public void test() {

        int[][] bb = new int[][]{{2, 9, 10}, {3, 7,15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};

        getSkyline(bb);

        StringBuilder sc  = new StringBuilder();
        sc.append(1);
        System.out.println(sc.toString());

    }

    public List<int[]> getSkyline(int[][] buildings) {




        List<SkyPoint> points = new ArrayList<>();
        for (int[] building : buildings) {

            SkyPoint p1 = new SkyPoint(building[0], building[2], true);
            SkyPoint p2 = new SkyPoint(building[1], building[2], false);
            points.add(p1);
            points.add(p2);
        }

        Collections.sort(points, new Comparator<SkyPoint>() {
            @Override
            public int compare(SkyPoint o1, SkyPoint o2) {
                return o1.x - o2.x;
            }
        });

        PriorityQueue<SkyPoint> queue = new PriorityQueue<SkyPoint>(new Comparator<SkyPoint>() {
            @Override
            public int compare(SkyPoint o1, SkyPoint o2) {
                return o2.y - o1.y;
            }
        });

        List<int[]> result = new ArrayList<>();
        for (SkyPoint point : points) {


            int x = point.x;
            int y = point.y;
            if (!point.start) {

                // to remove the starting point correspoding to this ending point


            } else {
                queue.add(point);
            }


            int maxheightPoint = queue.peek().y;


            if (maxheightPoint > point.y) {
                continue;
            } else {
                // we have to add red point
                result.add(new int[]{x, y});
            }

        }
        return result;
    }

}


class SkyPoint {
    int x;
    int y;
    boolean start;

    public SkyPoint(int x, int y, boolean start) {
        this.x = x;
        this.y = y;
        this.start = start;
    }
}