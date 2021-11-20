package myanswers;

import org.junit.Test;
import java.util.Stack;

public class UnifyPath {

    public String simplifyPath1(String path) {
        int index = 0;
        Stack<String> stack = new Stack<>();
        String[] components  = path.split("/");
        while (index < components.length) {
            String token = components[index];
            if (token.equals("/") || token.equals(".") || token.isEmpty()) {

            } else if (token.equals("..") ) {
                if(!stack.isEmpty())stack.pop();
            } else {
                stack.push("/"+token);
            }
            index++;
        }
        String res = "";
        while (!stack.isEmpty()) {
            res = stack.pop() + res;
        }
        return res.isEmpty()? "/": res;
    }

    public String simplifyPath(String path) {
        int index = 0;
        Stack<String> stack = new Stack<>();
        while (index < path.length()) {
            String token = getToken(path, index);
            index = index + token.length();
            if (token.equals("/")) {

            } else if (token.equals(".")) {

            } else if (token.equals("..") ) {
                if(!stack.isEmpty())stack.pop();
            } else {
                stack.push("/"+token);
            }
        }
        String res = "";
        while (!stack.isEmpty()) {
            res = stack.pop() + res;
        }
        return res.isEmpty()? "/": res;
    }


    String getToken(String s, int index) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = index;
        while (i < s.length() && (i == index || type(s.charAt(i)) == type(s.charAt(i - 1)))) {
            if (s.charAt(i) == '/') {
                return "/";
            }
            stringBuilder.append(s.charAt(i));
            i++;
        }
        return stringBuilder.toString();


    }

    int type(char c) {
        if (c == '/') {
            return 1;
        } else {
            return 2;
        }
    }


    @Test()
    public void test(){
        System.out.println(simplifyPath1("/../"));
    }

}
