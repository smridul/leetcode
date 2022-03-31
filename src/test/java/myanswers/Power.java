package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static myanswers.Utils.printIntegerList;

public class Power {

    public double myPow(double x, int n) {
        if(n<0){
            return 1/myPow2(x, n*-1);
        }
        return myPow2(x, n);

    }
    public double myPow2(double x, int n) {
        if(n==1){
            return x;
        }
        if(n%2==0){
            double a = myPow2(x, n/2);
            return a*a;
        }else {
            double a = myPow2(x, n/2);
            return a*a*x;
        }
    }

    @Test
    public void test() {
        System.out.println(myPow(2.0, -2));

       int [] a = new int[]{1, 2 ,3};
    }
}
