package myanswers;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BinarySearch {


    /* traditional binary search
    if target found return index
    else return next position index
      1, 4, 5, 6, 8, 10  target 9
    returns index of 10
    */
    public int binarySearchReturningNextPosIndexIfNotFound(int num[], int target) {
        int low = 0;
        int high = num.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (num[mid] == target) {
                return mid;
            } else if (num[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }


    /* traditional binary search
    if target found return index
    else return prev position index
    1, 4, 5, 6, 8, 10  target 9
    returns index of 8
    */
    public int binarySearchReturningPrevPosIndexIfnotFound(int num[], int target) {
        int low = 0;
        int high = num.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (num[mid] == target) {
                return mid;
            } else if (num[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }

    /*
     it is called lower bound
     and when element are not distinct it
     returns the first occurence of element
     when not found in array it is returning the next bigger element index
     */
    public int lowerBound(int num[], int target) {
        int low = 0;
        int high = num.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (num[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return num[low] >= target ? low : low + 1;
    }


    /*
    it is called upper bound
    it returns the next greater element and never the equal one
    when not found in array it is returning the array.length, not -1
    */
    public int upperBound(int num[], int target) {
        int low = 0;
        int high = num.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (num[mid] > target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return num[low] > target ? low : low + 1;
    }


    /*
     * Returning prev pos index if not found
     *to see optimized see bottom
     *  */
    public int binarySearchReturningPrevPosIndexIfNotFound(int num[], int target) {
        int low = 0;
        int high = num.length - 1;

        while (low + 1 < high) {
            int mid = low + (high - low) / 2;

            if (num[mid] > target) {
                high = mid - 1;
            } else if (num[mid] < target) {
                low = mid;
            } else {
                // equal element case
                high = mid;
            }
        }


        int i;
        for (i = low; i <= high; i++) {
            if (num[i] == target) {
                break;
            }
            if (num[i] > target) {
                i = i - 1;
                break;
            }
        }
        return Math.min(i, high);
    }

    /*
     * Returning prev pos index always
     * equal not returned
     *  */
    public int binarySearchReturningPrevPosIndex(int num[], int target) {
        int low = 0;
        int high = num.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (num[mid] > target) {
                high = mid - 1;
            } else if (num[mid] < target) {
                low = mid;
            } else {
                // equal element case
                high = mid - 1;
            }
        }
        return num[low] < target ? low : low - 1;
    }


    @Test
    public void test() {


        int[] num = new int[]{1, 4, 6, 6, 6, 9, 15, 19, 25, 30};

        //5
        System.out.println("Testcase 1: " + binarySearchReturningNextPosIndexIfNotFound(num, 9));
        //2
        System.out.println("Testcase 2: " + binarySearchReturningNextPosIndexIfNotFound(num, 5));
        //10
        System.out.println("Testcase 3: " + binarySearchReturningNextPosIndexIfNotFound(num, 31));

        //can return any matched
        System.out.println("Testcase 4: " + binarySearchReturningNextPosIndexIfNotFound(num, 6));


        num = new int[]{1, 4, 6, 6, 6, 9, 15, 19, 25, 30};
        //2 first occurence of 6
        System.out.println("Testcase 5: " + lowerBound(num, 6));
        //6
        System.out.println("Testcase 6: " + lowerBound(num, 15));
        //10
        System.out.println("Testcase 7: " + lowerBound(num, 31));

        //2 first occurence of next(6)
        System.out.println("Testcase 8: " + lowerBound(num, 5));


        num = new int[]{1, 4, 6, 6, 6, 9, 15, 19, 25, 30};
        System.out.println("Testcase 9: " + upperBound(num, 5));
        System.out.println("Testcase 10: " + upperBound(num, 15));
        System.out.println("Testcase 11: " + upperBound(num, 31));


        num = new int[]{1, 4, 6, 6, 6, 9, 15, 19, 25, 30};
        System.out.println("Testcase 12: " + binarySearchReturningPrevPosIndexIfNotFound(num, 6));
        System.out.println("Testcase 13: " + binarySearchReturningPrevPosIndexIfNotFound(num, 7));
        System.out.println("Testcase 14: " + binarySearchReturningPrevPosIndexIfNotFound(num, 15));
        System.out.println("Testcase 15: " + binarySearchReturningPrevPosIndexIfNotFound(num, 31));
        System.out.println("Testcase 16: " + binarySearchReturningPrevPosIndexIfNotFound(num, 0));


        System.out.println("Testcase 12: " + binarySearchReturningPrevPosIndexIfNotFound2(num, 6));
        System.out.println("Testcase 13: " + binarySearchReturningPrevPosIndexIfNotFound2(num, 7));
        System.out.println("Testcase 14: " + binarySearchReturningPrevPosIndexIfNotFound2(num, 15));
        System.out.println("Testcase 15: " + binarySearchReturningPrevPosIndexIfNotFound2(num, 31));
        System.out.println("Testcase 16: " + binarySearchReturningPrevPosIndexIfNotFound2(num, 0));

    }



    public int binarySearchReturningPrevPosIndexIfNotFound2(int num[], int target) {
        int low = -1;
        int high = num.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2 +1;

            if (num[mid] > target) {
                high = mid - 1;
            } else if (num[mid] < target) {
                low = mid;
            } else {
                // equal element case
                high = mid-1;
            }
        }

        //either low is answer or low+1


        if(low==-1 || num[low] < target){
            return low;
        }else{
            return low+1;
        }
    }
}
