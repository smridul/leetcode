package myanswers;

import org.junit.Test;

/**
 * Created by smridul on 3/3/19.
 */
public class NumberToWords {


    String[] teens = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen",
            "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    String[] tenss = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy",
            "Eighty", "Ninety"};

    String[] oness = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven",
            "Eight", "Nine"};


    @Test
    public void test() {


        System.out.print(numberToWords(1234567891));
    }


    public String numberToWords(int num) {

        if(num==0){
            return "Zero";
        }
        StringBuilder stringBuilder = new StringBuilder();
        int billions = num / 1000000000;
        if (billions != 0) {
            stringBuilder.append(hundredWords(billions) + "Billion ");
        }
        num = num % 1000000000;


        int millions = num / 1000000;
        if (millions != 0) {
            stringBuilder.append(hundredWords(millions) + "Million ");
        }
        num = num % 1000000;

        int thousands = num / 1000;
        if (thousands != 0) {
            stringBuilder.append(hundredWords(thousands) + "Thousand ");
        }
        num = num % 1000;

        stringBuilder.append(hundredWords(num));
        String s = stringBuilder.toString();
        return s.trim();

    }


    private String hundredWords(int number) {
        StringBuilder stringBuilder = new StringBuilder();

        int hundred = number / 100;

        if (hundred != 0) {
            stringBuilder.append(oness[hundred] + " " + "Hundred ");
        }

        int tens = number % 100;
        if (tens / 10 == 1) {
            stringBuilder.append(teens[tens % 10] + " ");
            return stringBuilder.toString();
        } else if (tens / 10 != 0) {
            stringBuilder.append(tenss[tens / 10] + " ");
        }

        int ones = tens % 10;
        if (ones != 0) {
            stringBuilder.append(oness[tens % 10] + " ");
        }
        return stringBuilder.toString();
    }
}
