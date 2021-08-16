package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by smridul on 3/1/19.
 */
public class MazeIII {


    int directions[][] = new int[][]{
            {0, 1},// right
            {1, 0},// down
            {0, -1},//left
            {-1, 0}// up
    };

    char dirString[] = new char[]{'r', 'd', 'l', 'u'};

    @Test
    public void test() {
        int maze[][] = {
                {0, 0, 0, 0, 0},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {0, 1, 0, 0, 0},
        };

       System.out.println(findShortestWay(maze, new int[]{4, 3}, new int[]{0, 1}));
    }


    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {

        int[][] visited = new int[maze.length][maze[0].length];
        Coordinate start = new Coordinate();
        start.x = ball[0];
        start.y = ball[1];
        Coordinate end = new Coordinate();
        end.x = hole[0];
        end.y = hole[1];
        ArrayList<PathToHole> results = new ArrayList<>();
        dfs(maze, start, end, new PathToHole("", 0), results, start.x, start.y, visited);

        results.sort(new Comparator<PathToHole>() {
            @Override
            public int compare(PathToHole s1, PathToHole s2) {

                if (s1.distance != s2.distance)  return s1.distance - s2.distance;
                return s1.path.compareTo(s2.path);
            }
        });


        return !results.isEmpty()? results.get(0).path: "IMPOSSIBLE";
    }


  //  public ArrayList<String> findAllWays(int[][] maze, int[] ball, int[] hole) {

   // }


    // try without cached information
    //  do it with cached information
    public void dfs(int[][] maze, Coordinate start, Coordinate end, PathToHole path,
                    ArrayList<PathToHole> results, int i, int j, int[][] visited) {
        if (i == end.x && j == end.y) {
            results.add(new PathToHole(path.path, path.distance));
        }

        visited[i][j] = 1;

        for (int dir = 0; dir < 4; dir++) {
            Coordinate nextCoordinate = getNextCoordinate(i, j, directions[dir], maze.length, maze[0].length, maze, start, end);
            if(visited[nextCoordinate.x][nextCoordinate.y]==0) {
                PathToHole pathToHole = new PathToHole(path.path + dirString[dir], path.distance + nextCoordinate.distance);
                dfs(maze, start, end, pathToHole, results, nextCoordinate.x, nextCoordinate.y, visited);
            }
        }
        visited[i][j] = 0;
    }


    private Coordinate getNextCoordinate(int i, int j, int[] dir, int maxrows, int maxcolumns, int[][]maze, Coordinate start,
                                         Coordinate end) {
        Coordinate next = new Coordinate();
        next.x = i;
        next.y = j;

        while (next.x < maxrows && next.y < maxcolumns && next.x >= 0 && next.y >= 0 && maze[next.x][next.y] == 0) {
            next.x += dir[0];
            next.y += dir[1];
            next.distance++;

            // hole or start return..
            if((next.x == start.x  && next.y ==  start.y) ||
                    (next.x == end.x  && next.y ==  end.y)){
                return next;
            }
        }
        // we are one cell ahead in that direction
        next.x -= dir[0];
        next.y -= dir[1];
        next.distance--;
        return next;
    }
}

class Coordinate {
    int x;
    int y;
    int distance;
}

class PathToHole{
    String path;
    int distance;
     PathToHole(String path, int distance){
        this.path = path;
        this.distance = distance;
    }
}
