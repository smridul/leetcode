package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by smridul on 1/8/19.
 */
public class JustifyText {


    @Test
    public void justify() {

        String[] words = new String[]{"Science", "is", "what", "we", "understand", "well", "enough", "to", "explain",
                "to", "a", "computer.", "Art", "is", "everything", "else", "we", "do"};
        int maxWidth = 20;

        int currentCount = 0;
        int currentLineFirstWord = 0;
        int currentLineLastWord = 0;
        List<String> result = new ArrayList<>();
        for (int current = 0; current < words.length; current++) {

            if (currentCount == 0) {
                // first word in line
                currentCount = words[current].length();
                currentLineFirstWord = current;
                currentLineLastWord = current;

            } else if (currentCount + 1 + words[current].length() > maxWidth) {
                //this word cannot be occupied
                // store the line in list.

                result.add(storeLine(currentLineFirstWord, currentLineLastWord, words, maxWidth, false));
                currentCount = 0;
                current--;

            } else {
                currentLineLastWord = current;
                currentCount = currentCount + 1 + words[current].length();
            }

            //last word
            if (current == words.length - 1) {
                result.add(storeLine(currentLineFirstWord, currentLineLastWord, words, maxWidth, true));
            }
        }

        for (String s : result) {
            print(s);
            System.out.println();
        }
    }
    public void print(String s){
        for(char c: s.toCharArray()){
            System.out.print(  c == ' '? '*':c);
        }
    }

    public String storeLine(int start, int end, String[] words, int maxwidth, boolean lastLine) {
        int totalLetters = 0;
        for (int i = start; i <= end; i++) {
            totalLetters += words[i].length();
        }

        int totalSpaces = maxwidth - totalLetters;
        StringBuilder line = new StringBuilder();
        if (start == end) {
            line.append(words[start]);
            line = addSpaces(line, totalSpaces);
            return line.toString();
        }

        int largeSpaceTimes  = totalSpaces % (end - start);

        int remainingSpaces = totalSpaces / (end - start);


        if (lastLine) {
            remainingSpaces = 1;
        }

        for (int i = start; i <= end; i++) {
            if (i == end) {
                // only last word
                line.append(words[end]);
                if (lastLine) {
                    line = addSpaces(line, totalSpaces - (end - start));
                }
            } else {
                //
                line.append(words[i]);
                line = addSpaces(line, largeSpaceTimes-- > 0 ? remainingSpaces+1 : remainingSpaces);
            }
        }
        return line.toString();
    }

    public StringBuilder addSpaces(StringBuilder string, int times) {
        while (times-- > 0) {
            string.append(' ');
        }
        return string;
    }
}
