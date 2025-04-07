package myanswers;

import org.junit.Test;

import java.util.*;

public class KthSmallestPairs {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<>(){
            public int compare(int []a, int [] b){
                return Integer.compare(nums1[a[0]]+nums2[a[1]], nums1[b[0]]+nums2[b[1]]);
            }
        });



        pq.offer(new int [] {0,  0});



        List<List<Integer>> ans = new ArrayList<>();
        Set<String> set = new HashSet<>();

        while(k-- >0 && !pq.isEmpty()){


            int cell[] = pq.poll();
            int num1Index = cell[0];
            int num2Index = cell[1];

            ans.add(Arrays.asList(nums1[num1Index], nums2[num2Index]));


            if(num1Index+1 < nums1.length && !set.contains((num1Index+1) + ","  + num2Index)){
                pq.offer(new int[]{num1Index+1, num2Index});
                set.add((num1Index+1) + ","  + num2Index);
            }

            if(num2Index+1 < nums2.length && !set.contains(num1Index + ","  + (num2Index+1))){
                pq.offer(new int[]{num1Index, (num2Index+1)});
                set.add(num1Index + ","  + (num2Index+1));
            }

        }

        return ans;
    }

    @Test
    public void test(){
        int[] aa = new int[]{1,2,4,5,6};
        int[] bb = new int[]{3,5,7,9};

        System.out.println(kSmallestPairs(aa, bb, 20));
    }

}
