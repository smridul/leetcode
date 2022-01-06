package myanswers.easy;

import org.junit.Test;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FindJudge {

    public int findJudge(int n, int[][] trust) {

        if(trust.length==0&&  n==1) {
            return 1;
        }
        int judge = -1;

        Set<Integer> normal = new HashSet<>();
        for (int[] t : trust) {
            normal.add(t[0]);
        }

        for (int[] t : trust) {
            if (judge == -1) {
                judge = t[1];
            }
            if(judge == t[0]){
                judge = -1;
            }
            if (t[1] != judge && !normal.contains(t[1])) {
                return -1; // 2 judge case
            }
        }
        return judge;
    }

    @Test
    public void t() {
        int[][] arr = new int[][]{
                {1, 2},
                {2, 3}
        };

        System.out.println(findJudge(2, arr));
    }
}
