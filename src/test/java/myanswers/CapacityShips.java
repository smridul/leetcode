package myanswers;

public class CapacityShips {
    public int shipWithinDays(int[] weights, int days) {

        int low = 1;
        int high = 0;
        for(int weight :  weights){
           low = Math.max(low, weight);
           high+=weight;
        }

        int mid = 0;
        while (low < high) {
             mid = low + (high - low) / 2;
            if (canThisCapacityShipInNDays(mid, days, weights)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        // when loop terminate low == high
        return low;
    }

    boolean canThisCapacityShipInNDays(int capacity, int N, int[] weights){
        int days = 1;
        int sum =0;
        for(int weight :  weights){
            sum+=weight;
            if(sum > capacity){
                sum = weight;
                days++;
            }
        }
        return days <= N;
    }
}
