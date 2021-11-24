package myanswers;

import org.junit.Test;

import java.util.*;


public class TimeIntervalDoordash {
    //Input - ("mon 10:00 am", mon 11:00 am)
    //Output - [11005, 11010, 11015...11100]


    public List<Integer> printAllTimeInBetween(String start, String end) {
        TimeInterval startInterval = new TimeInterval(start);
        TimeInterval endInterval = new TimeInterval(end);


        List<Integer> result = new ArrayList<>();

        if (startInterval.compareTo(endInterval)<=0) {
            while (startInterval.compareTo(endInterval)<=0) {
                result.add(startInterval.toInt());
                startInterval.increment();
            }

        } else {
            TimeInterval absoluteEnd = new TimeInterval("sun 11:59 pm");
            while (startInterval.compareTo(absoluteEnd) <=0) {
                result.add(startInterval.toInt());
                int old = startInterval.toInt();
                startInterval.increment();
                // if after incrementing it decreases, then overflow has occured
                if(startInterval.toInt() < old){
                    break;
                }
            }

            while (startInterval.compareTo(endInterval) <=0) {
                result.add(startInterval.toInt());
                startInterval.increment();
            }

        }

        return result;

    }


    @Test
    public void test() {

        List<Integer> result = printAllTimeInBetween("sun 10:00 pm", "mon 11:00 am");
        int a = 0;

    }


}

class TimeInterval implements Comparable<TimeInterval>{
    Integer days;
    Integer hours;
    Integer minutes;
    static Map<String, Integer> daysMap = new HashMap<>();

    static {
        daysMap.put("mon", 1);
        daysMap.put("tue", 2);
        daysMap.put("wed", 3);
        daysMap.put("thu", 4);
        daysMap.put("fri", 5);
        daysMap.put("sat", 6);
        daysMap.put("sun", 7);
    }

    public TimeInterval(String s) {
        //Input - ("mon 10:00 am", mon 11:00 am)
        String[] parts = s.split("\\s+");
        days = daysMap.get(parts[0]);
        hours = Integer.parseInt(parts[1].split(":")[0]);
        minutes = Integer.parseInt(parts[1].split(":")[1]);
        if ("pm".equals(parts[2])) {
            hours = hours + 12;
        }
    }

    public int toInt() {
        return (((days*100)+hours)*100) + minutes;
    }


    public int compareTo(TimeInterval other) {
        int d = days.compareTo(other.days);
        if (d == 0) {
            int h = hours.compareTo(other.hours);
            if (h == 0) {
                int m = minutes.compareTo(other.minutes);
                return m;
            } else {
                return h;
            }
        } else {
            return d;
        }
    }

    public void increment() {
        minutes = minutes + 5;
        hours = hours + (minutes / 60);
        minutes = minutes % 60;

        days = days + hours / 24;
        hours = hours % 24;

        days = (days % 7);
        if (days == 0) {
            days = 7;
        }

        int a = Integer.parseInt(days + "" + hours + "" + minutes);
        return ;
    }

    public void decrement() {
        minutes = minutes - 5;
        hours = hours - (minutes<0 ? 1 : 0);
        minutes = minutes<0? 60-Math.abs(minutes): minutes;

        days = days - (hours<0 ? 1: 0);
        hours = hours < 0 ? 24-Math.abs(hours):hours;

        if (days == 0) {
            days = 7;
        }

        int a = Integer.parseInt(days + "" + hours + "" + minutes);
        return ;
    }

}
