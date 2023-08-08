package myanswers.standards;

import org.junit.Test;

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

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (num[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;  // duplicate case will shift leftmost
            }
        }
        // return num[low] < target ? low+1 : low; this is correct too

        return num[low] >= target ? low : low + 1; //  this is correct too
    }


    /* traditional binary search
    if target found return index
    else return prev position index
    1, 4, 5, 6, 8, 10  target 9
    returns index of 8
    */
    public int binarySearchReturningPrevPosIndexIfNotFound(int num[], int target) {
        int low = 0;
        int high = num.length - 1;

        while (low < high) {
            int mid = low + (high - low + 1) / 2;

            if (num[mid] <= target) {
                low = mid; // duplicate case will shift rightmost
            } else {
                high = mid - 1;
            }
        }
        return num[low] > target ? low - 1 : low;
    }


    @Test
    public void test1() {


        int[] num = new int[]{1, 4, 6, 6, 6, 9, 15, 19, 25, 30};
        //                   {0, 1, 2, 3, 4, 5, 6,  7,  8,  9};


        System.out.println("Testcase 1: " + binarySearchReturningNextPosIndexIfNotFound(num, 9));  //5

        System.out.println("Testcase 2: " + binarySearchReturningNextPosIndexIfNotFound(num, 5));  //2

        System.out.println("Testcase 3: " + binarySearchReturningNextPosIndexIfNotFound(num, 31));  //10

        System.out.println("Testcase 4: " + binarySearchReturningNextPosIndexIfNotFound(num, 6)); //2 or 4

        System.out.println("Testcase 5: " + binarySearchReturningNextPosIndexIfNotFound(num, 0)); //0

    }

    @Test
    public void test2() {


        int [] num = new int[]{1, 4, 6, 6, 6, 9, 15, 19, 25, 30};
        //                    {0, 1, 2, 3, 4, 5, 6,  7,  8,  9};
        System.out.println("Testcase 12: " + binarySearchReturningPrevPosIndexIfNotFound(num, 6)); //4 or 2
        System.out.println("Testcase 13: " + binarySearchReturningPrevPosIndexIfNotFound(num, 7)); //4
        System.out.println("Testcase 14: " + binarySearchReturningPrevPosIndexIfNotFound(num, 15)); //6
        System.out.println("Testcase 15: " + binarySearchReturningPrevPosIndexIfNotFound(num, 31)); //9
        System.out.println("Testcase 16: " + binarySearchReturningPrevPosIndexIfNotFound(num, 0)); // -1

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

            if (num[mid] < target) {
                low = mid +1;
            } else {
                high = mid;
            }
        }

        return num[low] < target ? low+1 : low;
    }

    @Test
    public void test3() {


        int [] num = new int[]{1, 4, 6, 6, 6, 9, 15, 19, 25, 30};

        //2 first occurence of 6
        System.out.println("Testcase 5: " + lowerBound(num, 6));

        //6
        System.out.println("Testcase 6: " + lowerBound(num, 15));

        //10
        System.out.println("Testcase 7: " + lowerBound(num, 31));

        //2
        System.out.println("Testcase 8: " + lowerBound(num, 5));

        //0
        System.out.println("Testcase 9: " + lowerBound(num, -1));
    }




    /*
it is called upper bound
it returns the next greater element and never the equal one
*/
    public int upperBound(int num[], int target) {
        int low = 0;
        int high = num.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            if (target >= num[mid]) {
                low = mid+1;
            } else {
                high = mid;
            }
        }
        return num[low] > target ? low : low + 1;
    }


    @Test
    public void test4() {

        int []  num = new int[]{1, 4, 6, 6, 6, 9, 15, 19, 25, 30};
        System.out.println("Testcase 9: " + upperBound(num, 5)); //2
        System.out.println("Testcase 9.1: " + upperBound(num, 6));//5
        System.out.println("Testcase 10: " + upperBound(num, 15));//7
        System.out.println("Testcase 11: " + upperBound(num, 31));//10
    }
/*




main method






 */
}
