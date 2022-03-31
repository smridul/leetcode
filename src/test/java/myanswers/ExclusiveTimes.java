package myanswers;

import org.junit.Test;

import java.util.*;

public class ExclusiveTimes {

    public int[] exclusiveTime(int n, List<String> logs) {

        Stack<String> stack = new Stack<>();

        int lastTimestamp = 0;
        int currentTimestamp = 0;

        int[] times = new int[n];
        for (int i = 0; i < logs.size(); i++) {

            String event = logs.get(i);
            String[] eventPart = event.split(":");
            currentTimestamp = Integer.parseInt(eventPart[2]);

            String stackEvent = null;
            String[] stackEventPart = null;
            if (!stack.isEmpty()) {
                stackEvent = stack.peek();
                stackEventPart = stackEvent.split(":");
            }

            if (eventPart[1].equals("start")) {

                if (!stack.isEmpty()){
                    int totalTime = (currentTimestamp-1) - lastTimestamp;
                    times[Integer.parseInt(stackEventPart[0])] += totalTime;
                }

                stack.push(event);
                times[Integer.parseInt(eventPart[0])]++;
            } else {

                //this is end event
                // so pop the stack
                stack.pop();

                int totalTime = currentTimestamp - lastTimestamp ;
                times[Integer.parseInt(eventPart[0])] += totalTime;
            }

            lastTimestamp = Integer.parseInt(eventPart[2]);
        }
        return times;
    }



    public int[] exclusiveTime2(int n, List<String> logs) {

        Stack<String> stack = new Stack<>();

        int[] times = new int[n];
        for (int i = 0; i < logs.size(); i++) {

            String event = logs.get(i);
            String[] eventPart = event.split(":");

            if (eventPart[1].equals("start")) {
                stack.push(event);
            } else {
                //this is end event
                // so pop the stack
                String stackEvent = stack.pop();
                String[] stackEventPart = stackEvent.split(":");
                int totalTime = Integer.parseInt(eventPart[2]) - Integer.parseInt(stackEventPart[2])+1;
                times[Integer.parseInt(eventPart[0])] += totalTime;

                if(!stack.isEmpty()){
                    stackEvent = stack.peek();
                    stackEventPart = stackEvent.split(":");
                    times[Integer.parseInt(stackEventPart[0])] -= totalTime;
                }
            }
        }
        return times;
    }




    @Test
    public void test() {

        List<String> logs = Arrays.asList("0:start:0","1:start:2","1:end:5","0:end:6");
        int[] n = exclusiveTime2(2, logs);
        for (int i : n) {
            System.out.print(i + " ");
        }
    }
}
