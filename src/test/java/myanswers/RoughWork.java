package myanswers;

import org.junit.Test;

import java.util.*;

/**
 * Created by smridul on 6/13/18.
 */
public class RoughWork {
    @Test
    public void test() {

        String s = "abcabcbb";

        int i = 0, j = 0;
        int max = 0;
        int jmax = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while (i < s.length() && j < s.length()) {
            char current = s.charAt(j);
            if (!isCharacterExistInThisRange(i, j, current, map)) {
                int length = j - i + 1;
                if (length > max) {
                    max = length;
                    jmax = j;
                }
                map.put(current, j);
                j++;
            } else {
                int lastSeenPostion = map.get(current);
                i = Math.max(lastSeenPostion + 1, i);
            }
        }
        System.out.print(max + " starting at s[" + (jmax - max + 1) + "]=" + s.charAt(jmax - max + 1) + " ending at s[" + jmax + "]=" + s.charAt(jmax));

    }

    public boolean isCharacterExistInThisRange(int i, int j, char c, HashMap<Character, Integer> map) {
        if (map.containsKey(c)) {
            int lastSeen = map.get(c);
            return (lastSeen >= i);
        }
        return false;
    }

    public int lengthOfLongestSubstringTwoDistinct(String s) {

        int i = 0, j = 0;
        int max = 0;
        int k = 3;
        char prev = '\0';
        int firstOccurenceOfNextElementToStart = -1;
        int numberOfCharactersInSeq = 0;


        HashMap<Character, Integer> map = new HashMap<>();
        while (i < s.length() && j < s.length()) {
            char current = s.charAt(j);

            if (!isElementInTheSeqStartingFromI(current, i, map)) {

                if (numberOfCharactersInSeq < k) {
                    numberOfCharactersInSeq++;
                } else {

                    // get the leftmost value because that has to be removed
                    int leftMost = s.length();
                    ;
                    for (int value : map.values()) {
                        leftMost = Math.min(leftMost, value);
                    }


                    char c = s.charAt(leftMost);
                    map.remove(c);
                    i = leftMost + 1;
                    numberOfCharactersInSeq = numberOfCharactersInSeq - 1;
                    continue;
                }
            }

            if (numberOfCharactersInSeq == 2 && prev != current) {
                firstOccurenceOfNextElementToStart = j;
            }

//            if (numberOfCharactersInSeq == k + 1) {
//                i = firstOccurenceOfNextElementToStart;
//                numberOfCharactersInSeq = numberOfCharactersInSeq - 2;
//                continue;
//            }

            map.put(current, j);
            max = Math.max(max, j - i + 1);
            j++;
            prev = current; // update pre only when we increment j

        }

        return max;
    }

    public boolean isElementInTheSeqStartingFromI(char c, int i, HashMap<Character, Integer> map) {
        return (map.containsKey(c) && map.get(c) >= i);
    }

    @Test
    public void test2() {
        System.out.print(lengthOfLongestSubstringTwoDistinct("ababffzzeee"));
    }

    @Test
    public void test3() {

        Intt totalMultiplies = new Intt();
        System.out.println(isPalindrome(213212));

    }

    double power(double x, int y, Intt totalMultiplies) {

        if (y == 1) {
            return x;
        }

        if ((y & 1) == 1) {
            //odd number
            double result = power(x, y / 2, totalMultiplies);
            totalMultiplies.value += 2;
            return x * result * result;
        } else {
            double result = power(x, y / 2, totalMultiplies);
            totalMultiplies.value += 1;
            return result * result;
        }

    }

    double poweriterative(double x, int y) {
        int power = y;
        double result = 1;
        while (power != 0) {
            if (power % 2 == 1) {
                // odd
                result = result * x;
            }
            x = x * x;
            power >>>= 1;
        }
        return result;
    }


    double powerWhenYpowerof2(double x, int y) {
        double result = 1;
        while (y != 0) {
            result = x;
            x = x * x;
            y >>>= 1;
        }
        return result;
    }

    double power(double x, int y) {
        double result = 1;
        double intialX = x;
        while (y != 0) {
            ///when x= odd multuply extra x
            if ((y & 1) != 0 && y != 1) {
                result = x * intialX;
            } else {
                result = x;
            }
            x = x * x;
            y >>>= 1;
        }
        return result;
    }


    boolean isPalindrome(int x) {
        int numberofdigits = (int) Math.log10(x) + 1;
        int leftmask = (int) Math.pow(10, numberofdigits);
        int rightmask = (int) Math.pow(10, 1);

        int start = numberofdigits;
        int end = 1;
        while (start > end) {
            int leftDigit = (x % leftmask) / (leftmask / 10);
            int rightDigit = (x % rightmask) / (rightmask / 10);

            if (rightDigit != leftDigit) {
                return false;
            }

            start--;
            end++;
            leftmask = leftmask / 10;
            rightmask = rightmask * 10;
        }
        return true;
    }

    @Test
    public void test5() {
        getSum(1, 2);
    }

    public int getSum(int a, int b) {

        int k = 1;
        int sum = 0;
        int carryIn = 0;
        while (k != 0) {
            int ak = a & k;
            int bk = b & k;

            int carryout = (carryIn & ak) | (carryIn & bk) | (ak & bk);
            sum = sum | (ak ^ bk ^ carryIn);
            carryIn = carryout << 1;
            k <<= 1;
        }
        return sum;

    }

    public boolean advance(int currentPos, int[] array) {
        if (currentPos >= array.length - 1) {
            return true;
        }

        for (int steps = array[currentPos]; steps > 0; steps--) {
            int nextPos = currentPos + steps;
            boolean canReach = advance(nextPos, array);
            if (canReach) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void advanceTest() {
        // int [] array = new int[]{3, 3, 1, 0, 2, 0, 1};
        int[] array = new int[]{3, 2, 0, 0, 2, 0, 1};
        boolean can = advance(0, array);
        System.out.print(can);
    }


    @Test
    public void stock1() {
        List a = Arrays.asList("12", "24");
        List a1 = Arrays.asList(new Integer[]{1, 2});
        ArrayList<String> b = new ArrayList<>(Arrays.asList("1", "2"));
        b.add(0, "3");

        LinkedList<String> c = new LinkedList<>(Arrays.asList("1", "2"));
        c.add(0, "3");
        int x = 0;


    }

    @Test
    public void binarySearch() {
        List a = Arrays.asList("12", "24");
        int[] arr1 = new int[]{2, 3, 4, 5, 6, 6, 8, 10, 11};
        int index = Arrays.binarySearch(arr1, 7);
        // -(number of elements less than 7 + 1)


        System.out.println(index);

        int index2 = Collections.binarySearch(a, "344", new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        System.out.print(index2);
    }

    public String encode(String str) {
        int[] freq = new int[26];

        for (int i = 0; i < str.length(); i++)
            freq[str.charAt(i) - 'a']++;

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < freq.length; i++) {

            if (freq[i] == 0) {
                continue;
            } else {

                while (freq[i]-- != 0) {
                    int c = 'a' + i;
                    sb.append((char) c);
                }

            }
        }

        return sb.toString();
    }

    @Test
    public void testEncode() {
        System.out.print(encode("bcajlpiaabcht"));
    }


    @Test
    public void testhIndex() {
        //int[] citations = new int[]{8, 9, 10, 1, 3, 2, 2};
        int[] citations = new int[]{1, 4, 1, 4, 2, 1, 3, 5, 6};

        int k = hIndex(citations);
        System.out.println(k);

        k = myhIndex(citations);
        System.out.println(k);

        //System.out.println(0%2);
        //System.out.println(2%0);


        System.out.println(gcd(36, 156));
    }

    public long gcd(long x, long y) {
        return y == 0 ? x : gcd(y, x % y);
    }


    public int hIndex(int[] citations) {

        int length = citations.length;
        if (length == 0) {
            return 0;
        }

        int[] array2 = new int[length + 1];
        for (int i = 0; i < length; i++) {
            if (citations[i] > length) {
                array2[length] += 1;
            } else {
                array2[citations[i]] += 1;
            }
        }
        int t = 0;

        for (int i = length; i >= 0; i--) {
            t = t + array2[i];
            if (t >= i) {
                return i;
            }
        }
        return 0;
    }

    public int myhIndex(int[] citations) {

        int length = citations.length;
        if (length == 0) {
            return 0;
        }

        int[] array2 = new int[length + 1];
        for (int i = 0; i < length; i++) {
            if (citations[i] > length) {
                array2[length] += 1;
            } else {
                array2[citations[i]] += 1;
            }
        }
        int numberOfElements = 0;

        for (int i = length; i >= 0; i--) {
            numberOfElements = numberOfElements + array2[i];

            if (i >= numberOfElements) {
                continue;
            } else {
                return numberOfElements - array2[i];
            }
        }

        return 0;
    }
}

class Intt {
    int value;
}