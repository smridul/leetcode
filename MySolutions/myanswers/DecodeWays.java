package myanswers;


import org.junit.Test;

public class DecodeWays {

    @Test
    public void test() {

        String message = "*";
        System.out.println(message +  " ways:" + numDecodings(message));


        message = "1*";
        System.out.println(message +  " ways:" + numDecodings(message));

        message = "**";
        System.out.println(message +  " ways:" + numDecodings(message));

        message = "*1*1*0";
        System.out.println(message +  " ways:" + numDecodings(message));


        message = "*0**0";
        System.out.println(message +  " ways:" + numDecodings(message));

    }

    public int numDecodings(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int dp[] = new int[s.length() + 1];
        char[] messageArray = s.toCharArray();
        dp[0] = 1;

        for (int i = 0; i < messageArray.length; i++) {
            int ways = 0;
            if (messageArray[i] != '*') {
                // considering non merge case
                if(messageArray[i]!= '0') {
                    ways = dp[i];
                }
                // considering merge case
                if (i - 1 >= 0) {
                    char prev = messageArray[i - 1];

                    if (prev == '*') {
                        ways += mergePossible('1', messageArray[i]) ? dp[i - 1] : 0;
                        ways += mergePossible('2', messageArray[i]) ? dp[i - 1] : 0;
                    } else {
                        ways += mergePossible(prev, messageArray[i]) ? dp[i - 1] : 0;
                    }
                }
            } else {
                // considering non merge case
                ways = dp[i] * 9;
                // considering merge case
                for (int k = 1; k <= 9; k++) {
                    if (i - 1 >= 0) {
                        char prev = messageArray[i - 1];
                        char currentchar = toChar(k);

                        if (prev == '*') {
                            ways += mergePossible('1', currentchar) ? dp[i - 1] : 0;
                            ways += mergePossible('2', currentchar) ? dp[i - 1] : 0;
                        } else {
                            ways += mergePossible(prev, currentchar) ? dp[i - 1] : 0;
                        }
                    }
                }
            }
            dp[i + 1] = ways;
        }
        return dp[s.length()];
    }


    private char toChar(int i) {
        char c = '1';
        return (char) (c + (i - 1));
    }

    private boolean mergePossible(char c1, char c2) {
        StringBuilder s = new StringBuilder();
        s.append(c1).append(c2);

        int intvalue = Integer.parseInt(s.toString());
        if (intvalue <= 26 && intvalue >= 10) {
            return true;
        }
        return false;
    }
}
