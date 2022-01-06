package myanswers;

import org.junit.Test;

import java.util.*;

public class PermutationTogether {

    List<List<String>> permutationTogether(List<String> strings){
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

        int count[] = new int[26];

        for(char c: string.toCharArray()){
            count[c-'a']++;
        }
        StringBuilder stringBuilder = new StringBuilder("");
        int index=0;
        for(int i:count){
            if(i!=0) {
                stringBuilder.append((char)('a' + index) + ":" + i + ",");
            }
            index++;
        }
        return stringBuilder.toString();
    }

    @Test
    public void test(){
        List<String> arr = Arrays.asList("cat", "cta", "abc", "bca", "apple", "pplea", "a", "b");
        permutationTogether(arr);



        String s = "cm";
        char[] ca = new char[26];
        for (char c : s.toCharArray()) ca[c - 'a']++;
        String keyStr = String.valueOf(ca);
        System.out.println(keyStr);

        //2, 12 cm
        //21, 2 vc
    }
}
