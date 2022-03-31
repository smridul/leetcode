package myanswers;

import org.junit.Test;

public class ClockAngle {
    public double angleClock(int hour, int minutes) {


        hour= hour%12;

        double minutesAngle = (5* (double) hour + 5* (double) minutes/60) *6;

        double hourAngle = (double)minutes * 6;


        return Math.min(Math.abs(minutesAngle - hourAngle ), 360 - Math.abs(minutesAngle - hourAngle ));

    }

    @Test
    public void test(){
        System.out.println(angleClock(1, 57));
    }
}
