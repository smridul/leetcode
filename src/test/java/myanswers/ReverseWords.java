package myanswers;

import org.junit.Test;

public class ReverseWords {
    public String reverseWords1(String s) {

        String[] tokens =  s.split("\\s+");


        StringBuilder sb = new StringBuilder();

        int i=0;
        int j = tokens.length-1;
        while(i < j){
            String temp = tokens[i];
            tokens[i] =tokens[j];
            tokens[j] = temp;
            i++;
            j--;

        }



        for(i=0; i < tokens.length; i++){

            sb.append(tokens[i]);
            if(i < tokens.length-1){
                sb.append(" ");
            }

        }

        return sb.toString();

    }
    @Test
    public void test(){
        String s = "hello world  ";
        System.out.println("'" + reverseWords(s)+ "'");
    }



    public String reverseWords(String s) {
        // converst string to string builder
        // and trim spaces at the same time
        StringBuilder sb = trimSpaces(s);

        // reverse the whole string
        reverse(sb, 0, sb.length() - 1);

        // reverse each word
        reverseEachWord(sb);

        return sb.toString();
    }










    public StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        // remove leading spaces
        while (left <= right && s.charAt(left) == ' ') ++left;

        // remove trailing spaces
        while (left <= right && s.charAt(right) == ' ') --right;

        // reduce multiple spaces to single one
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);

            if (c != ' ') sb.append(c);
            else if (sb.charAt(sb.length() - 1) != ' ') sb.append(c);

            ++left;
        }
        return sb;
    }

    public void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    public void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start=0;
        int end = 0;


        while(end < n){

            while(end < n && end !=' '){
                end++;
            }

            // now end it at space or (last char+1)

            reverse(sb, start, end-1);
            start = end +1;
            end++;

        }


    }


}
