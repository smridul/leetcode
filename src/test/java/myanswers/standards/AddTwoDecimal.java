package myanswers.standards;

import org.junit.Test;

public class AddTwoDecimal {

    public String add(String s1, String s2) {

        int d1 = s1.indexOf('.');
        int d2 = s2.indexOf('.');

        // lengths of decimal parts
        int a1 = d1==-1 ? 0 : s1.length()-1 - d1;
        int a2 = d2==-1 ? 0 : s2.length()-1 - d2;

        StringBuilder sb = new StringBuilder();
        int i = s1.length() - 1;
        int j = s2.length() - 1;
        if (a1 > a2) {
            int toCopy = a1 - a2;
            while (toCopy-- > 0) {
                sb.append(s1.charAt(i--));
            }
        } else if (a1 < a2) {
            int toCopy = a2 - a1;
            while (toCopy-- > 0) {
                sb.append(s1.charAt(j--));
            }
        }

        //now both can be added together
        int carry = 0;
        while (i >= 0 || j >= 0) {
            char ch1 = i >= 0 ? s1.charAt(i) : '-';
            char ch2 = j >= 0 ? s2.charAt(j) : '-';
            if (ch1 == '.' && ch2 == '.') {
                sb.append('.');
                i--;
                j--;
                continue;
            }else if(ch1 == '.'){
                sb.append('.');
                i--;
                continue;
            } else if(ch2 == '.'){
                sb.append('.');
                j--;
                continue;
            }
            int dig1 = ch1=='-' ? 0 : ch1-'0';
            int dig2 = ch2=='-'? 0  : ch2-'0';
            int sum = dig1 + dig2 + carry;
            sb.append(sum % 10);
            carry = sum / 10;
            i--;
            j--;
        }

        if (carry == 1) {
            sb.append(1);
        }
        return sb.reverse().toString();
    }


    @Test
    public void test() {


        System.out.println(add("234.6387", "923.9"));
    }//

    @Test
    public void tes2t() {

       int a = 3;
       String ss = "" + (-a);
       System.out.println(ss);

       System.out.println(Integer.parseInt(ss));

    }
}
