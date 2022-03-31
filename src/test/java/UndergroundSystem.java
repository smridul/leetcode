import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UndergroundSystem {

    Map<String, List> timeSpentMap;
    Map<Integer, CheckIn> checkInMap;

    public UndergroundSystem() {
        timeSpentMap = new HashMap<>();
        checkInMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {

        CheckIn checkIn = new CheckIn();
        checkIn.starttime = t;
        checkIn.station = stationName;

        checkInMap.put(id, checkIn);

    }

    public void checkOut(int id, String stationName, int t) {

        String startStation = checkInMap.get(id).station;

        int endtime = t;

        int timeSpent = t - checkInMap.get(id).starttime;
        if (!timeSpentMap.containsKey(startStation + "_" + stationName)) {
            timeSpentMap.put(startStation + "_" + stationName, new ArrayList());
        }
        timeSpentMap.get(startStation + "_" + stationName).add(timeSpent);
    }

    public double getAverageTime(String startStation, String endStation) {
        List<Integer> list = timeSpentMap.get(startStation + "_" + endStation);
        double sum = 0;
        for (Integer i : list) {
            sum += i;
        }
        return sum / list.size();
    }



    @Test
    public void test(){

        checkIn(10, "Leyton", 3);
        checkOut(10, "Paradise", 8);

        System.out.println(getAverageTime("Leyton", "Paradise"));

        checkIn(5, "Leyton", 10);
        checkOut(5, "Paradise", 16);
        System.out.println(getAverageTime("Leyton", "Paradise"));

    }
}

class CheckIn {
    String station;
    int starttime;
}


