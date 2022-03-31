package myanswers;

import org.junit.Test;

import javax.swing.plaf.metal.MetalTheme;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CuttingRibbons {

    public int maxLength1(int[] ribbons, int k) {

        int max = Integer.MIN_VALUE;
        for (int i : ribbons) {
            max = Math.max(max, i);
        }
        int low = 1;
        int high = max;
        int ans = 0;


        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isCuttingPossible(ribbons, mid, k)) {
                low = mid + 1;
                ans = mid;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    public int maxLength(int[] ribbons, int k) {

        int max = Integer.MIN_VALUE;
        for (int i : ribbons) {
            max = Math.max(max, i);
        }
        int low = 1;
        int high = max;
        int ans = 0;


        while (low < high) {
            int mid = low + (high - low) / 2;
            mid++;
            if (isCuttingPossible(ribbons, mid, k)) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }

        return isCuttingPossible(ribbons, low, k) ? low : 0;
    }


    // can i get N numbers of elements
    boolean isCuttingPossible(int[] ribbons, int element, int N) {
        int sum = 0;
        for (int i = 0; i < ribbons.length; i++) {
            sum += ribbons[i] / element;
            if (sum >= N) {
                return true;
            }
        }
        return sum >= N;
    }

    @Test
    public void test() {
        int ribbons[] = new int[]{9, 7, 5};
        int k = 22;
        System.out.println(maxLength(ribbons, k));
    }
}
