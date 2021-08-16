package myanswers;


import org.junit.Test;

import java.util.*;

public class WordBreak {


    @Test
    public void wordBreak() {

        String s = "catsanddog";
        String[] wordDict = new String[]{"cat", "cats", "and", "sand", "dog"};

      //  s = "pineapplepenapple";
        //wordDict = new String[]{"apple", "pen", "applepen", "pine", "pineapple"};


        HashMap<String, List<String>> map = new HashMap<>();
        List<String> results = wordBreak(s, Arrays.asList(wordDict), map);
        for (String str : results) {
            System.out.println(str);
        }
    }


    public List<String> wordBreak(String s, List<String> wordDict, HashMap<String, List<String>> map) {

        if(map.containsKey(s)){
            return map.get(s);
        }

        ArrayList<String> results  = new ArrayList<>();
        if(s.isEmpty()){
            results.add("");
            return results;
        }

        for (int i = 0; i < s.length(); i++) {

            String first = s.substring(0, i + 1);
            String second = s.substring(i + 1, s.length());

            if (wordDict.contains(first)) {

                List<String> secondStringWords = wordBreak(second, wordDict, map);

                for(String str: secondStringWords){
                    String secondWord = str.isEmpty() ? "": " " + str;
                    results.add( first.isEmpty() ? str : first + secondWord );
                }
            }
        }
        map.put(s, results);
        return results;
    }

    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for(int i=0; i<=s.length()-1; i++){
            for(int j=0; j<=i; j++){
                String str = s.substring(j, i+1);
                if(dp[j] && wordDictSet.contains(str)){
                    dp[i+1]= true;
                    break;
                }
            }
        }
        return dp[s.length()+1];
    }
}
