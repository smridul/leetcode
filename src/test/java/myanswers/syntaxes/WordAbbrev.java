package myanswers.syntaxes;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by smridul on 3/5/19.
 */
public class WordAbbrev {

    @Test
    public void test() {
        List<String> arr = Arrays.asList("like", "god", "internal", "me", "internet", "interval", "intension", "face", "intrusion");
        List<String> res = wordsAbbreviation(arr);
        for (String s : res) {
            System.out.print(s + " ");
        }
    }


    public List<String> wordsAbbreviation(List<String> dict) {

        HashMap<String, Integer> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        for (String s : dict) {
            if (s.length() <= 3) continue;
            String last = s.substring(s.length() - 1);
            int middle = s.length() - 2;
            int k = 1;
            for (int i = middle; i > 1; i--) {
                String prefix = s.substring(0, k);
                String key = prefix + i + last;
                map.put(key, map.getOrDefault(key, 0) + 1);
                k++;
            }
        }

        for (String s : dict) {
            if (s.length() <= 3) {
                res.add(s);
                continue;
            } else {
                String last = s.substring(s.length() - 1);
                int middle = s.length() - 2;
                int k = 1;
                int i = middle;
                for (; i > 1; i--) {
                    String prefix = s.substring(0, k);
                    String key = prefix + i + last;

                    if(map.get(key)==1){
                       res.add(key);
                        break;
                    }
                    k++;
                }
                if(i==1) {
                    res.add(s);
                }
            }
        }

        return res;
    }
}
