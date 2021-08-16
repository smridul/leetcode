package myanswers;

import org.junit.Test;

public class RoboInstruction {

    @Test
    public void test(){
        System.out.println(isRobotBounded("RLLGLRRRRGGRRRGLLRRR"));
    }
    public boolean isRobotBounded(String instructions) {
        int dir = 0;

        int[][] steps = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int x= 0;
        int y=0;
        for(char c  : instructions.toCharArray()){

            if(c=='L'){
                dir = dir-1;
                if(dir<0){
                    dir = dir + 4;
                }
            }
            if(c== 'R'){
                dir = (dir + 1)%4;
            }
            if(c== 'G'){
                x = x + steps[dir][0];
                y = y+ steps[dir][1];
            }
        }

        return dir!=0 || (x==0&& y==0);
    }
}
