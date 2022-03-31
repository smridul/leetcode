package myanswers;

public class KokoBanana {

    public int minEatingSpeed(int[] piles, int h) {

        int high = 0;
        for(int i: piles){
            high = Math.max(high, i);
        }

        int low = 1;

        while(low < high){
            int mid = low + (high-low)/2;

            if(canEatBanana(piles, mid, h)){
                high = mid;
            }else{
                low = mid+1;
            }

        }

        return  canEatBanana(piles, low, h) ? low: -1;

    }


    boolean canEatBanana(int [] n, int speed, int h){

        int hours =0;
        for(int i: n){

            hours+= Math.ceil((double)(i)/speed);
        }

        return hours<=h;
    }
}
