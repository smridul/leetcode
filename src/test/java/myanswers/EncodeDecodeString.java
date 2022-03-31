package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class EncodeDecodeString {
    @Test
    public void test() {

        List<String> strs = Arrays.asList("abc", "aacd:", ":bcd");

        String s = encode(strs);
        List<String> strs1 = decode(s);
        for(String s1: strs1){
            System.out.print(s1 + " ");
        }
    }


    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            String s = encode(str);
            sb.append(s);
            sb.append(":");
        }

        return sb.toString();
    }

    String encode(String str) {
        StringBuilder sb = new StringBuilder();
        for (char c : str.toCharArray()) {
            sb.append('a');
            sb.append(c);
        }
        return sb.toString();
    }

    public List<String> decode(String s) {

        List<String> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == 'a') {
                i++;
                sb.append(s.charAt(i));
            } else {
                list.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        return list;
    }


}
