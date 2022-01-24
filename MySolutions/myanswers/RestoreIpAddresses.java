package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RestoreIpAddresses {

    public List<String> restoreIpAddresses(String s) {

        if (s.length() < 4) {
            return new ArrayList<>();
        }
        // form base case

        List<String>[][] dp = new List[4][s.length()];

        for (int i = s.length() - 1; i >= s.length() - 3; i--) {
            String token = s.substring(i);
            if (isValidToken(token)) {
                dp[0][i] = Arrays.asList(token);
            }
        }


        for (int i = 1; i <= 3; i++) {
            for (int j = 0; j < s.length(); j++) {

                // max three length token can be constructed
                dp[i][j] = new ArrayList<>();
                for (int k = 1; k <= 3 && j + k < s.length(); k++) {

                    String token = s.substring(j, j + k);
                    if (isValidToken(token)) {
                        List<String> list = dp[i - 1][j + k];
                        if (list != null && !list.isEmpty()) {
                            for (String a : list) {
                                String newToken = token + "." + a;
                                dp[i][j].add(newToken);
                            }
                        }
                    }
                }
            }
        }
        return dp[3][0];
    }


    boolean isValidToken(String token) {
        try {
            int val = Integer.parseInt(token);
            if (token.startsWith("0") && !token.equals("0") || val > 255) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }


    @Test
    public void test() {
        List<String> res = restoreIpAddresses("101023");
        for(String s: res){
            System.out.println(s);
        }
    }
}
