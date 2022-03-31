package myanswers;

import org.junit.Test;

public class NumberToWords1 {
    public String numberToWords(int num) {
        if(num == 0){
            return "Zero";
        }
        StringBuilder sb = new StringBuilder();

        if(num / 1000000000 !=0){

            sb.append(getThousand(num / 1000000000));
            sb.append(" Billion");
            num = num % 1000000000;
            if(num!=0){
                sb.append(" ");
            }
        }

        if(num / 1000000 !=0){

            sb.append(getThousand(num / 1000000));
            sb.append(" Million");
            num = num % 1000000;
            if(num!=0){
                sb.append(" ");
            }
        }


        if(num / 1000!=0){

            sb.append(getThousand(num / 1000));
            sb.append(" Thousand");
            num = num % 1000;
            if(num!=0){
                sb.append(" ");
            }
        }

        sb.append(getThousand(num));
        return sb.toString();
    }


    String one [] = new String[]{ "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String ten [] = new String[]{"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    String teen [] = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

    String getThousand(int num){

        StringBuilder sb = new StringBuilder();

        if(num /100 !=0){
            sb.append(one[num/100]);
            sb.append(" Hundred");
            num = num%100;
            if(num!=0){
                sb.append(" ");
            }
        }

        if(num / 10 !=0 && num / 10 !=1){
            sb.append( ten[num/10]);
            if(num%10 !=0){
                sb.append(" ");
            }
        }

        if(num / 10 ==1){
            sb.append( teen[num%10]);
            return sb.toString();
        }

        sb.append( one[num%10]);
        return sb.toString();

    }

    @Test
    public void test() {
        System.out.println(numberToWords(8098080));
    }
}
