package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupStrings {

    public List<List<String>> groupStrings(String[] strings) {

        Map<String, List<String>> mapToList = new HashMap<>();

        for (String s : strings) {

            String key = getKey(s);
            List<String> list = mapToList.getOrDefault(key, new ArrayList<>());
            list.add(s);
            mapToList.put(key, list);
        }

        List<List<String>> result = new ArrayList<>();
        for (String s : mapToList.keySet()) {
            result.add(mapToList.get(s));
        }
        return result;
    }


    public String getKey(String string) {
        if (string.length() == 1) {
            return "-";
        }
        StringBuilder stringBuilder = new StringBuilder("");
        for (int i = 0; i <= string.length() - 2; i++) {
            int diff = string.charAt(i + 1) - string.charAt(i);
            if (diff < 0) {
                diff += 26;
            }
            stringBuilder.append(diff+"-");
        }
        return stringBuilder.toString();
    }

    @Test
    public void test() {
        String [] array = new String[]{"abc","bcd","acef","xyz","az","ba","a","z","al"};
        System.out.println(groupStrings(array));
    }
}
