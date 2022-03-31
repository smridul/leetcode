package myanswers;

import org.junit.Test;

public class CustomSortString {
    public String customSortString(String order, String s) {

        int[] count = new int[order.length()];
        StringBuilder str = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int index = order.indexOf(c);
            if (index != -1) {
                count[index]++;
            } else {
                str.append(c);
            }
        }

        for (int i = 0; i < count.length; i++) {
            char c = order.charAt(i);
            while (count[i]-- > 0){
                str.append(c);
            }
        }

        return str.toString();
    }

    @Test
    public void test(){
        System.out.println(customSortString("cbafg", "abcd"));

    }

}
