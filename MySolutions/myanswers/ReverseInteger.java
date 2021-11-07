package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ReverseInteger {
    public int reverse(int x) {
        int sign = x > 0 ? 1 : -1;


        int number = 0;
        int newnumber = 0;
        while (x != 0) {

            newnumber = number*10 + (x % 10);
            if(newnumber /10 !=number){
                return 0;
            }
            number = newnumber;
            x=x/10;
        }

        return newnumber;
    }

    @Test
    public void test() {
        System.out.println(reverse(0));

        List<Integer> list = new ArrayList<>();

        list.add(2);
    }
}
