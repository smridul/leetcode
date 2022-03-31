package myanswers.easy;

import org.junit.Test;

public class AverageStream {
    int sum;
    int currentSize;
    int size;
    int [] arr;
    int lastInsert;

    @Test
    public void test(){
        sum=0;
        this.size=3;
        arr = new int[size];
        lastInsert = -1;

        next(1);
        next(10);
        next(3);
        next(5);
    }

    public double next(int val) {
        if(currentSize < size){
            currentSize++;
        }

        int newInsert = (lastInsert + 1) %size;
        arr[newInsert] = val;
        sum +=val;
        sum-=arr[newInsert];
        lastInsert = newInsert;
        return (double)(sum)/ currentSize;
    }
}
