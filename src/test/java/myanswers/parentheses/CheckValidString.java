package myanswers.parentheses;

import org.junit.Test;

public class CheckValidString {

    public boolean checkValidString1(String s) {


        int totalStars = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') totalStars++;
        }


        int count = 0;
        int lcount = 0;
        int usedStar1=0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                count++;
            } else if(s.charAt(i) == '('){
                lcount++;
            }else if(s.charAt(i) == ')' && lcount == 0){
                if(count > 0){
                    count--;
                    usedStar1++;
                }else{
                    return false;
                }
            }else {
                lcount--;
            }
        }



        int usedStar2=0;
        StringBuilder sb = new StringBuilder(s);
        sb.reverse();
        if(lcount > 0){
             count = 0;
             lcount = 0;
            for (int i = 0; i < sb.length(); i++) {
                if (sb.charAt(i) == '*') {
                    count++;
                } else if(sb.charAt(i) == ')'){
                    lcount++;
                }else if(sb.charAt(i) == '(' && lcount == 0){
                    if(count > 0){
                        count--;
                        usedStar2++;
                    }else{
                        return false;
                    }
                }else {
                    lcount--;
                }
            }
        }

        return usedStar1 + usedStar2 <=totalStars;
    }



    @Test
    public void test(){
        System.out.println(checkValidString("(((((*(()((((*((**(((()()*)()()()*((((**)())*)*)))))))(())(()))())((*()()(((()((()*(())*(()**)()(())"));
    }

    public boolean checkValidString(String s) {
        int cmin = 0, cmax = 0; // open parentheses count in range [cmin, cmax]
        for (char c : s.toCharArray()) {
            if (c == '(') {
                cmax++;
                cmin++;
            } else if (c == ')') {
                cmax--;
                cmin--;
            } else if (c == '*') {
                cmax++;
                cmin--;
            }
            if (cmax < 0) return false;
            cmin = Math.max(cmin, 0);
        }


        return cmin ==0;
    }
}
