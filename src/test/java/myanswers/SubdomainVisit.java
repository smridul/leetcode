package myanswers;

import org.junit.Test;

import java.util.*;

public class SubdomainVisit {

    public List<String> subdomainVisits(String[] cpdomains) {

        Map<String, Integer> map = new HashMap<>();

        for (String s : cpdomains) {
            String[] wordFreq = s.split("\\s+");

            String domain = wordFreq[1];
            int freq = Integer.parseInt(wordFreq[0]);
            String[] parts = domain.split("\\.");
            String word = "";
            for (int i = parts.length - 1; i >= 0; i--) {
                //last word
                if (i == parts.length - 1) {
                    word = parts[i];
                } else {
                    word = parts[i] + "." + word;
                }

                map.put(word, map.getOrDefault(word, 0) + freq);
            }
        }
        List<String> result = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            result.add(entry.getValue() +" "+ entry.getKey());
        }

        return result;
    }

    @Test
    public void test(){
        String [] cpdomains = new String[]{"900  google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};

        List<String> res = subdomainVisits(cpdomains);

        for(String s: res){
            System.out.println(s);
        }
    }
}
