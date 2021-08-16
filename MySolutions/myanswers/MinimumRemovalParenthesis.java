package myanswers;

import org.junit.Test;


public class MinimumRemovalParenthesis {

    public String minRemoveToMakeValid(String s) {
        StringBuilder str = process(s, '(', ')');
        str = process(str.reverse().toString(), ')', '(');
        return str.reverse().toString();
    }

    public StringBuilder process(String str, Character start, Character end) {
        int counter = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : str.toCharArray()) {
            if (c == start) {
                counter++;
                stringBuilder.append(c);

            } else if (c == end && counter == 0) {
                //ignore
            } else if (c == end) {
                counter--;
                stringBuilder.append(c);
            } else {
                stringBuilder.append(c);
            }
        }
        return stringBuilder;
    }

    @Test
    public void testt() {

        String s =  "(a(b(c)d)";
        System.out.println(minRemoveToMakeValid(s));
    }
}
