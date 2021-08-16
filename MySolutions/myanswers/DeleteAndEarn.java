package myanswers;

import org.junit.Test;

public class DeleteAndEarn {

    public int deleteAndEarn(int[] nums) {
        int n = 10001;
        int[] values = new int[n];
        for (int num : nums)
            values[num] += num;

        int take = 0, skip = 0;


        int ans = 0;
        int previous = 0;
        int select = 0, notselect = 0;
        for (int i = 1; i <= 10000; i++) {

            // if previous i, not present
            if(values[i-1] == 0){
                previous = ans;
                ans = ans + values[i];
            }else{
                // previous i is present

                // 2 cases
                // select this
                select = previous + values[i];

                // do not select this
                notselect = ans;

                previous = ans;
                ans = Math.max(select, notselect);
            }
        }

        return ans;
    }



    @Test
    public void test(){
        int num[] = new int[]{2,2,3,3,3,4};
        System.out.println(deleteAndEarn(num));
    }
}
