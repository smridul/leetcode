package myanswers;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.*;

public class MaxTaskAssign {

    boolean [] done ;
    int pillworkerindex;
    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {

        // sort both array in decresing order

        //get the first heaviest task which this person can do
        //if there is no such task then get the first lightest task
        // that this person can complete by pill. that means scan from right
        // and get the first undone task

        /*
        since decreasing order srot for int array for difficutl i made the change
        of logic keeping in incresing order
        */

        Arrays.sort(tasks);
        Arrays.sort(workers);

        done = new boolean[tasks.length];
        int count=0;

        pillworkerindex= 0;

        for(int i = workers.length-1; i >=0; i--){

            int k = getTaskWhichCanBeDoneWithoutPill(tasks, workers[i]);

            if(k==-1){

                if(pills==0){
                    break;
                }

                k = getTaskWhichCanBeDoneWithPill(tasks, workers[i], strength);
                pills--;
            }

            if(k==-1){
                break;
            }
            count++;
        }


        return count;
    }

    int getTaskWhichCanBeDoneWithoutPill(int[] tasks, int workerStrength){

        for(int i=tasks.length-1; i >= 0; i--){

            if(done[i]){
                continue;
            }

            if(tasks[i] <= workerStrength){
                done[i] = true;
                return i;
            }

        }
        return -1;

    }


    int getTaskWhichCanBeDoneWithPill(int[] tasks, int workerStrength, int strength){

        for(int i=0; i < tasks.length; i++){

            if(done[i]){
                continue;
            }

            // i is first task which is undone
            if(workerStrength + strength  >= tasks[i]){
                done[i] = true;
                //pillworkerindex = worker+1;
                return i;
            }

        }



        return -1;
    }

    @Test
    public void test() {

//        int[] tasks = new int[]  {5181,2717,7678,7730,5931,8066,2266,5873,3645,6636,3308,2848,2082,7158,5398,4030,4942,1723,6614,5165,8086,7526,9503,2051,5305,6606,7514,5078,1149,5782,4717,5969,4966,1292,4370,3863,4111,1140,2980,5295,5347,8700,2833,6750,2352,7604,6305,2697,7501,7719,7955,7901,1779,6850,6456,1040,9230,2712,8129,9875,9385,1814,8167,2960,9191,3588,7339,2255,5314,2873,3294,5375,6745,5984,9717,4983,2558,8075,7988,6490,4499,7236,2097,8097,2923,2972,8609,8993,6354,6502,3340,1666,1281,9703,8869,5274,8150,5270,3437,3171,7423,5865,1995,7002,8550,9908,7114,8777,1250,5855,3501,9316,5380,3877};
//        int[] workers = new int[] {2167,4646,1582,1102,2113,1258,4341,3193,3136,4096,3311,1501,3499,1815,1282,4914,772,4785,2632,1223,3479,3010,3505,1613,4257,1192,2918,2664,4274,4036,1039,1250,4713,3443,4514,4117,3400,3825,1782,3552,2386,865,2290,3618,793,1297,908,2187,3273,4531,3859,605,4274,3951,583,1135,2802,3585,727,2359,4011,4071,2035,4775,764,4702,2050,3304,3876,3772,4946,4371,1993,4746,1124,1221,1368,831,2337,506,951,3874,3094,2744,4258,4704,3229,1015,4876,1893,3098,4464,4189,4201,3986,3673,4126,2424,4280,2780,1748,1650,1591,753,3392,2498,835,608,1746,1243,3778,1382,4207,1909,832,4501,781,1274,973,4966,1873,2512,3644,3244,1120,4979,3945,1481,2172,4410,3572,4597,3414,4306,4714,4047,3239,4557,3226,3273,4997,3374};
//        int pills = 139;
//        int strength = 2075;


        int[] tasks = new int[]{4, 6, 7};

        int[] workers = new int[]{ 1, 2, 4, 6};
        int pills=2;
        int strength = 3;

//         int[] tasks = new int[]{4, 6, 7,10};
//
//        int[] workers = new int[]{ 6, 1, 2, 4};
        System.out.println(maxTaskAssign(tasks, workers, pills, strength));


    }














    public int maxTaskAssign1(int[] tasks, int[] workers, int pills, int strength) {
        int left = 0, right = Math.min(tasks.length, workers.length);
        Arrays.sort(tasks);
        Arrays.sort(workers);
        while(left+1<right)
        {
            int mid = left + (right - left)/2;
            if(canAssign(mid, tasks, workers, pills, strength))
            {
                left = mid;
            }
            else
            {
                right = mid;
            }
        }

        if(canAssign(right, tasks, workers, pills, strength))
        {
            return right;
        }
        else return left;
    }

    public boolean canAssign(int count, int[] tasks, int[] workers, int pills, int strength){
        Deque<Integer> dq = new ArrayDeque<>();
        int ind = workers.length - 1;
        for (int i = count - 1; i >= 0; i--) {
            while(ind>=workers.length-count && workers[ind]+strength>=tasks[i])
            {
                dq.offerLast(workers[ind]);
                ind--;
            }

            if(dq.isEmpty())return false;
            if(dq.peekFirst()>=tasks[i])
            {
                dq.pollFirst();
            }
            else
            {
                dq.pollLast();
                pills--;
                if(pills<0)return false;
            }
        }

        return true;
    }








}
