package myanswers;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class FrequencySort {
    public String frequencySort(String s) {


        int[][] charCount = new int[256][2];

        for(char c : s.toCharArray()){

            charCount[c][0] = c;
            charCount[c][1]++;
        }

        Arrays.sort(charCount, new Comparator<int[]>(){

            public int compare(int []a, int[] b){
                return Integer.compare(a[1], b[1]);
            }
        });

        StringBuilder sb = new StringBuilder();
        for(int [] c: charCount){
            if(c[1]>0){
                sb.append((char) c[0]);
            }

        }

        return sb.toString();
    }

    @Test
    public void test(){
        System.out.println(frequencySort("tree"));
        double a =0;
        double b =1;
        double x = (a/b);
        double y = (b/a);
        int m1 = (int ) (1.0/0);
        int k=0;

    }
}
