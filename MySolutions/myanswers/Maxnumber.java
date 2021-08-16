package myanswers;

import org.junit.Test;

import java.util.*;

/**
 * Created by smridul on 3/4/19.
 */
public class Maxnumber {

/*
    @Test
    public void test(){

        int []nums1 = new int[]{3, 4, 6, 5};
        int []nums2 = new int[]{9, 1, 2, 5, 8, 3};
        int k = 5;



        nums1 = new int[]{6, 7};
        nums2 = new int[]{6, 0, 4};
        k=5;



        nums1 = new int[]{3, 9};
        nums2 = new int[]{8, 9};
        k=3;


        int [] res = maxNumber(nums1, nums2, k);
        for (int i: res)
        System.out.print(i+" ");
    }

    public int[] maxNumber(int[] nums1, int[] nums2, int k) {

        HashMap<Integer, Deque<Integer>[][]> memo = new HashMap<>();

        Deque<Integer> list=  maxNumberHelper(nums1, nums2, k, 0, 0, memo);
        int []result = new int[list.size()];
        int i=0;
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            result[i] = (int)iterator.next();
            i++;
        }
        return result;
    }


    public Deque<Integer> maxNumberHelper(int[] nums1, int[] nums2, int k, int firstIndex, int secondIndex,   HashMap<Integer, Deque<Integer>[][]>  memo) {

        // first step to find till which we can scan the array to find the element

        if(k==0){
            return  new ArrayDeque<>();
        }


        if(memo.containsKey(k)){
            Deque<Integer>[][] array = memo.get(k);
            if(array[firstIndex][secondIndex] !=null){
                return array[firstIndex][secondIndex];
            }
        }




        int maxA = Math.max(0, k-1 - (nums2.length-secondIndex));
        int maxB = Math.max(0, k-1 - (nums1.length - firstIndex));

        // we can scan A from firstindex to maxA
        // we can scan B from secondindex to maxB

        int firstMax = findMax(nums1, firstIndex, nums1.length-1-maxA);
        int secondMax = findMax(nums2, secondIndex, nums2.length-1-maxB);

        if(firstMax ==  -1){
            // all from second
            Deque<Integer> answerk_1 =  maxNumberHelper(nums1, nums2, k-1, firstIndex, secondMax+1, memo);
            answerk_1.addFirst(nums2[secondMax]);
            return answerk_1;

        } else if(secondMax == -1){
            // all from first
            Deque<Integer> answerk_1 =  maxNumberHelper(nums1, nums2, k-1, firstMax+1, secondIndex,memo);
            answerk_1.addFirst(nums1[firstMax]);
            return answerk_1;
        }


        if(nums1[firstMax] != nums2[secondMax]){
            Deque<Integer> answerk_1 =  maxNumberHelper(nums1, nums2, k-1, nums1[firstMax] > nums2[secondMax]? firstMax+1: firstIndex,
                    nums1[firstMax] > nums2[secondMax] ? secondIndex : secondMax+1,memo);

            answerk_1.addFirst(Math.max(nums1[firstMax], nums2[secondMax]));
            return answerk_1;
        } else{
            // they are same

            // 2 options
            int sameDigit = nums1[firstMax];

            // if selected from A
            Deque<Integer> answerk_1_1 =  maxNumberHelper(nums1, nums2, k-1, firstMax+1, secondIndex,memo);

            // if selected from B
            Deque<Integer> answerk_1_2 =  maxNumberHelper(nums1, nums2, k-1, firstIndex, secondMax+1,memo);

            // select which one is bigger
            if(bigger(answerk_1_1, answerk_1_2)){
                answerk_1_1.addFirst(sameDigit);
                return answerk_1_1;
            }else{
                answerk_1_2.addFirst(sameDigit);
                return answerk_1_2;
            }
        }
    }


    private void store(HashMap<Integer, Deque<Integer>[][]>  memo, Deque<Integer> answer, int k, int i, int j){

        if(memo.containsKey(k)){

            Deque<Integer>[][] newArray =  memo.get(k);

            newArray[][]
        }else{

        }



        Deque<Integer>[][] newArray =  memo.getOrDefault(k, new Deque<Integer>[nums1.length][nums2.length]);
        memo.put(k, )

    }

    private int findMax(int[] arr, int start, int end){
        int maxIndex = -1;
        int max = Integer.MIN_VALUE;
        for(int i = start; i <= end; i++){
            if(arr[i] > max){
                max = arr[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private boolean bigger(Deque<Integer> answer1, Deque<Integer> answer2){


        Object[] answer1Array = answer1.toArray();
        Object[] answer2Array = answer2.toArray();

        for(int i = 0; i < answer1.size(); i++){
            int firstValue = (Integer)answer1Array[i];
            int secondValue = (Integer)answer2Array[i];
            if(firstValue  !=  secondValue ){
                return firstValue > secondValue;
            }
        }
        return false;
    }
    */

    @Test
    public void test2(){

        int []quality = new int[]{10,20,5};
        int [] wage = new int[]{70,50,30};
        int k = 2;
        mincostToHireWorkers(quality, wage, k);
    }

    public double mincostToHireWorkers(int[] q, int[] w, int K) {
        double[][] workers = new double[q.length][2];
        for (int i = 0; i < q.length; ++i)
            workers[i] = new double[]{(double)(w[i]) / q[i], (double)q[i]};
        Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));
        double res = Double.MAX_VALUE, qsum = 0;
        PriorityQueue<Double> pq = new PriorityQueue<>();
        for (double[] worker: workers) {
            qsum += worker[1];
            pq.add(-worker[1]);
            if (pq.size() > K) qsum += pq.poll();
            if (pq.size() == K) res = Math.min(res, qsum * worker[0]);
        }
        return res;
    }
}
