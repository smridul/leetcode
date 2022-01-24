package myanswers;

import org.junit.Test;

import java.util.*;

public class AlienDictionary {

    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> map = new HashMap<>();
        for(String s: words){
            for (char c : s.toCharArray()){
                map.put(c, new HashSet<>());
            }
        }


        int [] inDegree =  new int[26];

        for (int k = 1; k < words.length; k++) {
            int i = 0;
            while (i < words[k].length() && i < words[k - 1].length()) {
                if (words[k].charAt(i) != words[k - 1].charAt(i)) {
                    Set<Character> set = map.getOrDefault(words[k - 1].charAt(i), new HashSet<>());
                    if(set.add(words[k].charAt(i))){
                        inDegree[words[k].charAt(i)-'a']++;
                    }
                    map.put(words[k - 1].charAt(i), set);
                    if(!map.containsKey(words[k].charAt(i))){
                        map.put(words[k].charAt(i), new HashSet<>());
                    }
                    break;
                }
                i++;
            }
            if(i < words[k-1].length() && i == words[k].length()) {
                return "";
            }
        }

        Deque<Character> queue = new ArrayDeque<>();
        for(int i=0; i<26; i++){
            if(inDegree[i] == 0 && map.containsKey((char) (i+'a'))){
                queue.addLast((char) (i+'a'));
            }
        }

        StringBuilder result = new StringBuilder();
        while (!queue.isEmpty()){
            char c = queue.removeFirst();
            result.append(c);

            if(map.containsKey(c)) {
                for (char neighbour : map.get(c)) {
                    inDegree[neighbour - 'a']--;
                    if (inDegree[neighbour - 'a'] == 0) {
                        queue.addLast(neighbour);
                    }
                }
            }
        }

        if(result.length() == map.size()){
            return result.toString();
        }
        return "";
    }

    @Test
    public void test(){
        String [] words = new String[]{"wrt", "wr"};
        System.out.println(alienOrder(words));
    }
}
