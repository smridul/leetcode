package myanswers;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by smridul on 3/1/19.
 */
public class MaxChunk {


    @Test
    public void test() {
        int arr[] = new int[]{4, 3, 2, 1, 0};

        System.out.println(maxChunksToSorted1(arr));
        arr = new int[]{1, 0, 2, 3, 4};
        System.out.println(maxChunksToSorted1(arr));

        arr = new int[]{0,4,5,2,1,3};
        System.out.println(maxChunksToSorted1(arr));

        arr = new int[]{2,1,3,4,4};
        System.out.println(maxChunksToSorted1(arr));

        arr = new int[]{4,0,5, 2};
        System.out.println(maxChunksToSorted1(arr));
        System.out.println(maxChunksToSortedLC(arr));

        arr = new int[]{4,0,2, 5};
        System.out.println(maxChunksToSorted1(arr));
        System.out.println(maxChunksToSortedLC(arr));



    }

    // my correct
    public int maxChunksToSorted1(int[] arr) {
        ArrayList<Chunk> chunks = new ArrayList<>();
        for (int i=0; i< arr.length; i++){
            if(chunks.isEmpty() || arr[i] >= chunks.get(chunks.size()-1).max){
                // this is fine
                // create a new chunk
                Chunk chunk = new Chunk();
                chunk.max = arr[i];
                chunk.min = arr[i];
                chunks.add(chunk);
            }else{
                // merge this element in previous chunks
                mergeChunks(chunks, arr[i]);
            }
        }
        return chunks.size();
    }

    private void mergeChunks(ArrayList<Chunk> chunks, int element){

        // merge this element in chunk

        Chunk last = chunks.get(chunks.size()-1);

        last.max = Math.max(element, last.max);
        last.min = Math.min(element, last.min);

        // and then continousle check for chunk consistency
        // if not merge chunks

        while (chunks.size() > 1 && last.min < chunks.get(chunks.size()-2).max){

            last = chunks.get(chunks.size()-1);
            chunks.remove(chunks.size()-1);


            Chunk current = chunks.get(chunks.size()-1);

            chunks.get(chunks.size()-1).max = Math.max(last.max, current.max);
            chunks.get(chunks.size()-1).min = Math.min(last.min, current.min);
            last = current;
        }
    }


    public int maxChunksToSortedLC(int[] arr) {
        int n = arr.length;
        int[] maxOfLeft = new int[n];
        int[] minOfRight = new int[n];

        maxOfLeft[0] = arr[0];
        for (int i = 1; i < n; i++) {
            maxOfLeft[i] = Math.max(maxOfLeft[i-1], arr[i]);
        }

        minOfRight[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            minOfRight[i] = Math.min(minOfRight[i + 1], arr[i]);
        }

        int res = 0;
        for (int i = 0; i < n - 1; i++) {
            if (maxOfLeft[i] <= minOfRight[i + 1]) res++;
        }

        return res + 1;
    }

        //wrong
    public int maxChunksToSorted(int[] arr) {
        // given array is perm of 0 to n-1
        int cycleStart = 0;
        int maxchunks = 0;
        // process cycle by cycle

        while (cycleStart < arr.length){
            cycleStart = processCycle(arr, cycleStart);
            maxchunks++;
        }
        return maxchunks;
    }

    private int processCycle(int[] arr, int cycleStart){
        int maxIndex = cycleStart;
        int nextIndex = arr[cycleStart];

        while (nextIndex!=cycleStart){
            maxIndex = Math.max(maxIndex, nextIndex);
            nextIndex = arr[nextIndex];
        }

        return maxIndex+1;
    }
}

class Chunk{
    int max;
    int min;
}
