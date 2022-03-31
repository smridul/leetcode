package myanswers;

import org.junit.Test;

import java.util.*;

public class MaxEvents {
    public int maxEvents(int[][] events) {

        // first i will sort based on the ending day
        // if ending is earlier then it is early in the sorte order



        Arrays.sort(events, new Comparator<int[]>(){
            @Override
            public int compare(int [] a, int[] b){
                return Integer.compare(a[0], b[0]);
            }
        });

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int [] a, int[] b){
                return Integer.compare(a[1], b[1]);
            }
        });


        int total=0;
        int index = 0;
        List<int[]> list = new ArrayList<>();
        for(int day=1; day <= 31; day++){

            while(index< events.length && events[index][0] == day){
                pq.offer(events[index]);
                index++;
            }


//            while(!pq.isEmpty() && pq.peek()[1] < day){
//                pq.poll();
//            }
            while(!pq.isEmpty()) {
                int[] event = pq.poll();
                if(day <= event[1]){
                    total++;
                    list.add(event);
                    break;
                }
            }

        }

        for(int[] event: list){
            System.out.print("(" + event[0] +"," + event[1] +") ");
        }
        System.out.println();
        return total;
    }

    @Test
    public void test(){
        int arr[][]= new int[][]{
                {27,27},{8,10},{9,11},{20,21},{25,29},{17,20},{12,12},{12,12},{10,14},{7,7},{6,10},{7,7},{4,8},{30,31},{23,25},{4,6},{17,17},{13,14},{6,9},{13,14}
        };
        System.out.println(maxEvents(arr));
        System.out.println(maxEvents3(arr));

    }


    public int maxEvents3(int[][] A) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int [] a, int[] b){
                return Integer.compare(a[1], b[1]);
            }
        });
        List<int[]> list = new ArrayList<>();
        Arrays.sort(A, (a, b) -> Integer.compare(a[0], b[0]));
        int i = 0, res = 0, n = A.length;
        for (int d = 1; d <= 100000; ++d) {
            while (!pq.isEmpty() && pq.peek()[1] < d)
                pq.poll();
            while (i < n && A[i][0] == d)
                pq.offer(A[i++]);
            if (!pq.isEmpty()) {
                int[] evt = pq.poll();
                ++res;
                list.add(evt);
            }
        }
        for(int[] event: list){
            System.out.print("(" + event[0] +"," + event[1] +") ");
        }
        System.out.println();
        return res;
    }
}

