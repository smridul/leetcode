package myanswers;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TicTacTor {

    int [][] board ;
    int n;
    int [][] row = new int[2][n];
    int [][] col = new int[2][n];
    int []diag = new int[2];
    int [] diag2 = new int[2];

    @Test
    public void test(){



        n=3;
        board = new int[n][n];


        row = new int[2][n];
        col = new int[2][n];
        diag = new int[2];
        diag2 = new int[2];

        move(0, 0, 1);

    }

    public int move(int r, int c, int player) {

        row[player-1][r]++;
        col[player-1][c]++;
        if(r==c){
            diag[player-1]++;
        }
        if(r+c == n-1){
            diag2[player-1]++;
        }

        if(row[player-1][r] == n || col[player-1][c] ==n||
                diag[player-1] ==n|| diag2[player-1]==n){
            return player;
        }
        return 0;

    }

    public int maxchain(int [] nums){
        Set<Integer> set = new HashSet<>();

        for(int i: nums){
            set.add(i);
        }

        Set<Integer> seen = new HashSet<>();
        int max =0;
        for(int i : nums){
            if(seen.contains(i)){
                continue;
            }

            int up= i+1;
            int upcount=0;
            int downcount=0;
            while(set.contains(up)){
                seen.add(up);
                up = up+1;
                upcount++;
            }
            int down= i-1;
            while(set.contains(down)){
                seen.add(down);
                down = down-1;
                downcount++;
            }
            max= Math.max(max, downcount + upcount +1);
        }
        return max;


    }
}
