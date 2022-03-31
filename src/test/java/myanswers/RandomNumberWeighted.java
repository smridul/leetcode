package myanswers;

import org.junit.Test;

import java.util.Random;

public class RandomNumberWeighted {
    Random random;
    Mypair[] intervals;

    public void RandomNumberWeighted1(int[] w) {
         random = new Random();
         intervals = new Mypair[w.length];

        double sum = 0;
        for(int i=0; i<w.length; i++){
            sum+=w[i];
        }

        double lastprob = 0;
        for(int i=0; i<w.length; i++){
            double thisprob = w[i]/sum;
            intervals[i] = new Mypair();
            intervals[i].start = lastprob;
            intervals[i].end = lastprob + thisprob;

            lastprob = intervals[i].end;
        }

    }

    public int pickIndex() {

        //genrae random real numner

        double search  = random.nextDouble();
        random.nextInt(4);

        int low=0;
        int high=intervals.length-1;
        int index = 0;
        while(low<= high){
            int mid = low + (high-low)/2;

            if(search < intervals[mid].start){
                high =mid-1;
            }else if(search >= intervals[mid].end){
                low = mid +1;
            }else{
                index = mid;
                break;
            }

        }
        return index;
    }



    @Test
    public void test(){
        int[] num = new int[]{2, 3, 8};
        RandomNumberWeighted1(num);



    }
}
class Mypair
{
    double start;
    double end;
}

