package myanswers;

import org.junit.Test;

import java.util.Stack;

/**
 * Created by smridul on 2/16/19.
 */

// lc solution
public class ValidTag2 {


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
        Stack<String> stack = new Stack<>();
        for(int i = 0; i < code.length();){
            if(i>0 && stack.isEmpty()) return false;
            if(code.startsWith("<![CDATA[", i)){
                int j = i+9;
                i = code.indexOf("]]>", j);
                if(i < 0) return false;
                i += 3;
            }else if(code.startsWith("</", i)){
                int j = i + 2;
                i = code.indexOf('>', j);
                if(i < 0 || i == j || i - j > 9) return false;
                for(int k = j; k < i; k++){
                    if(!Character.isUpperCase(code.charAt(k))) return false;
                }
                String s = code.substring(j, i++);
                if(stack.isEmpty() || !stack.pop().equals(s)) return false;
            }else if(code.startsWith("<", i)){
                int j = i + 1;
                i = code.indexOf('>', j);
                if(i < 0 || i == j || i - j > 9) return false;
                for(int k = j; k < i; k++){
                    if(!Character.isUpperCase(code.charAt(k))) return false;
                }
                String s = code.substring(j, i++);
                stack.push(s);
            }else{
                i++;
            }
        }
        return stack.isEmpty();
    }
}
