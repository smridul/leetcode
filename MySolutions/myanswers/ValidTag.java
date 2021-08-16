package myanswers;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Stack;

import static myanswers.State.*;

/**
 * Created by smridul on 2/15/19.
 */
// MY solution
public class ValidTag {

    @Test
    public void test() {

        String code = "<DIV>This is the first line <![CDATA[<div>]]></DIV>";
        System.out.println(code + " " + isValid(code));

        code = "<DIV>>>  ![cdata[]] <![CDATA[<div>]>]]>]]>>]</DIV>";
        System.out.println(code + " " + isValid(code));


        code = "<A>  <B> </A>   </B>";
        System.out.println(code + " " + isValid(code));

        code = "<DIV>  div tag is not closed  <DIV>";
        System.out.println(code + " " + isValid(code));

        code = "<DIV>  unmatched <  </DIV>";
        System.out.println(code + " " + isValid(code));

        code = "<DIV> closed tags with invalid tag name  <b>123</b> </DIV>";
        System.out.println(code + " " + isValid(code));

        code = "<DIV> unmatched tags with invalid tag name  </1234567890> and <CDATA[[]]>  </DIV>";
        System.out.println(code + " " + isValid(code));

        code = "<DIV>  unmatched start tag <B>  and unmatched end tag </C>  </DIV>";
        System.out.println(code + " " + isValid(code));

        code = "<![CDATA[wahaha]]]><![CDATA[]> wahaha]]>";
        System.out.println(code + " " + isValid(code));

        code = "<DIV></DIV>a";
        System.out.println(code + " " + isValid(code));

        code = "<A><A>/A></A></A>";
        System.out.println(code + " " + isValid(code));


        code = "<A></A><B></B>";
        System.out.println(code + " " + isValid(code));

        code = "<AAAAAAAAAA></AAAAAAAAAA>";
        System.out.println(code + " " + isValid(code));

        code = "<DIV>This is the first line <![CDATA[<div>]]></DIV>";
        System.out.println(code + " " + isValid(code));

        code = "<A><A></A></A>";
        System.out.println(code + " " + isValid(code));
        System.out.print(generateGCD(0, 0));
    }


    private int generateGCD(int a,int b){

        if (b==0) return a;
        else return generateGCD(b,a%b);

    }

    public boolean isValid(String code) {
        Stack<String> stack = new Stack<>();
        State state = empty;

        char[] codeArray = code.toCharArray();

        for (int i = 0; i < codeArray.length; i++) {
            if (state == anycontent) {
                if (codeArray[i] == '>') {
                    // check if this is end
                    if (codeArray[i - 1] == ']' && codeArray[i - 2] == ']') {
                        state = tagcontent;
                    }
                }
            } else if (state == empty) {
                if (codeArray[i] != '<' || i!=0) {
                    return false;
                } else {
                    state = starttag;
                }
            } else if (state == starttag && codeArray[i] == '/') {
                i++;

                String tagName = getMeTagName(codeArray, i);
                if (tagName.isEmpty()) {
                    return false;
                } else {
                    if(stack.empty()){
                        return false;
                    }

                    if (!tagName.equals(stack.pop())) {
                        return false;
                    }


                }
                i = i + tagName.length() ;
                state = tagcontent;

                if(stack.empty()){
                    state = empty;
                }
            } else if (state == starttag && codeArray[i] == '!') {
                //reach to end of cdata or return false
                i++;

                if (parsecdataStart(codeArray, i) &&
                        !stack.empty()) {// we should be in between here
                    state = anycontent;
                } else {
                    return false;
                }
                i = i+7;
            } else if (state == starttag) {
                String tagName = getMeTagName(codeArray, i);

                if (tagName.isEmpty()) {
                    return false;
                } else {
                    stack.push(tagName);
                }
                state = tagcontent;
                i = i + tagName.length() ;
            } else if (state == tagcontent && codeArray[i] == '<') {
                state = starttag;
            }
        }
        return stack.empty();
    }

    public boolean parsecdataStart(char[] array, int i) {

        StringBuilder s = new StringBuilder();
        if (i + 6 < array.length) {
            for (int j = i; j <= i + 6; j++) {
                s.append(array[j]);
            }
        }
        return s.toString().equals("[CDATA[");
    }

    public String getMeTagName(char[] array, int i) {
        StringBuilder tagname = new StringBuilder();

        for (int j = i; j < array.length; j++) {
            if (array[j] != '>') {
                if (Character.isUpperCase(array[j]) && tagname.length() <= 8) {
                    tagname.append(array[j]);
                } else {
                    return "";
                }
            } else{
                break;
            }
        }
        return tagname.toString();
    }
}

enum State {
    empty,
    starttag,
    tagname,
    anycontent,
    tagcontent
}
