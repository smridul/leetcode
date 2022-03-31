package leetcode.MySolutions;

public class KthElementInTwoSortedArray {

    public int findKthInTwoSortedArray(int[] A, int[] B, int k) {
        int len1 = A.length;
        int len2 = B.length;
        if (k < 0 || k > len1 + len2 - 1) {
            return -1;
        }

        int low = Math.max(-1, k - 1 - (len2 - 1));
        int high = Math.min(k, len1 - 1);

        int i = -1;
        int j = k;
        while (low <= high) {
            i = low + (high - low) / 2;
            j = k - 1 - i;

            if ((i == -1 || j == len2 - 1 || A[i] <= B[j + 1]) &&
                    (j == -1 || i == len1 - 1 || B[j] <= A[i + 1])) {

                int val1 = i < 0 ? Integer.MIN_VALUE : A[i];
                int val2 = j < 0 ? Integer.MIN_VALUE : B[j];

                return Math.max(val1, val2);
            } else if (i != -1 && j != len2 - 1 && A[i] > B[j + 1]) {
                high = i - 1;
            } else {
                low = i + 1;
            }
        }
        return -1;
    }


    public double findMedianSortedArrays(int[] array1, int[] array2) {

        int totalLength = array1.length + array2.length;

        int low = 0;
        int high = totalLength - 1;

        int first;
        if (totalLength % 2 == 1) {
            first = low + (high - low) / 2;
            int firstMedian = findKthInTwoSortedArray(array1, array2, first);
            return firstMedian;
        } else {
            first = low + (high - low) / 2;
            int second = first + 1;
            int firstMedian = findKthInTwoSortedArray(array1, array2, first);
            int secondMedian = findKthInTwoSortedArray(array1, array2, second);
            return (double) (firstMedian + secondMedian) / 2;
        }
    }

    public double findMedianSortedArrays2(int[] A, int[] B) {
        int len1 = A.length;
        int len2 = B.length;
        int totalLength = len1+len2;

        int k = (totalLength - 1) / 2;

        if (k < 0 || k > len1 + len2 - 1) {
            return -1;
        }

        int low = Math.max(-1, k - 1 - (len2 - 1));
        int high = Math.min(k, len1 - 1);

        int i = -1;
        int j = k;
        while (low <= high) {
            i = low + (high - low) / 2;
            j = k - 1 - i;

            if ((i == -1 || j == len2 - 1 || A[i] <= B[j + 1]) &&
                    (j == -1 || i == len1 - 1 || B[j] <= A[i + 1])) {

                int val1 = i < 0 ? Integer.MIN_VALUE : A[i];
                int val2 = j < 0 ? Integer.MIN_VALUE : B[j];


                int val3 = i >= len1 - 1 ? Integer.MAX_VALUE : A[i + 1];
                int val4 = j >= len2 - 1 ? Integer.MAX_VALUE : B[j + 1];

                int totalLen = len1 + len2;
                if (totalLen % 2 == 0) {
                    return (double) (Math.max(val1, val2) + Math.min(val3, val4)) / 2;
                } else {
                    return Math.max(val1, val2);
                }


            } else if (i != -1 && j != len2 - 1 && A[i] > B[j + 1]) {
                high = i - 1;
            } else {
                low = i + 1;
            }
        }
        return -1;
    }


    public void test() {
        int[] array1 = new int[]{1, 2};
        int[] array2 = new int[]{3, 4, 11, 12};


        System.out.println(findMedianSortedArrays2(array1, array2));

        for (int i = -1; i <= array1.length + array2.length; i++) {
            System.out.print(findKthInTwoSortedArray(array1, array2, i));
            System.out.print(" ");
        }
    }
}
