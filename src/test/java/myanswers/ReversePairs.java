package myanswers;


import org.junit.Test;

public class ReversePairs {


    @Test
    public void test() {

         //int nums[] = new int[]{2, 4, 3, 5, 1};


        // int nums[] = new int[]{4, 5, 0, 1, 6};

        //int nums[] = new int[]{5, 4, 3, 2, 1};
    //    int nums[] =new int[] {9,9,-9,-9,-9,9};

        int nums[] =new int[] {2147483647,2147483647,-2147483647,-2147483647,-2147483647,2147483647};
        //   getBothNumbers(0, 1, 2, 4, nums);

        System.out.print(reversePairs(nums));
    }


    public int reversePairs(int[] nums) {
        return reversePairs(nums, 0, nums.length - 1);
    }

    public int reversePairs(int[] nums, int start, int end) {

        if (start >= end) {
            return 0;
        }

        int mid = (start + end) / 2;

        int left = reversePairs(nums, start, mid);

        int right = reversePairs(nums, mid + 1, end);

        int both = getBothNumbers(start, mid, mid + 1, end, nums);

        mergeArray(start, mid, mid + 1, end, nums);

        return left + right + both;

    }

    int getBothNumbers(int a, int b, int x, int y, int[] nums) {
        int i = a;
        int j = x;
        int count = 0;
        int smallerIndexesInRightSide = 0;
        /*while (i <= b && j <= y) {

            // condition true
          //  if (nums[i] > 2L * nums[j]) {

           if (  (double)nums[i] / 2 >  nums[j]) {
                count++;
                smallerIndexesInRightSide++;
                j++;
            } else {
                // condition false
                i++;
                if (i <= b) {
                    count = count + smallerIndexesInRightSide;
                }
            }
        }
        i++;
        while(i <= b){
            count = count + smallerIndexesInRightSide;
            i++;
        }
         */



        // I FOUND THIS HELLA COMPLEX

        // earlier i did the above but realised that only i<=b is required
        while (i <= b) {
            // condition true
            if(j<=y && (double)nums[i]/2  >  nums[j]){
                count++;
                j++;
                smallerIndexesInRightSide++;
            }else{
                // condition false
                i++;
                if(i<=b) count = count + smallerIndexesInRightSide;
            }
        }


        // no point in increasing J because we have already reached end of i, now increeasing J will not increasse
        // count for SURE
        return count;
    }

    void mergeArray(int a, int b, int x, int y, int[] nums) {

        int i = a;
        int j = x;

        int temp[] = new int[y - a + 1];
        int current = 0;
        while (i <= b && j <= y) {

            if (nums[i] < nums[j]) {
                temp[current++] = nums[i];
                i++;
            } else {
                temp[current++] = nums[j];
                j++;
            }
        }

        // Store remaining elements of second array
        while (i <= b)
            temp[current++] = nums[i++];

        // Store remaining elements of second array
        while (j <= y)
            temp[current++] = nums[j++];


        current = 0;
        for (int k = a; k <= y; k++) {
            nums[k] = temp[current++];
        }
        return;
    }

}
