package myanswers;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class NextGreaterII {
    public int[] nextGreaterElements(int[] nums) {


        Deque<Integer> stack = new ArrayDeque<>();
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        for (int i=0; i < nums.length; i++){

            while (!stack.isEmpty() && nums[i] > nums[stack.getLast()] ){
                int index = stack.removeLast();
                result[index] = nums[i];
            }
            stack.addLast(i);
        }


        int largest = stack.getFirst();
        for(int i =0; i<=largest; i++){
            while (!stack.isEmpty() && nums[i] > nums[stack.getLast()] ){
                int index = stack.removeLast();
                result[index] = nums[i];
            }
            stack.addLast(i);
        }

        return result;
    }

    @Test
    public void test(){

        int[] num = new int[]{2};
        int [] res = nextGreaterElements(num);

        for (int i: res){
            System.out.print(" " + i);
        }
    }
}
