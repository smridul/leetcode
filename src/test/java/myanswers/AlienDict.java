package myanswers;

import org.junit.Test;

import java.util.*;

/**
 * Created by smridul on 1/29/19.
 */
public class AlienDict {

    @Test // ctci question
    public void test(){
        String[] projects = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j"};
        String[][] dependencies = {
                {"a", "b"},// right is dependent on left
                {"b", "c"},
                {"a", "c"},
                {"d", "e"},
                {"b", "d"},
                {"e", "f"},
                {"a", "f"},
                {"h", "i"},
                {"h", "j"},
                {"i", "j"},
                {"g", "j"}};




        HashMap<String, Integer> degree = new HashMap<>();
        HashMap<String, Set<String>> adjacency = new HashMap<>();



        for(String p : projects){
            degree.put(p, 0);
        }

        for(String[] dep : dependencies){
            Set set = new HashSet<>();

            if(adjacency.containsKey(dep[0])){
                set = adjacency.get(dep[0]);
            }
            set.add(dep[1]);
            adjacency.put(dep[0], set);
            degree.put(dep[1], degree.get(dep[1]) + 1);
        }


        Queue<String> queue = new LinkedList<>();
        for (String  c : degree.keySet()) {
            if (degree.get(c) == 0) {
                queue.add(c);
            }
        }

        StringBuilder result = new StringBuilder();
        while (queue.size() != 0) {
            String  c = queue.poll();
            result.append(c);

            if (adjacency.containsKey(c)) {
                for (String to : adjacency.get(c)) {
                    degree.put(to, degree.get(to) - 1);
                    if (degree.get(to) == 0) {
                        queue.add(to);
                    }
                }
            }
        }


        if( result.length() == degree.size() ){
            for(char c : result.toString().toCharArray()){
                System.out.print(c + " ");
            }
        } else{
            System.out.print("NOT possible");
        }
    }

    public String alienOrder(String[] words) {

        HashMap<Character, Integer> degree = new HashMap<>();
        HashMap<Character, Set<Character>> adjacency = new HashMap<>();
        for (String word : words) {
            for (char c : word.toCharArray()) {
                degree.put(c, 0);
            }
        }

        for (int i = 0; i < words.length - 1; i++) {
            int len = Math.min(words[i].length(), words[i + 1].length());
            for (int j = 0; j < len; j++) {
                char c1 = words[i].charAt(j);
                char c2 = words[i + 1].charAt(j);
                if (c1 != c2) {
                    Set set = new HashSet<>();
                    if (adjacency.containsKey(c1)) {
                        set = adjacency.get(c1);
                    }
                    if (!set.contains(c2)) {
                        set.add(c2);
                        adjacency.put(c1, set);
                        // we want to get the element with 0 degree at the end, so reverse this order
                        degree.put(c2, degree.get(c2) + 1);
                    }
                    break;
                }
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for (Character c : degree.keySet()) {
            if (degree.get(c) == 0) {
                queue.add(c);
            }
        }
        StringBuilder result = new StringBuilder();
        while (queue.size() != 0) {

            Character c = queue.poll();
            result.append(c);

            if (adjacency.containsKey(c)) {
                for (Character to : adjacency.get(c)) {
                    degree.put(to, degree.get(to) - 1);
                    if (degree.get(to) == 0) {
                        queue.add(to);
                    }
                }
            }
        }
        return result.length() == degree.size() ? result.toString() : "";
    }
}
