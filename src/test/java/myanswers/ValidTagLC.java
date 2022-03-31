package myanswers;

import org.junit.Test;

import java.util.Stack;

/**
 * Created by smridul on 2/16/19.
 */

// lc solution
public class ValidTagLC {
    Stack< String > stack = new Stack < > ();
    boolean contains_tag = false;
    public boolean isValidTagName(String s, boolean ending) {
        if (s.length() < 1 || s.length() > 9)
            return false;
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isUpperCase(s.charAt(i)))
                return false;
        }
        if (ending) {
            if (!stack.isEmpty() && stack.peek().equals(s))
                stack.pop();
            else
                return false;
        } else {
            contains_tag = true;
            stack.push(s);
        }
        return true;
    }
    public boolean isValidCdata(String s) {
        return s.indexOf("[CDATA[") == 0;
    }




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
    }

    public boolean isValid(String code) {
        if (code.charAt(0) != '<' || code.charAt(code.length() - 1) != '>')
            return false;
        for (int i = 0; i < code.length(); i++) {
            boolean ending = false;
            int closeindex;
            if(stack.isEmpty() && contains_tag)
                return false;
            if (code.charAt(i) == '<') {
                if (!stack.isEmpty() && code.charAt(i + 1) == '!') {
                    closeindex = code.indexOf("]]>", i + 1);
                    if (closeindex < 0 || !isValidCdata(code.substring(i + 2, closeindex)))
                        return false;
                } else {
                    if (code.charAt(i + 1) == '/') {
                        i++;
                        ending = true;
                    }
                    closeindex = code.indexOf('>', i + 1);
                    if (closeindex < 0 || !isValidTagName(code.substring(i + 1, closeindex), ending))
                        return false;
                }
                i = closeindex;
            }
        }
        return stack.isEmpty() && contains_tag;
    }
}