package myanswers;

import org.junit.Test;

import java.util.*;

public class GetOrder {
    public int[] getOrder(int[][] tasks) {


        int[][] sortedTasks= new int[tasks.length][3];
        for(int i=0; i < tasks.length; i++){
            sortedTasks[i][0] =  i;
            sortedTasks[i][1] = tasks[i][0];
            sortedTasks[i][2] = tasks[i][1];
        }


        Arrays.sort(sortedTasks, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                return Integer.compare(a[1], b[1]);
            }
        });

        boolean[] added = new boolean[tasks.length];


        int [] ans = new int[tasks.length];

        // [taskid, processingtime]
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>(){
            public int compare(int[]a, int[] b){
                return Integer.compare(a[1], b[1]);
            }
        });



        int currentTime = sortedTasks[0][1];
        int i=0;
        while(i < tasks.length){



            // get all tasks available started<= currentTime
            for(int k=0; k < sortedTasks.length; k++){

                if(!added[k] && sortedTasks[k][1] <= currentTime){
                    pq.offer(new int[]{k, sortedTasks[k][2]});
                    added[k] = true;
                }

            }


            if(pq.isEmpty()){
                //tomhandle if currenttime is less than any available time of time
                currentTime = sortedTasks[i][1];
                continue;
            }

            int[] task = pq.poll();
            ans[i] = sortedTasks[task[0]][0];
            i++;
            currentTime += task[1];

        }


        return ans;


    }




    @Test
    public void test(){
        int[][] a = new int[][]{{19,13},{16,9},{21,10},{32,25},{37,4},{49,24},{2,15},{38,41},{37,34},{33,6},{45,4},{18,18},{46,39},{12,24}};

        getOrder(a);
    }

}
