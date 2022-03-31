package myanswers;

import org.junit.Test;

import java.util.Arrays;

public class FriendRequestAppropriateAges {
    public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);

        int low = 0;
        int ans = 0;
        for (int i = 0; i < ages.length; i++) {

            int toFound = ages[i] / 2 + 7;

            int validIndex = higherBound(toFound + 1, low, i - 1, ages);
            low = Math.max(validIndex, 0);

            int pair = Math.max(0, i - 1 - validIndex + 1);


            // we can find exact match too on the right side
            if(toFound+1 <= ages[i]) {
                int validIndex2 = matchRightSide(ages[i], i + 1, ages.length - 1, ages);
                if (validIndex2 != -1) {
                    int sameMatch = validIndex2 - (i + 1) + 1;
                    ans += sameMatch;
                }
            }
            ans += pair;

        }

        return ans;
    }


    public int numFriendRequests2(int[] ages) {
        Arrays.sort(ages);

        int ans = 0;
        for (int i = 0; i < ages.length; i++) {
            int toFound = ages[i] / 2 + 7 +1;
            int validIndex = higherBound(toFound, 0, i - 1, ages);
            int validIndex2 = i;
            // we can find exact match too on the right side
            if(toFound <= ages[i]) {
                validIndex2 = matchRightSide(ages[i], i, ages.length - 1, ages);
            }
            ans += Math.max(validIndex2-validIndex+1-1, 0);

        }
        return ans;
    }


    // what this is returned will be index which can be used
    //
    int higherBound(int target, int low, int high, int[] ages) {

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (target > ages[mid]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return ages[low] >= target ? low : low + 1;
    }

    int matchRightSide(int target, int low, int high, int[] ages) {

        if(low>high){
            return -1;
        }
        while (low < high) {
            int mid = low + (high - low) / 2 + 1;

            if (target == ages[mid]) {
                low = mid;
            } else if (target > ages[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ages[low] == target ? low : -1;
    }


    @Test
    public void test() {
        int ages[] = new int[]{16,16};
        ages= new int[]{108,115,5,24,82};

        System.out.println(numFriendRequests3(ages));
    }


    public int numFriendRequests3(int[] ages) {
        int [] count = new int[128];


        for(int i: ages){
            count[i]++;
        }

        int ans=0;
        for(int i =0; i < ages.length; i++){

            int toFind = ages[i] / 2 + 7 +1;

            // iwant to find all elemnts >= than toFind and <= ages[i]. then subtract for same element
            int sum = 0;
            for(int k=1; k < count.length; k++){

                if(k >= toFind && k<= ages[i]){
                    sum+= count[k];
                }

            }
            ans+= Math.max(sum-1, 0);
        }

        return ans;

    }
}
