package myanswers.syntaxes;

import org.junit.Test;

public class NewTestclass {


    @Test
    public void test() {

        char [] chars = new char[]{'a'};
        int a = compress(chars);
        System.out.println(a);
        System.out.println(new String(chars));
    }


    public int compress(char[] chars) {
        int currentIndex = 0;
        int currentCharStartIndex = 0;
        int writeIndex = 0;
        while (currentIndex < chars.length) {
            if (currentIndex+1 == chars.length || chars[currentIndex+1] != chars[currentCharStartIndex]){
                chars[writeIndex++] = chars[currentCharStartIndex];
                if(currentIndex-currentCharStartIndex+1 > 1) {
                    for (char c : Integer.toString(currentIndex - currentCharStartIndex + 1).toCharArray()) {
                        chars[writeIndex++] = c;
                    }
                }
                currentCharStartIndex = currentIndex + 1;
            }
            currentIndex++;
        }
        return writeIndex;
    }



}
