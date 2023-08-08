import org.junit.Test;

public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int max = (right-left) * Math.min(height[left], height[right]);
        while(left < right){

            max = Math.max(max, (right-left) * Math.min(height[left], height[right]));
           if(height[left] < height[right]){
               left++;
           }else {
               right--;
           }
        }
        return max;
    }

    @Test
    public void test(){
        int [] nums = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(maxArea(nums));
        String s = "abcd";
        System.out.println(s.substring(0, 4));
    }
}
