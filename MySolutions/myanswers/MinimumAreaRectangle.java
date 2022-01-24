package myanswers;

import org.junit.Test;

import java.util.*;

public class MinimumAreaRectangle {
    public int minAreaRect(int[][] points) {

        Comparator<int[]> com = new Comparator<int[]>(){
            @Override
            public int compare(int[] p1, int[] p2){

                if(p1[0]==p2[0]){
                    return Integer.compare(p1[1], p2[1]);
                }

                return Integer.compare(p1[0], p2[0]);
            }
        };

        Arrays.sort(points, com);
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for(int [] point : points){

            if(!map.containsKey(point[0])){
                map.put(point[0], new HashSet<>());
            }

            map.get(point[0]).add(point[1]);
        }


        int min = Integer.MAX_VALUE;

        for(int i=0; i < points.length; i++){
            int [] p1 = points[i];
            for(int j=i+1; j < points.length; j++){

                int [] p2= points[j];

                if(p1[0] == p2[0] || p1[1]==p2[1]){
                    continue;
                }

                if(map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])){
                    int area = Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]);
                    min = Math.min(min, area);
                }
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;

    }


    @Test
    public void test() {

        int[][] grid = new int[][]{
                {1,1},{1,3},{3,1},{3,3},{2,2}
        };
        System.out.println(minAreaRect(grid));
    }
}
